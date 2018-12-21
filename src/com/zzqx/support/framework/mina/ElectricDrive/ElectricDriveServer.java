package com.zzqx.support.framework.mina.ElectricDrive;

import com.zzqx.support.utils.StringHelper;
import org.apache.commons.lang.ArrayUtils;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.List;

public class ElectricDriveServer extends IoHandlerAdapter{

    private static Logger logger = LoggerFactory.getLogger(ElectricDriveServer.class);

    /**
     * 客户端与服务端创建新的连接
     */
    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
        ElectricDriveManager.add(session);
        System.out.println("*************新的Ele连接：" + session.getRemoteAddress() + "   *************");
        System.out.println("客户端：" + ElectricDriveManager.getFlashClients().size() + "，其它："
                + ElectricDriveManager.getOtherClients().size());
        //读写空闲时间:15秒
        session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, ElectricDriveConstant.SOCKET_CLIENT_TIMEOUT);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
    }

    /**
     * 客户端与服务端断开连接
     */
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
        ElectricDriveSession minaSession = ElectricDriveManager.get(session);
        ElectricDriveManager.remove(session);
        System.out.println("=============Ele连接断开：" + session.getRemoteAddress() + "   =============");
        System.out.println("客户端：" + ElectricDriveManager.getFlashClients().size() + "，其它："
                + ElectricDriveManager.getOtherClients().size());
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        super.sessionIdle(session, status);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
    }

    /**
     * 处理回调消息
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        super.messageReceived(session,message);
        String str = message.toString();
        logger.warn("客户端" + ((InetSocketAddress) session.getRemoteAddress()).getAddress().getHostAddress() + "连接成功！");
        session.setAttribute("type", message);
        String remoteAddress = ((InetSocketAddress) session.getRemoteAddress()).getAddress().getHostAddress();
        session.setAttribute("ip", remoteAddress);
        logger.warn("服务器收到的消息是：" + str);
//        session.write(message);
         boardcast(str);
    }

    /**
     * 向客户端发送消息后调用此方法
     */
    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
    }

    @Override
    public void inputClosed(IoSession session) throws Exception {
        super.inputClosed(session);
    }

    public static void sendMessage( Object message){

    }

    public void boardcast(String message) {

            List<ElectricDriveSession> list = ElectricDriveManager.getClients();
            list.forEach(minaSession->{
                System.out.println("广播："+message);
                minaSession.getIoSession().write(message);
            });
    }
}
