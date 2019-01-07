package com.zzqx.support.framework.mina.androidser;

import cn.hutool.http.HttpUtil;
import com.zzqx.mvc.SpringContext;
import com.zzqx.mvc.commons.CountInfo;
import com.zzqx.mvc.entity.EmployeeInformation;
import com.zzqx.mvc.entity.Message;
import com.zzqx.mvc.entity.Personnel;
import com.zzqx.mvc.service.EmployeeInformationService;
import com.zzqx.mvc.service.MessageService;
import com.zzqx.mvc.service.PersonnelService;
import com.zzqx.support.utils.DateManager;
import com.zzqx.support.utils.ServiceException;
import com.zzqx.support.utils.StringHelper;
import com.zzqx.support.utils.net.SocketDataSender;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 处理android手机终端的Main服务端
 * 
 * @author admin
 *
 */
public class AndroidMinaServer extends IoHandlerAdapter {

	private static Logger logger = LoggerFactory.getLogger(AndroidMinaServer.class);
	@Autowired
	private EmployeeInformationService employeeInformationService;

	String s = null;

	@Override
	public void messageReceived(IoSession session, Object msg) throws Exception {
		super.messageReceived(session, msg);
		String message = msg.toString();
		AndroidMinaManager.updateTime(session);
		if (StringHelper.isBlank(message))
			return;
		message = java.net.URLDecoder.decode(message, "UTF-8");

		if (message.toLowerCase().indexOf(AndroidConstant.SOCKET_DATA_WATCH_PREFIX) == 0) {// 手表发出来的通知消息
			logger.info("收到手表消息类型消息");
			String msgs = message.substring(
					message.toLowerCase().indexOf(AndroidConstant.SOCKET_DATA_WATCH_PREFIX) + 10, message.length());
			String[] msgArr = msgs.split(",");
			AndroidMinaManager.setWatchCode(session, msgArr[1]);
			if (Integer.valueOf(msgArr[0]).intValue() == AndroidConstant.MESSAGE_TYPE_PEACE_KEY.intValue()) {
				logger.info("收到" + AndroidConstant.MESSAGE_TYPE_PEACE_VALUE + "类型消息");
				logicMsgUrgent(AndroidMinaManager.get(session), Integer.valueOf(msgArr[0]), msgArr[1]);
			} else if (Integer.valueOf(msgArr[0]).intValue() == AndroidConstant.MESSAGE_TYPE_MEDICAL_KEY.intValue()) {
				logger.info("收到" + AndroidConstant.MESSAGE_TYPE_MEDICAL_VALUE + "类型消息");
				logicMsgUrgent(AndroidMinaManager.get(session), Integer.valueOf(msgArr[0]), msgArr[1]);
			} else if (Integer.valueOf(msgArr[0]).intValue() == AndroidConstant.MESSAGE_TYPE_FIREALARM_KEY.intValue()) {
				logger.info("收到" + AndroidConstant.MESSAGE_TYPE_FIREALARM_VALUE + "类型消息");
				logicMsgUrgent(AndroidMinaManager.get(session), Integer.valueOf(msgArr[0]), msgArr[1]);
			} else if (Integer.valueOf(msgArr[0]).intValue() == AndroidConstant.MESSAGE_TYPE_CALLMONITOR_KEY
					.intValue()) {
				logger.info("收到" + AndroidConstant.MESSAGE_TYPE_CALLMONITOR_VALUE + "类型消息");
				logicMsgCall(AndroidMinaManager.get(session), Integer.valueOf(msgArr[0]), msgArr[1]);
			} else if (Integer.valueOf(msgArr[0]).intValue() == AndroidConstant.MESSAGE_TYPE_CALLSECURITY_KEY
					.intValue()) {
				logger.info("收到" + AndroidConstant.MESSAGE_TYPE_CALLSECURITY_VALUE + "类型消息");
				logicMsgCall(AndroidMinaManager.get(session), Integer.valueOf(msgArr[0]), msgArr[1]);
			} else if (Integer.valueOf(msgArr[0]).intValue() == AndroidConstant.MESSAGE_TYPE_GROUPBUSINESS_KEY
					.intValue()) {
				logger.info("收到" + AndroidConstant.MESSAGE_TYPE_GROUPBUSINESS_VALUE + "类型消息");
				logicMsgCall(AndroidMinaManager.get(session), Integer.valueOf(msgArr[0]), msgArr[1]);
			} else if (Integer.valueOf(msgArr[0]).intValue() == AndroidConstant.MESSAGE_TYPE_SHIFT_KEY
					.intValue()) {
				logger.info("收到" + AndroidConstant.MESSAGE_TYPE_SHIFT_VALUE + "类型消息");
				logicMsgCall(AndroidMinaManager.get(session), Integer.valueOf(msgArr[0]), msgArr[1]);
			} else if (Integer.valueOf(msgArr[0]).intValue() == AndroidConstant.MESSAGE_TYPE_VACATION_KEY
					.intValue()) {
				logger.info("收到" + AndroidConstant.MESSAGE_TYPE_VACATION_VALUE + "类型消息");
				logicMsgCall(AndroidMinaManager.get(session), Integer.valueOf(msgArr[0]), msgArr[1]);
			}
		} else if (message.toLowerCase().indexOf(AndroidConstant.SOCKET_DATA_WATCH_CONN) == 0) {// 手表成功连接类型
			String msgs = message.substring(message.toLowerCase().indexOf(AndroidConstant.SOCKET_DATA_WATCH_CONN) + 11,
					message.length());
			logger.info("手表" + msgs + "：" + session.getRemoteAddress() + "建立连接成功！");
			if (msgs != null && !"".equals(msgs) && !"null".equals(msgs)) {
				AndroidMinaSession minaSession = AndroidMinaManager.get(session);
				minaSession.setWatchCode(msgs);
				AndroidMinaManager.add(minaSession);
				EmployeeInformation employeeInformation = new EmployeeInformation();
				employeeInformation.setWatchCode(msgs);
				employeeInformation = employeeInformationService.selectByWatchCode(employeeInformation).get(0);
				PersonnelService personnelService = (PersonnelService) SpringContext.getBean("personnelService");
//				List<Personnel> personnels = personnelService.find(Restrictions.eq("watch_code", msgs));
				try{
//					PropertiesHelper p = new PropertiesHelper("config.properties");
//					String httpCore = p.readValue("url");
//					s = HttpUtil.get(httpCore+"/api/employeeInformation/getListByWatch?watchCode="+msgs);
					s = HttpUtil.get(CountInfo.GET_PERSON_BY_WATCHCODE+"watchCode="+msgs,2000);
				}catch (Exception e){
					return ;
				}
//				Personnel personnels = new Personnel();
				if(!"".equals(s)){
					cn.hutool.json.JSONObject object = new cn.hutool.json.JSONObject(s);
//					personnels = JSONUtil.toBean(object,Personnel.class);=
					employeeInformation.setBindState(Short.parseShort(object.get("bindState").toString()));
				}
				if (employeeInformation != null ) {
					if (employeeInformation.getBindState()+0 == AndroidConstant.PERSON_BIND_STATUS_BOUND_KEY) {
						String statDay = DateManager.date2Str(DateManager.date_sdf);
						MessageService messageService = (MessageService) SpringContext.getBean("messageService");
						List<Message> messageList = messageService.find(Restrictions.eq("watch_code", msgs),
								Restrictions.eq("type", AndroidConstant.MESSAGE_TYPE_NORMAL_KEY),
								Restrictions.like("create_time", statDay,MatchMode.ANYWHERE));
						Message msgTemp = null;
						if (messageList != null && messageList.size() > 0) {
							msgTemp = messageList.get(0);
						}
						if (msgTemp == null) {
							/**
							 * 插入日常消息
							 */
							personnelService.logicMsgCall(minaSession, AndroidConstant.MESSAGE_TYPE_NORMAL_KEY, msgs,
									messageService);
						}
						SocketDataSender.sendAndroid(session, AndroidConstant.SOCKET_DATA_WATCH_CONN + "success");
						return;
					}
				}
				SocketDataSender.sendAndroid(session, AndroidConstant.SOCKET_DATA_WATCH_CONN + "unbound");
			} else {
				logger.info("手表" + msgs + "：" + session.getRemoteAddress() + "断开连接成功！");
				session.closeNow();
			}
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
		AndroidMinaSession minaSession = AndroidMinaManager.get(session);
		if (minaSession != null) {
//			HardWareHandler.removeHardwareMonitor(AndroidMinaManager.get(session).getWatchCode());
		}
		AndroidMinaManager.remove(session);
		System.out.println("=============连接断开：" + session.getRemoteAddress() + "   =============");
		System.out.println("客户端：" + AndroidMinaManager.getFlashClients().size() + "，其它："
				+ AndroidMinaManager.getOtherClients().size());
	}

	/**
	 * 客户端与服务器新创建连接
	 */
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		super.sessionCreated(session);
		AndroidMinaManager.add(session);
		System.out.println("*************新的连接：" + session.getRemoteAddress() + "   *************");
		System.out.println("客户端：" + AndroidMinaManager.getFlashClients().size() + "，其它："
				+ AndroidMinaManager.getOtherClients().size());
		session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, AndroidConstant.SOCKET_CLIENT_TIMEOUT);
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(session, cause);
		AndroidMinaManager.remove(session);
		session.closeNow();
	}

	@Override
	public void inputClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.inputClosed(session);
	}

	/**
	 * 向客户端发送消息后调用此方法
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
		// if(status == IdleStatus.BOTH_IDLE) {
		// //System.out.println("闲置");
		// System.out.println(session.getRemoteAddress()+"连接超时，服务器断开连接！");
		// AndroidMinaManager.getClients().remove(session);
		// session.closeNow();
		// }
	}

	/**
	 * 打开与客户端的连接
	 */
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionOpened(session);
	}

	/**
	 * 呼叫类型（呼叫班长、呼叫保安、集团业务、日常消息）消息函数
	 * 
	 * @param minaSession
	 *            socket Session对象
	 * @param msgType
	 *            消息类型
	 * @param watchCode
	 *            手表ID
	 */
	private void logicMsgCall(AndroidMinaSession minaSession, Integer msgType, String watchCode) {
		PersonnelService personnelService = (PersonnelService) SpringContext.getBean("personnelService");
		MessageService msgService = (MessageService) SpringContext.getBean("messageService");
		try {
			if (personnelService.logicMsgCall(minaSession, msgType, watchCode, msgService)) {
				logger.info(AndroidConstant.MESSAGE_TYPE_MAP.get(msgType) + "成功！");
			} else {
				logger.info(AndroidConstant.MESSAGE_TYPE_MAP.get(msgType) + "失败！");
			}
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 处理紧急类型（治安、医疗、火警）消息函数
	 * 
	 * @param minaSession
	 *            socket Session对象
	 * @param msgType
	 *            消息类型
	 * @param watchCode
	 *            手表ID
	 */
	private void logicMsgUrgent(AndroidMinaSession minaSession, Integer msgType, String watchCode) {

		PersonnelService personnelService = (PersonnelService) SpringContext.getBean("personnelService");
		MessageService messageService = (MessageService) SpringContext.getBean("messageService");

		List<Personnel> personnels = personnelService.find(Restrictions
				.not(Restrictions.in("work", new Object[] { AndroidConstant.PERSONNEL_STATE_VACATION_KEY })));
		try {
			if (messageService.logicMsgUrgent(minaSession, msgType, watchCode, personnelService, personnels)) {
				logger.info(AndroidConstant.MESSAGE_TYPE_MAP.get(msgType) + "成功！");
			} else {
				logger.info(AndroidConstant.MESSAGE_TYPE_MAP.get(msgType) + "失败！");
			}
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
