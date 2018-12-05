package com.zzqx.mvc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzqx.mvc.entity.ArrangeDate;
import com.zzqx.mvc.entity.ArrangeDetial;
import com.zzqx.mvc.entity.Message;
import com.zzqx.mvc.entity.Personnel;
import com.zzqx.mvc.entity.WorkPosition;
import com.zzqx.mvc.javabean.ReturnMessage;
import com.zzqx.mvc.service.ArrangeDateService;
import com.zzqx.mvc.service.ArrangeDetialService;
import com.zzqx.mvc.service.MessageService;
import com.zzqx.mvc.service.PersonnelService;
import com.zzqx.mvc.service.WorkPositionService;
import com.zzqx.support.framework.mina.androidser.AndroidConstant;
import com.zzqx.support.framework.mina.androidser.AndroidMinaManager;
import com.zzqx.support.framework.mina.androidser.AndroidMinaSession;
import com.zzqx.support.utils.DateManager;
import com.zzqx.support.utils.StringHelper;
import com.zzqx.support.utils.net.SocketDataSender;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;


@SuppressWarnings("unchecked")
@Controller
@RequestMapping(value = "/arrange")
public class ArrangeController extends BaseController {

	@Autowired
	private ArrangeDateService arrangeDateService;
	@Autowired
	private PersonnelService personnelService;
	@Autowired
	private ArrangeDetialService arrangeDetialService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private WorkPositionService workPositionService;
	
	List<AndroidMinaSession> sessions = AndroidMinaManager.getClients();
	
	@ResponseBody
	@RequestMapping("getAllArrangeDate")
	public String getAll(String getDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date aDate = sdf.parse(getDate);
		
		List<ArrangeDate> dates;
		Query query = arrangeDateService.createQuery("from ArrangeDate d where 1=? and d.arrange_date>=? order by d.arrange_date asc", 1, aDate);
		query.setFirstResult(0);
		query.setMaxResults(7);
		dates = query.list();
		while(dates.size()<7){
			addADate();
			Query wquery = arrangeDateService.createQuery("from ArrangeDate d where 1=? and d.arrange_date>=? order by d.arrange_date asc", 1, aDate);
			wquery.setFirstResult(0);
			wquery.setMaxResults(7);
			dates = query.list();
		}
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONArray jsonArray = JSONArray.fromObject(dates, jsonConfig);
		return jsonArray.toString();
	}
	
	@ResponseBody
	@RequestMapping("addADate")
	public String addADate() {
		Query query = arrangeDateService.createQuery("from ArrangeDate d where 1=? order by d.arrange_date desc", 1);
		List<ArrangeDate> list = query.list();
		if(list.size() > 0){
			Calendar cal = Calendar.getInstance();
			cal.setTime(list.get(0).getArrange_date());
			for(int i=0;i<10;i++){
				cal.add(Calendar.DATE, 1);
				ArrangeDate add = new ArrangeDate();
				add.setArrange_date(cal.getTime());
				arrangeDateService.saveOrUpdate(add);
			}
			return "添加成功";
		}else{
			ArrangeDate add = new ArrangeDate();
			add.setArrange_date(new Date());
			arrangeDateService.saveOrUpdate(add);
			return "添加成功";
		}
		
	}
	@ResponseBody
	@RequestMapping("getAllArrangeDetial")
	public String getAllArrangeDetial(HttpServletRequest request) {
		List<ArrangeDetial> list = arrangeDetialService.getAll();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		return jsonArray.toString();
	}
	@ResponseBody
	@RequestMapping("getArrangeDetialByDate")
	public String getArrangeDetialByDate(HttpServletRequest request) {
		String arrangeDateId = request.getParameter("arrangeDateId");
		Query detialQuery = arrangeDetialService.createQuery("from ArrangeDetial d where d.arrange_date_id.id=? order by d.position asc", arrangeDateId);
		List<ArrangeDetial> list = detialQuery.list();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		return jsonArray.toString();
	}
	@ResponseBody
	@RequestMapping("addArrangeDetial")
	public String addArrangeDetial(HttpServletRequest request, HttpServletResponse response){
		ArrangeDetial arrangeDetial =new ArrangeDetial();
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "操作成功！");
		arrangeDetial.setId(null);
		arrangeDetial.setArrange_date_id(arrangeDateService.getById(request.getParameter("arrange_date_id")));
		arrangeDetial.setPerson_id(request.getParameter("person_id"));
		arrangeDetial.setPosition(request.getParameter("position"));
		arrangeDetial.setCreate_time(new Date());
		arrangeDetial.setPart(Integer.parseInt(request.getParameter("part")));
		arrangeDetial.setRemark(request.getParameter("remark"));
		arrangeDetialService.saveOrUpdate(arrangeDetial);
		List<Personnel> persons = personnelService.get(request.getParameter("person_id").split(","));
		List<String> watchIds = new ArrayList<String>();
		for(Personnel p : persons){
			watchIds.add(p.getWatch_code());
		}
		sessions = AndroidMinaManager.getClients();
		for(AndroidMinaSession as:sessions){
			for(String watchCode : watchIds){
				if(as != null && as.getWatchCode().equals(watchCode)){
					AndroidMinaManager.remove(as.getIoSession());
				}
			}
		}
		return message.toString();
	}

	@ResponseBody
	@RequestMapping("deleteDetial")
	public String delete(String id, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "删除成功！");
		if (id == null) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("删除失败！");
			return message.toString();
		}
		String[] ids = id.split(",");
		//删除排班同时删除相应人员日常信息
		List<ArrangeDetial> detials = arrangeDetialService.find(Restrictions.in("id", ids));
		List<String> personIds = new ArrayList<String>();
		List<String> watchIds = new ArrayList<String>();
		for(ArrangeDetial detialTemp : detials){
			String[] pids  = detialTemp.getPerson_id().split(",");
			for(int i=0;i<pids.length;i++){
				personIds.add(pids[i]);
			}
		}
//		List<Personnel> persons = personnelService.find(Restrictions.in("id", personIds.toArray()));
		String idString = personIds.stream().map(pid->"'"+pid+"'").collect(Collectors.joining(","));
		List<Personnel> persons = personnelService.createQuery("from Personnel where id in ("+idString+")").list();
		for(Personnel p : persons){
			watchIds.add(p.getWatch_code());
			p.setMy_work(null);
			personnelService.saveOrUpdate(p);
		}
		List<Message> msgs = messageService.find(Restrictions.in("watch_code", watchIds),
							Restrictions.ilike("create_time", DateManager.date2Str(DateManager.date_sdf),MatchMode.ANYWHERE),
							Restrictions.eq("type", AndroidConstant.MESSAGE_TYPE_NORMAL_KEY));
		List<String> msgIds = new ArrayList<String>();
		for(Message m : msgs){
			msgIds.add(m.getId());
		}
		String[] msgArray = new String[msgIds.size()];
		messageService.delete(msgIds.toArray(msgArray));
		arrangeDetialService.delete(ids);
		sessions = AndroidMinaManager.getClients();
		for(AndroidMinaSession as:sessions){
			for(String watchCode : watchIds){
				if(as!=null && as.getWatchCode().equals(watchCode)){
					SocketDataSender.sendAndroid(as.getIoSession(), "AutoMessage");
				}
			}
		}
		return message.toString();
	}
	
	//复制排班
	@ResponseBody
	@RequestMapping("copy")
	public String copy(String arrangeDateId, String source, HttpServletRequest request) throws ParseException {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "复制成功！");
		if (StringHelper.isBlank(arrangeDateId) || StringHelper.isBlank(source)) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("复制失败！");
			return message.toString();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date sourceDateString = sdf.parse(source);
		List<ArrangeDate> arrangeDates = arrangeDateService.find(Restrictions.eq("arrange_date", sourceDateString));
		if(arrangeDates.isEmpty()) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("复制失败，"+source+"无排班信息！");
			return message.toString();
		}
		ArrangeDate arrangeDate = arrangeDateService.getById(arrangeDateId);
		ArrangeDate sourceDate = arrangeDates.get(0);
		List<ArrangeDetial> todayDetial = arrangeDetialService.find(Restrictions.eq("arrange_date_id", arrangeDate));
		List<ArrangeDetial> sourceDetial = arrangeDetialService.find(Restrictions.eq("arrange_date_id", sourceDate));
		List<ArrangeDetial> detials = new ArrayList<>();//需要删除的
		List<ArrangeDetial> newDetials = new ArrayList<>();//需要添加的
		for(ArrangeDetial ad1 : todayDetial) {
			boolean b = false;
			for(ArrangeDetial ad2 : sourceDetial) {
				if(ad1.getPerson_id().equals(ad2.getPerson_id()) && ad1.getPosition().equals(ad2.getPosition())) {
					b = true;
					break;
				}
			}
			if(!b) {
				detials.add(ad1);
			}
		}
		for(ArrangeDetial ad1 : sourceDetial) {
			boolean b = false;
			for(ArrangeDetial ad2 : todayDetial) {
				if(ad1.getPerson_id().equals(ad2.getPerson_id()) && ad1.getPosition().equals(ad2.getPosition())) {
					b = true;
					break;
				}
			}
			if(!b) {
				newDetials.add(ad1);
			}
		}
//		String[] ids = id.split(",");
		String[] ids = detials.stream().map(ArrangeDetial::getId).collect(Collectors.toList()).toArray(new String[0]);
		//删除排班同时删除相应人员日常信息
//		List<ArrangeDetial> detials = arrangeDetialService.find(Restrictions.in("id", ids));
		List<String> personIds = new ArrayList<String>();
		List<String> watchIds = new ArrayList<String>();
		for(ArrangeDetial detialTemp : detials){
			String[] pids  = detialTemp.getPerson_id().split(",");
			for(int i=0;i<pids.length;i++){
				personIds.add(pids[i]);
			}
		}
//		List<Personnel> persons = personnelService.find(Restrictions.in("id", personIds.toArray()));
		String idString = personIds.stream().map(pid->"'"+pid+"'").collect(Collectors.joining(","));
		sessions = AndroidMinaManager.getClients();
		if(StringHelper.isNotBlank(idString)) {
			List<Personnel>	persons = personnelService.createQuery("from Personnel where id in ("+idString+")").list();
			for(Personnel p : persons){
				watchIds.add(p.getWatch_code());
				p.setMy_work(null);
				personnelService.saveOrUpdate(p);
			}
			List<Message> msgs = messageService.find(Restrictions.in("watch_code", watchIds),
								Restrictions.ilike("create_time", DateManager.date2Str(DateManager.date_sdf),MatchMode.ANYWHERE),
								Restrictions.eq("type", AndroidConstant.MESSAGE_TYPE_NORMAL_KEY));
			List<String> msgIds = new ArrayList<String>();
			for(Message m : msgs){
				msgIds.add(m.getId());
			}
			String[] msgArray = new String[msgIds.size()];
			messageService.delete(msgIds.toArray(msgArray));
			arrangeDetialService.delete(ids);
			for(AndroidMinaSession as:sessions){
				for(String watchCode : watchIds){
					if(as!=null && as.getWatchCode().equals(watchCode)){
						SocketDataSender.sendAndroid(as.getIoSession(), "AutoMessage");
					}
				}
			}
		}
		for(ArrangeDetial arrangeDetial : newDetials) {
			ArrangeDetial newArrangeDetial = new ArrangeDetial();
			newArrangeDetial.setArrange_date_id(arrangeDate);
			newArrangeDetial.setPerson_id(arrangeDetial.getPerson_id());
			newArrangeDetial.setPosition(arrangeDetial.getPosition());
			newArrangeDetial.setCreate_time(new Date());
			newArrangeDetial.setPart(arrangeDetial.getPart());
			newArrangeDetial.setRemark(arrangeDetial.getRemark());
			arrangeDetialService.saveOrUpdate(newArrangeDetial);
			List<Personnel> personList = personnelService.get(arrangeDetial.getPerson_id().split(","));
			List<String> watchIdList = new ArrayList<String>();
			for(Personnel p : personList){
				watchIds.add(p.getWatch_code());
			}
			for(AndroidMinaSession as:sessions){
				for(String watchCode : watchIdList){
					if(as != null && as.getWatchCode().equals(watchCode)){
						AndroidMinaManager.remove(as.getIoSession());
					}
				}
			}
		}
		return message.toString();
	}
	
	@ResponseBody
	@RequestMapping("editDetial")
	public String editDetial(HttpServletRequest request) {
		String id = request.getParameter("id");
		ArrangeDetial arrangeDetial = arrangeDetialService.getById(id);
		arrangeDetial.setPerson_id(request.getParameter("person_id"));
		arrangeDetial.setPosition(request.getParameter("position"));
		arrangeDetial.setUpdata_time(new Date());
		arrangeDetialService.saveOrUpdate(arrangeDetial);
		Personnel personnel = personnelService.getById(request.getParameter("person_id"));
		String watchCode = null;
		if(personnel!=null){
			 watchCode = personnel.getWatch_code();
		}
		
		if(watchCode!=null){
			List<Message> msgList = messageService.find(Restrictions.eq("watch_code", watchCode),
					Restrictions.ilike("create_time", DateManager.date2Str(DateManager.date_sdf),MatchMode.ANYWHERE),
					Restrictions.eq("type", AndroidConstant.MESSAGE_TYPE_NORMAL_KEY));
			Message msg = null;
			if(msgList!=null&&msgList.size()>0){
				msg = msgList.get(0);
				messageService.delete(new String[]{msg.getId()});
				List<AndroidMinaSession> amsList = AndroidMinaManager.getListByWatchCode(watchCode);
				for(AndroidMinaSession ams : amsList){
					if (ams != null && ams.getIoSession() != null) {
						AndroidMinaManager.remove(ams.getIoSession());
					}
				}
			}
		}
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "操作成功！");
		
		return message.toString();
	}
	/**
	 * 获取未排班人员
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getNotArrangePersons")
	public String getNotArrangePersons(HttpServletRequest request) {
		String arrangeDateId = request.getParameter("arrangeDateId");
		Query detialQuery = arrangeDetialService.createQuery("from ArrangeDetial d where d.arrange_date_id.id=? order by d.position asc", arrangeDateId);
		List<ArrangeDetial> list = detialQuery.list();
		List<String> personIds = new ArrayList<String>();
		for(ArrangeDetial ad : list){
			String[] ids = ad.getPerson_id().split(",");
			for(int i = 0;i<ids.length;i++){
				personIds.add(ids[i]);
			}
		}
		String selfPersonId = request.getParameter("selfPersonId");
		String[] selfPersonIds = null;
		JSONArray jsonArray = null;
		if(selfPersonId != null && !"".equals(selfPersonId)){
			selfPersonIds = selfPersonId.split(",");
			for(int i = 0;i<selfPersonIds.length;i++){
				personIds.remove(selfPersonIds[i]);
			}
			if(personIds.size()<1){
				personIds.add("XOXOXOXOXO");
			}
			String idString = personIds.stream().map(pid->"'"+pid+"'").collect(Collectors.joining(","));
			List<Personnel> personnels = personnelService.createQuery("from Personnel where id not in ("+idString+")").list();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
			jsonArray = JSONArray.fromObject(personnels, jsonConfig);
		}else{
			return null;
		}
		return jsonArray.toString();
	}
	/**
	 * 获取未排班岗位
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getNotArrangePositions")
	public String getNotArrangePositions(HttpServletRequest request) {
		String arrangeDateId = request.getParameter("arrangeDateId");
		Query detialQuery = arrangeDetialService.createQuery("from ArrangeDetial d where d.arrange_date_id.id=? order by d.position asc", arrangeDateId);
		List<ArrangeDetial> list = detialQuery.list();
		List<String> positionIds = new ArrayList<String>();
		for(ArrangeDetial ad : list){
			positionIds.add(ad.getPosition());
		}
		String selfPosition = request.getParameter("selfPosition");
		JSONArray jsonArray = null;
		if(selfPosition != null && !"".equals(selfPosition)){
			positionIds.remove(selfPosition);
			if(positionIds.size()<1){
				positionIds.add("9898999");
			}
		String ids = positionIds.stream().map(id->"'"+id+"'").collect(Collectors.joining(","));
		List<WorkPosition> workPositions = workPositionService.createQuery("from WorkPosition where id not in ("+ids+")").list();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonArray = JSONArray.fromObject(workPositions, jsonConfig);
		}else{
			return null;
		}
		return jsonArray.toString();
	}
	/**
	 * 验证重复
	 * @param arrangeDateId
	 * @return
	 */
	public String checkRepeat(String arrangeDateId,String getPerson,String getPosition){
		
		Query detialQuery = arrangeDetialService.createQuery("from ArrangeDetial d where d.arrange_date_id.id=? order by d.position asc", arrangeDateId);
		List<ArrangeDetial> list = detialQuery.list();
		if(list != null && list.size()>0){
			List<String> positionIds = new ArrayList<String>();
			List<String> personIds = new ArrayList<String>();
			for(ArrangeDetial ad : list){
				String[] ids = ad.getPerson_id().split(",");
				for(int i = 0;i<ids.length;i++){
					personIds.add(ids[i]);
				}
				positionIds.add(ad.getPosition());
			}
			for(String position : positionIds){
				if(position.equals(getPosition)){
					return "排班岗位重复";
				}
			}
			for(String person:personIds){
				if(getPerson.contains(person)){
					return "排班人员重复";
				}
			}
			
		}
		return null;
	}
}
