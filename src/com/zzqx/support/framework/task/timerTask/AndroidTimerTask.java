package com.zzqx.support.framework.task.timerTask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.zzqx.mvc.entity.ArrangeDate;
import com.zzqx.mvc.entity.ArrangeDetial;
import com.zzqx.mvc.entity.Message;
import com.zzqx.mvc.entity.Personnel;
import com.zzqx.mvc.entity.WorkPosition;
import com.zzqx.mvc.service.ArrangeDateService;
import com.zzqx.mvc.service.ArrangeDetialService;
import com.zzqx.mvc.service.MessageService;
import com.zzqx.mvc.service.PersonnelService;
import com.zzqx.mvc.service.WorkPositionService;
import com.zzqx.support.framework.mina.androidser.AndroidConstant;
import com.zzqx.support.framework.mina.androidser.AndroidMinaManager;
import com.zzqx.support.framework.mina.androidser.AndroidMinaSession;
import com.zzqx.support.utils.DateManager;
import com.zzqx.support.utils.net.SocketDataSender;
@Transactional
public class AndroidTimerTask {
	@Autowired
	private PersonnelService personnelService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private ArrangeDateService arrangeDateService;
	@Autowired
	private ArrangeDetialService arrangeDetialService;
	@Autowired
	private WorkPositionService workPositionService;
	
	List<Personnel> allPersons;
	
	
	public void sendAutoMessage() throws ParseException {
		allPersons = personnelService.getAll();
		clearWork(allPersons);
		List<AndroidMinaSession> sessions = AndroidMinaManager.getClients();
		Calendar now = Calendar.getInstance();
		String dateString = now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1)+"-"+now.get(Calendar.DAY_OF_MONTH);
		Query datesQuery = arrangeDateService.createQuery("from ArrangeDate d where d.arrange_date=?", new SimpleDateFormat("yyyy-MM-dd").parse(dateString));
		List<ArrangeDate> arrangeDates = datesQuery.list();
		if(!arrangeDates.isEmpty()) {
			Query detialQuery = arrangeDetialService.createQuery("from ArrangeDetial d where d.arrange_date_id.id=?", arrangeDates.get(0).getId());
			List<ArrangeDetial> arrangeDetials = detialQuery.list();
			if(!arrangeDetials.isEmpty()) {
				System.out.println(dateString+"排班信息：");
				for(Personnel perTemp:allPersons){
					WorkPosition wp = getWork(perTemp, arrangeDetials);
					if(wp != null) {
						System.out.println(perTemp.getName()+"-"+wp.getPosition_name());
					}
					//获取今天岗位
					perTemp.setMy_work(wp);
					//查询员工当天是否已经有日常信息
					List<Message> messageList = messageService.find(Restrictions.eq("watch_code", perTemp.getWatch_code()),
							Restrictions.eq("type", AndroidConstant.MESSAGE_TYPE_NORMAL_KEY),
							Restrictions.ilike("create_time", DateManager.date2Str(DateManager.date_sdf),MatchMode.ANYWHERE));
					Message msgTemp = null;
					if (messageList != null && messageList.size() > 0) {
						msgTemp = messageList.get(0);
					}
					if (msgTemp == null) {
						//查询是否已经连接服务器
						for(AndroidMinaSession sTemp:sessions){
							if(sTemp!=null){
								if(sTemp.getWatchCode().equals(perTemp.getWatch_code())){
									
									/**
									 * 插入日常消息
									 */
									personnelService.logicMsgCall(sTemp, AndroidConstant.MESSAGE_TYPE_NORMAL_KEY, perTemp.getWatch_code(),
											messageService);
									SocketDataSender.sendAndroid(sTemp.getIoSession(), "AutoMessage");
								}
							}
						}
						
					}
				}
			}
		}
	}
	/**
	 * 获取今日岗位
	 * @param perTemp 
	 * @throws ParseException 
	 */
	@SuppressWarnings("unchecked")
	public WorkPosition getWork(Personnel perTemp, List<ArrangeDetial> list) throws ParseException {
		if(perTemp == null || list == null) return null;
		for(ArrangeDetial ad :list){
			if(ad.getPerson_id().equals(perTemp.getId()) || ad.getPerson_id().contains(perTemp.getId())){
				return workPositionService.getById(ad.getPosition());
			}
		}
		return null;
	}
	/**
	 * 清空岗位
	 */
	public void clearWork(List<Personnel> list) {
		for(Personnel perTemp:list){
			perTemp.setMy_work(null);
			perTemp.setWork_status(AndroidConstant.PERSONNEL_STATE_FREE_KEY);
			personnelService.saveOrUpdate(perTemp);
		}
	}
}