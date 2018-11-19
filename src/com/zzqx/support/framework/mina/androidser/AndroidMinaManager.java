package com.zzqx.support.framework.mina.androidser;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import org.apache.mina.core.session.IoSession;

import com.google.common.collect.Lists;
import com.zzqx.support.framework.mina.MinaManager;
import com.zzqx.support.framework.mina.MinaSession;

public class AndroidMinaManager {
	
	private static List<AndroidMinaSession> clients = new CopyOnWriteArrayList<>();

	public static AndroidMinaSession get(IoSession ioSession) {
		for (AndroidMinaSession minaSession : clients) {
			if (minaSession.getIoSession() == ioSession) {
				return minaSession;
			}
		}
		return null;
	}

	public static AndroidMinaSession getByWatchCode(String watchCode) {
		if (null == watchCode) {
			return null;
		}
		for (AndroidMinaSession minaSession : clients) {
			if (watchCode.equalsIgnoreCase(minaSession.getWatchCode())) {//2ab35f07-85e6-4bc6-914f-175d98e1524b
				return minaSession;
			}
		}
		return null;
	}

	public static List<AndroidMinaSession> getListByWatchCode(String watchCode) {
		if (null == watchCode) {
			return null;
		}
		List<AndroidMinaSession> list = Lists.newArrayList();
		for (AndroidMinaSession minaSession : clients) {
			if (watchCode.equalsIgnoreCase(minaSession.getWatchCode())) {//2ab35f07-85e6-4bc6-914f-175d98e1524b
				list.add(minaSession);
			}
		}
		return list;
	}

	public static boolean setWatchCode(AndroidMinaSession minaSession, String watchCode) throws UnknownHostException {
		
		if (minaSession != null) {
			minaSession.setWatchCode(watchCode);
		}
		return false;
	}

	public static boolean setWatchCode(IoSession session, String watchCode) {
		for (AndroidMinaSession minaSession : clients) {
			if (minaSession.getIoSession() == session) {
				try {
					AndroidMinaManager.setWatchCode(minaSession, watchCode);
					return true;
				} catch (UnknownHostException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return false;
	}

	public static List<AndroidMinaSession> getClients() {
		return clients;
	}

	public static List<AndroidMinaSession> getOtherClients() {
		return clients.stream().filter(minaSession -> minaSession.getType() == AndroidConstant.SOCKET_CLIENT_TYPE_OTHER)
				.collect(Collectors.toList());
	}

	public static List<AndroidMinaSession> getFlashClients() {
		return clients.stream().filter(minaSession -> minaSession.getType() == AndroidConstant.SOCKET_CLIENT_TYPE_FLASH)
				.collect(Collectors.toList());
	}

	public static boolean add(AndroidMinaSession minaSession) throws Exception {
		return clients.add(minaSession);
	}

	public static boolean add(IoSession ioSession) throws Exception {
		AndroidMinaSession minaSession = new AndroidMinaSession(ioSession);
		return add(minaSession);
	}

	public static void remove(AndroidMinaSession minaSession) {
		if (minaSession != null) {
			remove(minaSession.getIoSession());
		}
	}

	public static void remove(IoSession ioSession) {
		if (ioSession != null) {
			clients.forEach(minaSession -> {
				if (minaSession.getIoSession() == ioSession) {
					clients.remove(minaSession);
					ioSession.closeNow();
				}
			});
		}
	}

	public static boolean exist(IoSession ioSession) {
		for (AndroidMinaSession minaSession : clients) {
			if (minaSession.getIoSession() == ioSession) {
				return true;
			}
		}
		return false;
	}

	public static void updateStringBuffer(IoSession ioSession, StringBuffer stringBuffer) {
		clients.forEach(minaSession -> {
			if (minaSession.getIoSession() == ioSession) {
				minaSession.setStringBuffer(stringBuffer);
			}
		});
	}

	public static void isFlashClient(IoSession ioSession) {
		clients.forEach(minaSession -> {
			if (minaSession.getIoSession() == ioSession) {
				minaSession.setType(AndroidConstant.SOCKET_CLIENT_TYPE_FLASH);
			}
		});
	}

	public static void updateTime(IoSession ioSession) {
		MinaSession minaSession = MinaManager.get(ioSession);
		if (minaSession != null) {
			minaSession.setTime(new Date().getTime());
		}
	}
}
