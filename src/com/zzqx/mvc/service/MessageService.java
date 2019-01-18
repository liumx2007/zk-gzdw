package com.zzqx.mvc.service;

import com.zzqx.mvc.entity.EmployeeInformation;
import com.zzqx.mvc.entity.Message;
import com.zzqx.support.framework.mina.androidser.AndroidMinaSession;
import com.zzqx.support.utils.ServiceException;

import java.util.List;

public interface MessageService extends BaseService<Message> {

	/**
	 * 处理紧急类型（治安、医疗、火警）消息函数
	 * @param minaSession socket Session对象
	 * @param msgType 消息类型
	 * @param watchCode 手表ID
	 */
	boolean logicMsgUrgent(AndroidMinaSession minaSession, Integer msgType, String watchCode,
			PersonnelService personnelService, List<EmployeeInformation> employeeInformationList) throws ServiceException;
}