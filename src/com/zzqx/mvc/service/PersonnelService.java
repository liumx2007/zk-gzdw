package com.zzqx.mvc.service;

import com.zzqx.mvc.entity.Personnel;
import com.zzqx.support.framework.mina.androidser.AndroidMinaSession;
import com.zzqx.support.utils.ServiceException;

public interface PersonnelService extends BaseService<Personnel> {

	/**
	 * 呼叫类型（呼叫班长、呼叫保安、集团业务、日常消息）消息函数
	 * @param minaSession socket Session对象
	 * @param msgType 消息类型
	 * @param watchCode 手表ID
	 */
	boolean logicMsgCall(AndroidMinaSession minaSession, Integer msgType, String watchCode,MessageService msgService) throws ServiceException;

	Personnel getPersonnelWatchCode(String WatchCode);
}