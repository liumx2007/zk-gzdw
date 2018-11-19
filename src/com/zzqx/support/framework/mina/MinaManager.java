package com.zzqx.support.framework.mina;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import org.apache.mina.core.session.IoSession;

import com.zzqx.support.utils.StringHelper;

public class MinaManager {
	
	private static List<MinaSession> clients = new CopyOnWriteArrayList<>();
	
	public static MinaSession get(IoSession ioSession) {
		for(MinaSession minaSession : clients) {
			if(minaSession.getIoSession() == ioSession) {
				return minaSession;
			}
		}
		return null;
	}
	
	public static MinaSession getByMac(String mac) {
		if(!StringHelper.isMac(mac)) {
			return null;
		}
		for(MinaSession minaSession : clients) {
			if(mac.equalsIgnoreCase(minaSession.getMac())) {
				return minaSession;
			}
		}
		return null;
	}
	
	public static boolean setMac(MinaSession minaSession, String mac) throws UnknownHostException {
		if(!StringHelper.isMac(mac)) {
			throw new UnknownHostException("无效的MAC地址！");
		}
		if(minaSession != null) {
			minaSession.setMac(mac);
		}
		return false;
	}
	
	public static boolean setMac(IoSession session, String mac) {
		for(MinaSession minaSession : clients) {
			if(minaSession.getIoSession() == session) {
				try {
					MinaManager.setMac(minaSession, mac);
					return true;
				} catch (UnknownHostException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return false;
	}
	
	public static List<MinaSession> getClients() {
		return clients;
	}
	
	public static List<MinaSession> getOtherClients() {
		return clients.stream().filter(minaSession->minaSession.getType()==Mina.SOCKET_CLIENT_TYPE_OTHER).collect(Collectors.toList());
	}
	
	public static List<MinaSession> getFlashClients() {
		return clients.stream().filter(minaSession->minaSession.getType()==Mina.SOCKET_CLIENT_TYPE_FLASH).collect(Collectors.toList());
	}
	
	public static List<MinaSession> getControllerClients() {
		return clients.stream().filter(minaSession->minaSession.getType()==Mina.SOCKET_CLIENT_TYPE_CONTROLLER).collect(Collectors.toList());
	}
	
	public static boolean add(MinaSession minaSession) throws Exception {
		return clients.add(minaSession);
	}
	
	public static boolean add(IoSession ioSession) throws Exception {
		MinaSession minaSession = new MinaSession(ioSession);
		return add(minaSession);
	}
	
	public static void remove(MinaSession minaSession) {
		if(minaSession != null) {
			remove(minaSession.getIoSession());
		}
	}
	
	public static void remove(IoSession ioSession) {
		if(ioSession != null) {
			clients.forEach(minaSession -> {
				if(minaSession.getIoSession() == ioSession) {
					clients.remove(minaSession);
					ioSession.closeNow();
				}
			});
		}
	}
	
	public static boolean exist(IoSession ioSession) {
		for(MinaSession minaSession : clients) {
			if(minaSession.getIoSession() == ioSession) {
				return true;
			}
		}
		return false;
	}
	
	public static void updateStringBuffer(IoSession ioSession, StringBuffer stringBuffer) {
		clients.forEach(minaSession -> {
			if(minaSession.getIoSession() == ioSession) {
				minaSession.setStringBuffer(stringBuffer);
			}
		});
	}
	
	public static void isFlashClient(IoSession ioSession) {
		clients.forEach(minaSession -> {
			if(minaSession.getIoSession() == ioSession) {
				minaSession.setType(Mina.SOCKET_CLIENT_TYPE_FLASH);
			}
		});
	}
	
	public static void isController(IoSession ioSession) {
		clients.forEach(minaSession -> {
			if(minaSession.getIoSession() == ioSession) {
				minaSession.setType(Mina.SOCKET_CLIENT_TYPE_CONTROLLER);
			}
		});
	}
	
	public static void updateTime(IoSession ioSession) {
		MinaSession minaSession = MinaManager.get(ioSession);
		if(minaSession != null) {
			minaSession.setTime(new Date().getTime());
		}
	}
}
