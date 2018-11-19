package com.zzqx.support.framework.mina;
import java.util.List;

import com.zzqx.mvc.service.GroupService;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.zzqx.mvc.SpringContext;
import com.zzqx.mvc.entity.Terminal;
import com.zzqx.mvc.service.TerminalService;
import com.zzqx.support.utils.StringHelper;
import com.zzqx.support.utils.machine.hardware.HardwareHandler;
import com.zzqx.support.utils.net.SocketDataSender;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;


//负责处理连接上来的客户机，即消息处理器
@Transactional
public class MinaServer extends IoHandlerAdapter{
	
	@Override
	public void messageReceived(IoSession session, Object msg) throws Exception {
		// TODO Auto-generated method stub
		super.messageReceived(session, msg);
		String message = msg.toString();
		MinaManager.updateTime(session);
		if(StringHelper.isBlank(message)) return;
		//String ip = StringHelper.getIp(session.getRemoteAddress());
//		message = java.net.URLDecoder.decode(message, "UTF-8");
		MinaSession minaSession = MinaManager.get(session);
		if(Mina.SOCKET_DATA_CLIENT.equalsIgnoreCase(message)) {
			MinaManager.isFlashClient(session);
			System.out.println("客户端："+MinaManager.getFlashClients().size()+"，控制端："+MinaManager.getControllerClients().size()+"，其它："+MinaManager.getOtherClients().size());
			String mac = minaSession==null?"":minaSession.getMac();
			if(StringHelper.isNotBlank(mac)) {
				TerminalService terminalService = (TerminalService) SpringContext.getBean("terminalService");
				List<Terminal> terminals = terminalService.find(Restrictions.eq("mac", mac));
				for(Terminal terminal : terminals) {
					JsonConfig jsonConfig = new JsonConfig();
					jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
					SocketDataSender sender = new SocketDataSender();
					sender.send(session, "play:"+JSONArray.fromObject(terminal.getTerminalContents(), jsonConfig).toString());
				}
			}
		} else if(Mina.SOCKET_DATA_CONTROLLER.equalsIgnoreCase(message)) {
			MinaManager.isController(session);
			System.out.println("客户端："+MinaManager.getFlashClients().size()+"，控制端："+MinaManager.getControllerClients().size()+"，其它："+MinaManager.getOtherClients().size());
		} else if(message.toLowerCase().indexOf(Mina.SOCKET_DATA_MAC.toLowerCase()) == 0) {
			String mac = message.substring(Mina.SOCKET_DATA_MAC.length()).trim();
			MinaManager.setMac(session, mac);
			if(minaSession != null && minaSession.getType() == Mina.SOCKET_CLIENT_TYPE_FLASH) {
				TerminalService terminalService = (TerminalService) SpringContext.getBean("terminalService");
				List<Terminal> terminals = terminalService.find(Restrictions.eq("mac", mac));
				for(Terminal terminal : terminals) {
					JsonConfig jsonConfig = new JsonConfig();
					jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
					SocketDataSender sender = new SocketDataSender();
					sender.send(session, "play:"+JSONArray.fromObject(terminal.getTerminalContents(), jsonConfig).toString());
					if(terminal.getSerialNumber() == 0) {
						terminal.getGroup().setServerNodeMac(mac);
						GroupService groupService = (GroupService) SpringContext.getBean("groupService");
						groupService.saveOrUpdate(terminal.getGroup());
					}
				}
			}
		} else if(message.toLowerCase().indexOf(Mina.SOCKET_DATA_TRANSMIT.toLowerCase()) == 0) {
			for(String cmd : message.substring(Mina.SOCKET_DATA_TRANSMIT.length()).trim().split(",")) {
				if(cmd.contains("_")) {
					String ipOrCodeName = cmd.substring(0, cmd.indexOf("_"));
					String messageString = cmd.substring(cmd.indexOf("_")+1, cmd.length());
					SocketDataSender sender = new SocketDataSender();
					sender.sendToTerminal(ipOrCodeName, messageString);
				}
			}
		} else if(message.toLowerCase().indexOf(Mina.SOCKET_DATA_HARDWARE.toLowerCase()) == 0) {
			String hardwareMonitorString = message.substring(Mina.SOCKET_DATA_TRANSMIT.length()).trim();
			String mac = minaSession==null?"":minaSession.getMac();
			HardwareHandler.setHardwareMonitor(mac, hardwareMonitorString);
		}
	}

	/**
	 * 
	 * 与客户端断开连接
	 */
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionClosed(session);
		MinaSession minaSession = MinaManager.get(session);
		if(minaSession != null) {
			HardwareHandler.removeHardwareMonitor(MinaManager.get(session).getMac());
		}
		MinaManager.remove(session);
		System.out.println("=============连接断开："+session.getRemoteAddress()+"   =============");
		System.out.println("客户端："+MinaManager.getFlashClients().size()+"，控制端："+MinaManager.getControllerClients().size()+"，其它："+MinaManager.getOtherClients().size());
	}

	/**
	 * 客户端与服务器新创建连接
	 */
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		super.sessionCreated(session);
		MinaManager.add(session);
		System.out.println("*************新的连接："+session.getRemoteAddress()+"   *************");
		System.out.println("客户端："+MinaManager.getFlashClients().size()+"，控制端："+MinaManager.getControllerClients().size()+"，其它："+MinaManager.getOtherClients().size());
		session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, Mina.SOCKET_CLIENT_TIMEOUT);
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(session, cause);
		MinaManager.remove(session);
		session.closeNow();
	}
	
	@Override
	public void inputClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.inputClosed(session);
	}
	
	/**
	 * 向客户端发送连接
	 */
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		super.messageSent(session, message);
	}
	
	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.sessionIdle(session, status);
		if(status == IdleStatus.BOTH_IDLE) {
			//System.out.println("闲置");
			System.out.println(session.getRemoteAddress()+"连接超时，服务器断开连接！");
			session.closeNow();
		}
	}
	
	/**
	 * 打开与客户端的连接
	 */
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionOpened(session);
	}
  
}