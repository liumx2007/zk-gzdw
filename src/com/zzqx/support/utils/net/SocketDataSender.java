package com.zzqx.support.utils.net;

import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.mina.core.session.IoSession;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zzqx.Global;
import com.zzqx.mvc.SpringContext;
import com.zzqx.mvc.entity.Personnel;
import com.zzqx.mvc.entity.Terminal;
import com.zzqx.mvc.service.TerminalService;
import com.zzqx.support.framework.mina.Mina;
import com.zzqx.support.framework.mina.MinaManager;
import com.zzqx.support.framework.mina.MinaSession;
import com.zzqx.support.framework.mina.androidser.AndroidConstant;
import com.zzqx.support.framework.mina.androidser.AndroidMinaManager;
import com.zzqx.support.framework.mina.androidser.AndroidMinaSession;
import com.zzqx.support.utils.StringHelper;

public class SocketDataSender extends Thread {

	private static Logger logger = LoggerFactory.getLogger(SocketDataSender.class);
	
	private String target;
	private String terminalMark;
	private int port;
	private String message;
	
	@Override
	public void run() {
		if("tcp_server".equals(target)) {
			Socket socket;
			PrintWriter out;
			try {
				socket = new Socket(terminalMark, port);
				out = new PrintWriter(socket.getOutputStream(),true);
				out.print(message);
				out.flush();
				out.close();
				socket.close();
				System.out.println("向"+terminalMark+"("+port+")"+"发送("+message+")成功！");
			} catch (Exception e) {
				System.out.println("向"+terminalMark+"("+port+")"+"发送("+message+")失败！");
			}
		} else if("udp_server".equals(target)) {
			DatagramSocket socket;
			try {
				socket = new DatagramSocket();
				byte[] messgaeBuf = message.getBytes();
				InetAddress address = InetAddress.getByName(terminalMark);
				DatagramPacket packet = new DatagramPacket(messgaeBuf, messgaeBuf.length, address, port);
				socket.send(packet);
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			TerminalService terminalService = (TerminalService) SpringContext.getBean("terminalService");
			String terminalIp = "";
			String terminalMac = "";
			if(StringHelper.isIp(terminalMark)) {
				terminalIp = terminalMark;
				//todo
				System.out.println(terminalIp);
			} else if(StringHelper.isMac(terminalMark)) {
				terminalMac = terminalMark;
				//todo
				System.out.println(terminalMac);
			} else {
				List<Terminal> terminals = terminalService.find(Restrictions.eq("codeName", terminalMark));
				if(terminals.size() > 0) {
					terminalIp = terminals.get(0).getIp();
					terminalMac = terminals.get(0).getMac();
					//todo
					System.out.println("------------2----------------"+terminalIp+terminalMac);
				}
			}
			List<MinaSession> list = "client".equals(target)?MinaManager.getFlashClients():"controller".equals(target)?MinaManager.getControllerClients():MinaManager.getOtherClients();
			for(MinaSession minaSession : list) {
				IoSession session = minaSession.getIoSession();
				if(!session.isConnected()) continue;
				String ip = StringHelper.getIp(session.getRemoteAddress());
				if((StringHelper.isNotBlank(ip) && (ip.equals(terminalIp) || Global.localIP.equals(terminalIp))) || (StringHelper.isNotBlank(terminalMac) && terminalMac.equals(minaSession.getMac()))) {
					session.write(message);
				}
			}
		}
	}
	
	public void sendToTerminal(String terminalMark, String message) {
		this.target = "terminal";
		this.terminalMark = terminalMark;
		this.message = message+Mina.SOCKET_DATA_ENDING;
		this.start();
	}
	
	public void sendToClient(String terminalMark, String message) {
		this.target = "client";
		this.terminalMark = terminalMark;
		this.message = message+Mina.SOCKET_DATA_ENDING;
		this.start();
	}
	
	public void sendToController(String terminalMark, String message) {
		this.target = "controller";
		this.terminalMark = terminalMark;
		this.message = message+Mina.SOCKET_DATA_ENDING;
		this.start();
	}
	
	public void boardcast(String message,int ...clientTypes) {
		if(clientTypes != null) {
			List<MinaSession> list = MinaManager.getClients();
			list.stream().filter(minaSession->ArrayUtils.contains(clientTypes, minaSession.getType())).forEach(minaSession->{
				System.out.println("广播："+message);
				minaSession.getIoSession().write(message+Mina.SOCKET_DATA_ENDING);
			});
		}
	}
	
	public void send(IoSession ioSession, String message) {
		MinaSession minaSession = MinaManager.get(ioSession);
		if(minaSession != null) {
			if(minaSession.getType() == Mina.SOCKET_CLIENT_TYPE_FLASH) {
				ioSession.write(message+Mina.SOCKET_DATA_ENDING);
			} else {
				ioSession.write(message);
			}
		}
	}
	
	public static void sendWatchMsg(Integer msgType, String watchCode, Personnel p) {
		AndroidMinaSession minaSessionSend;
		minaSessionSend = AndroidMinaManager.getByWatchCode(p.getWatch_code());
		if(minaSessionSend!=null&&minaSessionSend.getIoSession()!=null){
			logger.info("通知"+p.getName()+"收消息成功");
			SocketDataSender.sendAndroid(minaSessionSend.getIoSession(),AndroidConstant.SOCKET_DATA_WATCH_PREFIX+msgType+","+watchCode);
		}
	}
	
	public static void sendAndroid(IoSession ioSession, String message) {
		AndroidMinaSession minaSession = AndroidMinaManager.get(ioSession);
		if(minaSession != null) {
			if(minaSession.getType() == Mina.SOCKET_CLIENT_TYPE_FLASH) {
				ioSession.write(message+Mina.SOCKET_DATA_ENDING);
			} else {
				ioSession.write(message);
			}
		}
	}
	
	public void sendToTCPServer(String ip, int port, String message) {
		this.target = "tcp_server";
		this.terminalMark = ip;
		this.port = port;
		this.message = message;
		this.start();
	}

	public void sendToUDPServer(String ip, int port, String message) {
		this.target = "udp_server";
		this.terminalMark = ip;
		this.port = port;
		this.message = message;
		this.start();
	}
}