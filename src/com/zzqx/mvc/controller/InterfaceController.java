package com.zzqx.mvc.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.zzqx.Global;
import com.zzqx.mvc.annotation.OpenAccess;
import com.zzqx.mvc.commons.CountInfo;
import com.zzqx.mvc.dao.BhSchduMapper;
import com.zzqx.mvc.dao.EmployeeInformationMapper;
import com.zzqx.mvc.dao.EmployeeJobsMapper;
import com.zzqx.mvc.entity.*;
import com.zzqx.mvc.javabean.NewsContent;
import com.zzqx.mvc.javabean.NewsImage;
import com.zzqx.mvc.javabean.NewsInfo;
import com.zzqx.mvc.javabean.NewsWords;
import com.zzqx.mvc.service.*;
import com.zzqx.mvc.vo.PersonVo;
import com.zzqx.support.framework.mina.androidser.AndroidConstant;
import com.zzqx.support.framework.mina.androidser.AndroidMinaManager;
import com.zzqx.support.framework.mina.androidser.AndroidMinaSession;
import com.zzqx.support.utils.CommonUtil;
import com.zzqx.support.utils.DateManager;
import com.zzqx.support.utils.StringHelper;
import com.zzqx.support.utils.file.PropertiesHelper;
import com.zzqx.support.utils.net.SocketDataSender;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/interface")
public class InterfaceController extends BaseController {

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
	@Autowired
	private EmployeeInformationMapper employeeInformationMapper;
	@Autowired
	private EmployeeJobsMapper employeeJobsMapper;
	@Autowired
	private BhSchduMapper bhSchduMapper;
	Map<String, WorkPosition> goingOnduty = new HashMap<String, WorkPosition>();


	
	List<AndroidMinaSession> sessions = AndroidMinaManager.getClients();
	@OpenAccess
	@ResponseBody
	@RequestMapping("weather/get")
	public String weatherGet(String type) {
		String t = StringHelper.isBlank(type)?"f":type.trim();
		if("n".equalsIgnoreCase(t)) {
			return JSONObject.fromObject(Global.weatherToday).toString();
		} else if("h".equalsIgnoreCase(type)) {
			return JSONArray.fromObject(Global.weatherHistory).toString();
		} else {
			return JSONArray.fromObject(Global.weatherFuture).toString();
		}
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("news/roll")
	public String newsRoll() {
		return JSONArray.fromObject(Global.news).toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("news/detail")
	public String newsDetail(String url) {
		NewsInfo newsInfo = null;
		for(NewsInfo ni : Global.news) {
			if(ni.getUrl().equalsIgnoreCase(url)) {
				newsInfo = ni;
				break;
			}
		}
		if(newsInfo == null || StringHelper.isBlank(url)) {
			return JSONObject.fromObject(null).toString();
		}
		if(newsInfo.getContent() != null) {
			return JSONObject.fromObject(newsInfo).toString();
		}
		try {
			List<NewsContent> newsContents = new ArrayList<>();
			Document document = Jsoup.connect(url).get();
			String subTitle = "";
			String source = "";
			Element content = document.getElementById("endText");
			Elements paragraphs = content.getElementsByTag("p");
			for(Element paragraph : paragraphs) {
				if(StringHelper.isBlank(paragraph.html())) continue;
				//获取副标题开始
				if(paragraph.hasClass("otitle")) {
					subTitle = paragraph.ownText();
				} else {
					//获取副标题结束
					if(paragraph.hasClass("f_center")) {
						//获取图片开始
						Elements imageElements = paragraph.getElementsByTag("img");
						for(Element imageElement : imageElements) {
							NewsContent newContent = new NewsContent();
							String alt = imageElement.attr("alt");
							String src = imageElement.attr("src");
							NewsImage newsImage = new NewsImage();
							newsImage.setAlt(alt);
							newsImage.setSrc(src);
							newContent.setImage(newsImage);
							newContent.setType("image");
							newsContents.add(newContent);
						}
						//获取图片结束
					} else {
						//获取段落文字开始
						NewsContent newContent = new NewsContent();
						NewsWords newWords = new NewsWords();
						Elements bElements = paragraph.getElementsByTag("b");
						if(bElements.size() > 0) {
							Element bElement = bElements.get(0);
							if(StringHelper.isNotBlank(bElement.ownText())) {
								newWords.setBold(true);
								newWords.setWords(bElement.ownText());
							}
						} else {
							newWords.setBold(false);
							newWords.setWords(paragraph.ownText());
						}
						newContent.setWords(newWords);
						newContent.setType("text");
						newsContents.add(newContent);
						//获取段落文字结束
					}
				}
			}
			//获取来源开始
			Elements sourceElements = content.getElementsByClass("ep-source cDGray");
			if(sourceElements.size() > 0) {
				source = sourceElements.get(0).ownText();
			}
			//获取来源结束
			newsInfo.setSubTitle(subTitle);
			newsInfo.setSource(source);
			newsInfo.setContent(newsContents);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		return JSONObject.fromObject(newsInfo).toString();
	}
	/**
	 * 确认上岗
	 * @param request
	 */
	@OpenAccess
	@ResponseBody
	@RequestMapping("onduty/confirm")
	public String ondutyConfirm(HttpServletRequest request) {
		String watchCode = request.getParameter("watchCode");
//		Personnel person = personnelService.find(Restrictions.eq("watch_code", watchCode)).get(0);
//		person.setMy_work(getWork(person));
//		personnelService.saveOrUpdate(person);
		try{
			String getData = HttpUtil.get(CountInfo.GET_JOB_BY_WATCHCODE+watchCode,2000);
		}catch (Exception e){
			System.out.print("--------------------连接中台超时。。。。。。。。。。。。。。。。。");
			EmployeeInformationExample ex = new EmployeeInformationExample();
			EmployeeInformationExample.Criteria criteria = ex.createCriteria();
			criteria.andWatchCodeEqualTo(watchCode);
			List<EmployeeInformation> empList = employeeInformationMapper.selectByExample(ex);
			EmployeeInformation emp = new EmployeeInformation();
			if(empList != null && empList.size()>0){
				emp = empList.get(0);
			}
			emp.setMyWork(getWork_new(emp).getJobsName());
			employeeInformationMapper.updateByPrimaryKey(emp);
		}

		return "确认到岗";
	}
	/**
	 * 获取今日岗位
	 * @param perTemp 
	 */
	public WorkPosition getWork(Personnel perTemp) {
		//根据排班获取今天的岗位
		Query datesQuery = arrangeDateService.createQuery("from ArrangeDate d where date_format(d.arrange_date,'%Y-%m-%d')=?", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		ArrangeDate Date = null;
		if(datesQuery.list().size()>0){
			Date = (ArrangeDate) datesQuery.list().get(0);
		}else{
			return perTemp.getMy_work();
		}
		Query detialQuery = arrangeDetialService.createQuery("from ArrangeDetial d where d.arrange_date_id.id=?", Date.getId());
		List<ArrangeDetial> list = detialQuery.list();
		for(ArrangeDetial ad :list){
			if(ad.getPerson_id().equals(perTemp.getId()) || ad.getPerson_id().contains(perTemp.getId())){
				return workPositionService.getById(ad.getPosition());
			}
		}
		return perTemp.getMy_work();
	}
	/**
	 * 获取今日岗位
	 * @param -perTemp
	 */
	public EmployeeJobs getWork_new(EmployeeInformation employeeInformation) {
		BhSchduExample bhSchduExample = new BhSchduExample();
		BhSchduExample.Criteria criteria = bhSchduExample.createCriteria();
		BigDecimal bigDecimal = new BigDecimal(employeeInformation.getId());
		criteria.andEmployeeIdEqualTo(bigDecimal);
		BhSchdu bhSchdu = bhSchduMapper.selectByExample(bhSchduExample).get(0);
		EmployeeJobsExample employeeJobsExample = new EmployeeJobsExample();
		EmployeeJobsExample.Criteria jobCriteria = employeeJobsExample.createCriteria();
		BigDecimal jobId = new BigDecimal(bhSchdu.getJobsId());
		jobCriteria.andIdEqualTo(jobId);
		EmployeeJobs employeeJobs = employeeJobsMapper.selectByExample(employeeJobsExample).get(0);
		return employeeJobs;
	}
	/**
	 *自动调度
	 * @param request(toWhere)
	 */
	@SuppressWarnings("unchecked")
	@OpenAccess
	@ResponseBody
	@RequestMapping("autoOnduty")
	public void autoOnduty(HttpServletRequest request) {
		String p = request.getParameter("position");
		PropertiesHelper props = new PropertiesHelper("config");
		List<String> autoLine = props.readList("autoLine", ",");
		String words = props.readValueTrim("auto.onduty."+p);
		List<WorkPosition> wps = workPositionService.get(props.readValueTrim("autoLine").split(","));
		List<Personnel> allPersons = personnelService.find(Restrictions.in("my_work", wps));
		//自动调度的对象
		Personnel Per = null;
		Personnel zz = null;
		for(String workId:autoLine){
			for(Personnel perTemp:allPersons){
				if(perTemp.getMy_work() != null && perTemp.getMy_work().getId().equals(workId)){
					if(perTemp.getWork_status()==AndroidConstant.PERSONNEL_STATE_FREE_KEY){
						Per = perTemp;
						break;
					}
				}
				if(zz == null && perTemp.getMy_work() != null && perTemp.getMy_work().getId().equals("11") && perTemp.getWork_status() != AndroidConstant.PERSONNEL_STATE_TEMP_KEY) {
					zz = perTemp;
				}
			}
			if(Per != null) {
				break;
			}
		}
		if(Per == null){
			Per = zz;
		}
		if(Per != null) {
			Message msg = new Message();
			String content = words;
			msg.setContent(content);
			msg.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			msg.setCreator("admin");
			msg.setStatu(AndroidConstant.MESSAGE_STATE_UNREAD_KEY);
			msg.setTitle("临时调度信息");
			msg.setType(AndroidConstant.MESSAGE_TYPE_CALLMONITOR_KEY);
			msg.setWatch_code(Per.getWatch_code());
			msg.setOrdertime(new Date());
			messageService.saveOrUpdate(msg);
			SocketDataSender.sendWatchMsg(AndroidConstant.MESSAGE_TYPE_CALLMONITOR_KEY, Per.getWatch_code(), Per);
			System.out.println(Per.getName() + "(" + words +")");
		}
		
	}
	/**
	 * 手动发送调度信息
	 * @param request (personId position)
	 */
	@OpenAccess
	@ResponseBody
	@RequestMapping("interface_onduty")
	public void dispatchTemp(HttpServletRequest request) {
		WorkPosition work = null;
//		Personnel person = new Personnel();
		Message msg = new Message();
		String watchCode = request.getParameter("watchCode");

//		String personId = request.getParameter("personId");
//		if(!personId.isEmpty() && personId != null){
//			person = personnelService.getById(personId);
//		}
//		PropertiesHelper p = new PropertiesHelper("config.properties");
//		String httpCore = p.readValue("url");
		String s = null;
		Personnel person = new Personnel();
		try{
			s = HttpUtil.get(CountInfo.GET_PERSON_BY_WATCHCODE+"watchCode="+watchCode,2000);
			if(!"".equals(s)){
				cn.hutool.json.JSONObject object = new cn.hutool.json.JSONObject(s);
				String name = object.get("name").toString();
				person.setName(name);
				person.setWatch_code(watchCode);
			}
		}catch (Exception e){
			System.out.print("--------------------连接中台超时。。。。。。。。。。。。。。。。。");
			EmployeeInformationExample employeeInformationExample = new EmployeeInformationExample();
			EmployeeInformationExample.Criteria criteria = employeeInformationExample.createCriteria();
			criteria.andWatchCodeEqualTo(watchCode);
			EmployeeInformation employeeInformation = employeeInformationMapper.selectByExample(employeeInformationExample).get(0);
			person.setName(employeeInformation.getName());
			person.setWatch_code(employeeInformation.getWatchCode());
		}
		String position = request.getParameter("position");
		if(position != null && !position.isEmpty() ){
			//查询岗位
			String wPosition = "";
			try {
				s =  HttpUtil.get(CountInfo.JOB_BY_ID+position,2000);
				cn.hutool.json.JSONObject object = new cn.hutool.json.JSONObject(s);
				wPosition = object.get("jobsName").toString();
			}catch (Exception e){
				System.out.print("--------------------连接中台超时。。。。。。。。。。。。。。。。。");
				EmployeeJobsExample employeeJobsExample = new EmployeeJobsExample();
				EmployeeJobsExample.Criteria criteria = employeeJobsExample.createCriteria();
				BigDecimal jobId = new BigDecimal(position);
				criteria.andIdEqualTo(jobId);
				EmployeeJobs employeeJobs = employeeJobsMapper.selectByExample(employeeJobsExample).get(0);
				wPosition = employeeJobs.getJobsName();
			}

			msg.setContent("请" + person.getName() + "到" + wPosition);
			msg.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			msg.setCreator("admin");
			msg.setStatu(AndroidConstant.MESSAGE_STATE_UNREAD_KEY);
			msg.setTitle("调度信息");
			msg.setType(AndroidConstant.MESSAGE_TYPE_CALLMONITOR_KEY);
			msg.setWatch_code(person.getWatch_code());
			msg.setOrdertime(new Date());
		}
		messageService.saveOrUpdate(msg);
		SocketDataSender.sendWatchMsg(AndroidConstant.MESSAGE_TYPE_CALLMONITOR_KEY, person.getWatch_code(), person);
//		System.out.println(person.getName() + "调度到" + work.getPosition_name() );
//		goingOnduty.put(person.getWatch_code(), work);
	}
	/**
	 * 确认调度
	 * @param request
	 */
	@OpenAccess
	@ResponseBody
	@RequestMapping("confirmGoOnDuty")
	public void confirmGoOnDuty(HttpServletRequest request) {
		String watchCode = request.getParameter("watchCode");
//		String ondutyType = request.getParameter("ondutyType");
//		Personnel person = personnelService.find(Restrictions.eq("watch_code", watchCode)).get(0);
		PropertiesHelper p = new PropertiesHelper("config.properties");
		String httpCore = p.readValue("url");
		String s = null;
		try{
			s = HttpUtil.get(CountInfo.GET_PERSON_BY_WATCHCODE+"watchCode="+watchCode,2000);
		}catch (Exception e){
			System.out.print("--------------------连接中台超时。。。。。。。。。。。。。。。。。");

		}
		PersonVo personnels = null;
		if(!"".equals(s)){
			cn.hutool.json.JSONObject object = new cn.hutool.json.JSONObject(s);
			personnels = JSONUtil.toBean(object,PersonVo.class);
		}
		Personnel person = new Personnel(personnels);

		if(goingOnduty.get(watchCode)!=null){
			person.setMy_work(goingOnduty.get(watchCode));
			personnelService.saveOrUpdate(person);
			goingOnduty.remove(watchCode);
			//永久调度 同时更改日常信息
			List<Integer> status = new ArrayList<Integer>();
			status.add(AndroidConstant.MESSAGE_STATE_READ_KEY);
			status.add(AndroidConstant.MESSAGE_STATE_UNREAD_KEY);
			List<Message> list = messageService.find(Restrictions.eq("watch_code",watchCode),
								Restrictions.in("statu", status),
								Restrictions.ilike("create_time", DateManager.date2Str(DateManager.date_sdf),MatchMode.ANYWHERE),
								Restrictions.eq("type", AndroidConstant.MESSAGE_TYPE_NORMAL_KEY));
			Message normalMsg = list.get(0);
			normalMsg.setContent(person.getName() + "工号" + person.getJob_num() + "今日工作岗位为" + person.getMy_work().getPosition_name());
			normalMsg.setStatu(AndroidConstant.MESSAGE_STATE_UNREAD_KEY);
//			normalMsg.setOrdertime(new Date());
			messageService.saveOrUpdate(normalMsg);
			//永久调度更改排班岗位
			Query datesQuery = arrangeDateService.createQuery("from ArrangeDate d where date_format(d.arrange_date,'%Y-%m-%d')=?", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			ArrangeDate Date = null;
			if(datesQuery.list().size()>0){
				Date = (ArrangeDate) datesQuery.list().get(0);
			}else{
				return;
			}
			Query detialQuery = arrangeDetialService.createQuery("from ArrangeDetial d where d.arrange_date_id.id=?", Date.getId());
			List<ArrangeDetial> arrangeList = detialQuery.list();
			for(ArrangeDetial ad :arrangeList){
				if(ad.getPerson_id().equals(person.getId())){
					ad.setPosition(person.getMy_work().getId());
					arrangeDetialService.saveOrUpdate(ad);
				}else if(ad.getPerson_id().contains("," + person.getId())){
					ad.setPerson_id(ad.getPerson_id().replace("," + person.getId(), ""));
					arrangeDetialService.saveOrUpdate(ad);
					ArrangeDetial arrangeDetial = new ArrangeDetial();
					arrangeDetial.setArrange_date_id(ad.getArrange_date_id());
					arrangeDetial.setCreate_time(ad.getCreate_time());
					arrangeDetial.setPart(ad.getPart());
					arrangeDetial.setPerson_id(person.getId());
					arrangeDetial.setPosition(person.getMy_work().getId());
					arrangeDetial.setUpdata_time(ad.getUpdata_time());
					arrangeDetialService.saveOrUpdate(arrangeDetial);
				}else if(ad.getPerson_id().contains(person.getId())){
					ad.setPerson_id(ad.getPerson_id().replace(person.getId(), ""));
					arrangeDetialService.saveOrUpdate(ad);
					ArrangeDetial arrangeDetial = new ArrangeDetial();
					arrangeDetial.setArrange_date_id(ad.getArrange_date_id());
					arrangeDetial.setCreate_time(ad.getCreate_time());
					arrangeDetial.setPart(ad.getPart());
					arrangeDetial.setPerson_id(person.getId());
					arrangeDetial.setPosition(person.getMy_work().getId());
					arrangeDetial.setUpdata_time(ad.getUpdata_time());
					arrangeDetialService.saveOrUpdate(arrangeDetial);
				}
			}
		}
		
	}
	@OpenAccess
	@ResponseBody
	@RequestMapping("interface_getDetialByDate")
	public String pad_getDetialByDate(HttpServletRequest request) {
		String arrangeDate = request.getParameter("arrangeDate");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(arrangeDate == null || "".equals(arrangeDate) || "null".equals(arrangeDate) ){
			arrangeDate = sdf.format(new Date());
		}
		Query datesQuery = arrangeDateService.createQuery("from ArrangeDate d where date_format(d.arrange_date,'%Y-%m-%d')=?", arrangeDate);
		ArrangeDate Date = (ArrangeDate) datesQuery.list().get(0);
		Query detialQuery = arrangeDetialService.createQuery("from ArrangeDetial d where d.arrange_date_id.id=?", Date.getId());
		List<ArrangeDetial> list = detialQuery.list();
		List<ArrangeDetialAndPersData> arrangeDetialAndPersDataList = new ArrayList<ArrangeDetialAndPersData>();
		for(ArrangeDetial ad:list){
			ArrangeDetialAndPersData arrangeDetialAndPersData = new ArrangeDetialAndPersData();
			Set<Personnel> persons = new HashSet<Personnel>();
			for (String pid : ad.getPerson_id().split(",")) {
				Personnel p = personnelService.getById(pid);
				persons.add(p);
			}
			arrangeDetialAndPersData.setPersons(persons);
			arrangeDetialAndPersData.setArrange_date_id(ad.getArrange_date_id().getId());
			arrangeDetialAndPersData.setCreate_time(ad.getCreate_time());
			arrangeDetialAndPersData.setPosition(ad.getPosition());
			arrangeDetialAndPersData.setUpdata_time(ad.getUpdata_time());
			arrangeDetialAndPersDataList.add(arrangeDetialAndPersData);
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONArray jsonArray = JSONArray.fromObject(arrangeDetialAndPersDataList, jsonConfig);
		return jsonArray.toString();
	}
	@OpenAccess
	@ResponseBody
	@RequestMapping("getServerTime")
	public String getServerTime(HttpServletRequest request) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONObject jsonArray = JSONObject.fromObject(new Date(), jsonConfig);
		return jsonArray.toString();
	}
	/**
	 * 不响应调度
	 * @param request
	 */
	@OpenAccess
	@ResponseBody
	@RequestMapping("ondutyCancle")
	public String ondutyCancle(HttpServletRequest request) {
		String watchCode = request.getParameter("watchCode");
		Personnel person = personnelService.find(Restrictions.eq("watch_code", watchCode)).get(0);
		if("11".equals(person.getMy_work().getId())){
			return "不响应调度";
		}
		Message msg = new Message();
		msg.setContent(person.getName() + "未响应您的调度");
		msg.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		msg.setCreator("admin");
		msg.setStatu(AndroidConstant.MESSAGE_STATE_UNREAD_KEY);
		msg.setTitle("回复信息");
		msg.setType(AndroidConstant.MESSAGE_TYPE_ANSWER_KEY);
		Personnel mate = personnelService.find(Restrictions.eq("my_work", workPositionService.getById("11"))).get(0);
		msg.setWatch_code(mate.getWatch_code());
		msg.setOrdertime(new Date());
		messageService.saveOrUpdate(msg);
		for(AndroidMinaSession as:sessions){
			if(as.getWatchCode().equals(mate.getWatch_code())){
				SocketDataSender.sendAndroid(as.getIoSession(), "ReturnMessage");
				return "不响应调度";
			}
		}
		return "不响应调度";
	}
	/**
	 * 呼叫类型（呼叫班长、呼叫保安、集团业务）消息函数
	 * 
	 * @param -type
	 */
	@OpenAccess
	@ResponseBody
	@RequestMapping("callService")
	public String callMonitor(HttpServletRequest request) {
		Integer type = Integer.parseInt(request.getParameter("type"));
		personnelService.logicMsgCall(new AndroidMinaSession(null), type, "admin", messageService);
		return "呼叫成功";
	}
	/**
	 * 处理紧急类型（治安、医疗、火警）消息函数
	 * 
	 * @param   -type
	 */
	@OpenAccess
	@ResponseBody
	@RequestMapping("emergencyService")
	public String emergencyService(HttpServletRequest request) {
		Integer type = Integer.parseInt(request.getParameter("type"));
		List<Personnel> personnels = personnelService.find(Restrictions
				.not(Restrictions.in("my_work", new Object[] { AndroidConstant.PERSONNEL_STATE_VACATION_KEY })));
		messageService.logicMsgUrgent(new AndroidMinaSession(null), type, "admin", personnelService, personnels);
		return "紧急处理成功";
	}
	/**
	 * 请假反馈
	 * 
	 * @param -type
	 */
	@OpenAccess
	@ResponseBody
	@RequestMapping("vacationAnser")
	public String vacationAnser(HttpServletRequest request) {
		String msgId = request.getParameter("msgId");
		String anser = request.getParameter("anser");
		Message message = messageService.getById(msgId);
		String toWatchCode = message.getCreator();
		Message msg = new Message();
		String content = "值长同意了你的请假申请！";
		if("0".equals(anser)){
			content = "值长同意了你的请假申请！";
		}else{
			content = "值长不同意你的请假申请！具体原因请联系值长。";
		}
		msg.setContent(content);
		msg.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		msg.setCreator("admin");
		msg.setStatu(AndroidConstant.MESSAGE_STATE_UNREAD_KEY);
		msg.setTitle("请假反馈信息");
		msg.setType(AndroidConstant.MESSAGE_TYPE_ANSWER_KEY);
		msg.setWatch_code(toWatchCode);
		msg.setOrdertime(new Date());
		messageService.saveOrUpdate(msg);
//		Personnel person = personnelService.find(Restrictions.eq("watch_code", toWatchCode)).get(0);
		PropertiesHelper p = new PropertiesHelper("config.properties");
		String httpCore = p.readValue("url");
		String s = null;
		try{
//			s = HttpUtil.get(httpCore+"/api/employeeInformation/getListByWatch?watchCode="+toWatchCode);
			s = HttpUtil.get(CountInfo.GET_PERSON_BY_WATCHCODE+"watchCode="+toWatchCode,2000);
		}catch (Exception e){
			return "";
		}
		PersonVo personnels = null;
		if(!"".equals(s)){
			cn.hutool.json.JSONObject object = new cn.hutool.json.JSONObject(s);
			personnels = JSONUtil.toBean(object,PersonVo.class);
		}
		Personnel person = new Personnel(personnels);
		SocketDataSender.sendWatchMsg(AndroidConstant.MESSAGE_TYPE_ANSWER_KEY, toWatchCode, person);
		return "反馈成功";
	}
	/**
	 * 直接给值长发消息
	 * 
	 * @param -type
	 */
	@OpenAccess
	@ResponseBody
	@RequestMapping(value = "toTheMate", method = RequestMethod.POST)
	public String toTheMate(HttpServletRequest request) {
		Query query = personnelService.createQuery("from Personnel p where p.my_work.id=?", "11");
		List<Personnel> personnels = query.list();
		Personnel person = personnels.get(CommonUtil.getRandom(0, personnels.size() - 1));// 随机选一个班长(后台)
		String ss = request.getParameter("content");
		
		Message msg = new Message();
		if(ss!=null){
			msg.setContent(ss);
		}
		msg.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		msg.setCreator("admin");
		msg.setStatu(AndroidConstant.MESSAGE_STATE_UNREAD_KEY);
		msg.setTitle("意见簿留言信息");
		msg.setType(99);
		msg.setWatch_code(person.getWatch_code());
		msg.setOrdertime(new Date());
		messageService.saveOrUpdate(msg);
		SocketDataSender.sendWatchMsg(AndroidConstant.MESSAGE_TYPE_ANSWER_KEY, person.getWatch_code(), person);
		return "反馈成功";
	}
	/**
	 * 获取灯光、空调等设备列表
	 *
	 */
	@OpenAccess
	@ResponseBody
	@RequestMapping("getOtherDevice")
	public String getOtherDevice(HttpServletRequest request) {
		return JSON.toJSONString(CountInfo.DEVICE_LIST);
	}
}
