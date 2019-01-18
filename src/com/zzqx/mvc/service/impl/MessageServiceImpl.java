package com.zzqx.mvc.service.impl;

import com.jetsum.core.orm.entity.Page;
import com.zzqx.mvc.dao.MessageDao;
import com.zzqx.mvc.entity.EmployeeInformation;
import com.zzqx.mvc.entity.Message;
import com.zzqx.mvc.entity.Personnel;
import com.zzqx.mvc.service.MessageService;
import com.zzqx.mvc.service.PersonnelService;
import com.zzqx.support.framework.mina.androidser.AndroidConstant;
import com.zzqx.support.framework.mina.androidser.AndroidMinaSession;
import com.zzqx.support.utils.ServiceException;
import com.zzqx.support.utils.StringHelper;
import com.zzqx.support.utils.net.SocketDataSender;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("messageService")
@Transactional
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageDao messageDao;

	@Override
	public void executeHql(String hql, Object... values) {
		 
	}

	@Override
	public void saveOrUpdate(Message t) {
		messageDao.saveOrUpdate(t);
		messageDao.flush();
	}

	@Override
	public Message getById(String id) {
		return messageDao.findUnique(Restrictions.eq("id", id));
	}

	@Override
	public void delete(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			messageDao.batchExecute("delete Message where id in ("+str+")");
			messageDao.flush();
		}
	}

	@Override
	public List<Message> find(Criterion... criterions) {
		// TODO Auto-generated method stub
		return messageDao.find(criterions);
	}

	@Override
	public List<Message> getAll() {
		return messageDao.getAll();
	}

	@Override
	public List<Message> get(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			return messageDao.find("from Message where id in ("+str+")");
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public Page<Message> getByPage(Map<String, Object> map, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Message> getByPage(Map<String, Object> map, int pageNo, int pageSize, String orderBy, String order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query createQuery(String hql, Object... values) {
		return messageDao.createQuery(hql, values);
	}

	@Override
	public Query createQuery(String hql, Map<String, ?> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean logicMsgUrgent(AndroidMinaSession minaSession, Integer msgType, String watchCode,
			PersonnelService personnelService, List<EmployeeInformation> employeeInformationList) throws ServiceException{
		boolean resState = false;
		try {
//			String fromWatchCode = null;
//			for (Personnel p : personnels) {
//				if (minaSession.getWatchCode().equals(p.getWatch_code())) {
//					fromWatchCode = minaSession.getWatchCode();
//					break;
//				}
//			}
//			if (fromWatchCode == null) {
//				List<Personnel> presonList = personnelService
//						.find(Restrictions.eq("watch_code", minaSession.getWatchCode()));
//				if (presonList != null && presonList.size() > 0) {
//					fromWatchCode = presonList.get(0).getWatch_code();
//				}
//			}
			Message msg = null;
			Personnel personnel = new Personnel();
//			for (Personnel p : personnels) {
			for (EmployeeInformation employeeInformation : employeeInformationList) {
				msg = new Message();
				msg.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				msg.setTitle(AndroidConstant.MESSAGE_TYPE_SHOW_MAP.get(msgType));
				msg.setContent(AndroidConstant.MESSAGE_TYPE_MAP.get(msgType));
				msg.setType(msgType);
				msg.setWatch_code(watchCode);
				msg.setEdit_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				msg.setCreator(minaSession.getWatchCode());
				msg.setWatch_code(employeeInformation.getWatchCode());
				msg.setStatu(AndroidConstant.MESSAGE_STATE_UNREAD_KEY);
				msg.setOrdertime(new Date());
				messageDao.saveOrUpdate(msg);
				personnel.setName(employeeInformation.getName());
				personnel.setWatch_code(employeeInformation.getWatchCode());
				SocketDataSender.sendWatchMsg(msgType, watchCode, personnel);
			} 
			resState = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return resState;
	}
}
