package com.zzqx.support.framework.mina.ElectricDrive;

import com.zzqx.support.utils.StringHelper;
import org.apache.mina.core.session.IoSession;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class ElectricDriveManager {

    private static List<ElectricDriveSession> clients = new CopyOnWriteArrayList<>();

    public static ElectricDriveSession get(IoSession ioSession) {
        for(ElectricDriveSession ElectricDriveSession : clients) {
            if(ElectricDriveSession.getIoSession() == ioSession) {
                return ElectricDriveSession;
            }
        }
        return null;
    }

    public static ElectricDriveSession getList(){
        for(ElectricDriveSession ElectricDriveSession : clients) {
            return ElectricDriveSession;
        }
        return null;
    }

//    public static ElectricDriveSession getByMac(String mac) {
//        if(!StringHelper.isMac(mac)) {
//            return null;
//        }
//        for(ElectricDriveSession ElectricDriveSession : clients) {
//            if(mac.equalsIgnoreCase(ElectricDriveSession.getMac())) {
//                return ElectricDriveSession;
//            }
//        }
//        return null;
//    }

//    public static boolean setMac(ElectricDriveSession ElectricDriveSession, String mac) throws UnknownHostException {
//        if(!StringHelper.isMac(mac)) {
//            throw new UnknownHostException("无效的MAC地址！");
//        }
//        if(ElectricDriveSession != null) {
//            ElectricDriveSession.setMac(mac);
//        }
//        return false;
//    }

//    public static boolean setMac(IoSession session, String mac) {
//        for(ElectricDriveSession ElectricDriveSession : clients) {
//            if(ElectricDriveSession.getIoSession() == session) {
//                try {
//                    MinaManager.setMac(ElectricDriveSession, mac);
//                    return true;
//                } catch (UnknownHostException e) {
//                    e.printStackTrace();
//                    return false;
//                }
//            }
//        }
//        return false;
//    }

    public static List<ElectricDriveSession> getClients() {
        return clients;
    }

    public static List<ElectricDriveSession> getOtherClients() {
        return clients.stream().filter(ElectricDriveSession->ElectricDriveSession.getType()==ElectricDriveConstant.SOCKET_CLIENT_TYPE_OTHER).collect(Collectors.toList());
    }

    public static List<ElectricDriveSession> getFlashClients() {
        return clients.stream().filter(ElectricDriveSession->ElectricDriveSession.getType()==ElectricDriveConstant.SOCKET_CLIENT_TYPE_FLASH).collect(Collectors.toList());
    }

    public static List<ElectricDriveSession> getControllerClients() {
        return clients.stream().filter(ElectricDriveSession->ElectricDriveSession.getType()==ElectricDriveConstant.SOCKET_CLIENT_TYPE_CONTROLLER).collect(Collectors.toList());
    }

    public static boolean add(ElectricDriveSession ElectricDriveSession) throws Exception {
        return clients.add(ElectricDriveSession);
    }

    public static boolean add(IoSession ioSession) throws Exception {
        ElectricDriveSession ElectricDriveSession = new ElectricDriveSession(ioSession);
        return add(ElectricDriveSession);
    }

    public static void remove(ElectricDriveSession ElectricDriveSession) {
        if(ElectricDriveSession != null) {
            remove(ElectricDriveSession.getIoSession());
        }
    }

    public static void remove(IoSession ioSession) {
        if(ioSession != null) {
            clients.forEach(ElectricDriveSession -> {
                if(ElectricDriveSession.getIoSession() == ioSession) {
                    clients.remove(ElectricDriveSession);
                    ioSession.closeNow();
                }
            });
        }
    }

    public static boolean exist(IoSession ioSession) {
        for(ElectricDriveSession ElectricDriveSession : clients) {
            if(ElectricDriveSession.getIoSession() == ioSession) {
                return true;
            }
        }
        return false;
    }

    public static void updateStringBuffer(IoSession ioSession, StringBuffer stringBuffer) {
        clients.forEach(ElectricDriveSession -> {
            if(ElectricDriveSession.getIoSession() == ioSession) {
                ElectricDriveSession.setStringBuffer(stringBuffer);
            }
        });
    }

    public static void isFlashClient(IoSession ioSession) {
        clients.forEach(ElectricDriveSession -> {
            if(ElectricDriveSession.getIoSession() == ioSession) {
                ElectricDriveSession.setType(ElectricDriveConstant.SOCKET_CLIENT_TYPE_FLASH);
            }
        });
    }

    public static void isController(IoSession ioSession) {
        clients.forEach(ElectricDriveSession -> {
            if(ElectricDriveSession.getIoSession() == ioSession) {
                ElectricDriveSession.setType(ElectricDriveConstant.SOCKET_CLIENT_TYPE_CONTROLLER);
            }
        });
    }

    public static void updateTime(IoSession ioSession) {
        ElectricDriveSession ElectricDriveSession = ElectricDriveManager.get(ioSession);
        if(ElectricDriveSession != null) {
            ElectricDriveSession.setTime(new Date().getTime());
        }
    }
}

