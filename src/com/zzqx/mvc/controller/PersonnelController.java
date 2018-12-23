package com.zzqx.mvc.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.zzqx.mvc.annotation.OpenAccess;
import com.zzqx.mvc.commons.CountInfo;
import com.zzqx.mvc.entity.*;
import com.zzqx.mvc.javabean.ReturnMessage;
import com.zzqx.mvc.service.*;
import com.zzqx.mvc.vo.PersonVo;
import com.zzqx.support.framework.mina.androidser.AndroidConstant;
import com.zzqx.support.framework.mina.androidser.AndroidMinaManager;
import com.zzqx.support.framework.mina.androidser.AndroidMinaSession;
import com.zzqx.support.utils.DateManager;
import com.zzqx.support.utils.StringHelper;
import com.zzqx.support.utils.file.FileManager;
import com.zzqx.support.utils.net.SocketDataSender;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.apache.commons.io.FileUtils;
import org.apache.mina.core.session.IoSession;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
@OpenAccess
@Controller
@SuppressWarnings({ "deprecation", "unchecked" })
@RequestMapping(value = "/personnel")
public class PersonnelController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(PersonnelController.class);


	@Autowired
	private PersonnelService personnelService;
	@Autowired
	private ArrangeDetialService arrangeDetialService;
	@Autowired
	private MessageService messageService;

	@Autowired
	private StatService statService;

	@Autowired
	EmployeeInformationService employeeInformationService;

	String s = null;

	@ResponseBody
	@RequestMapping("getAll")
	public String all(HttpServletRequest request) {
		List<Personnel> list = personnelService.getAll();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String s = JSONArray.fromObject(list, jsonConfig).toString();
		return JSONArray.fromObject(list, jsonConfig).toString();
	}

	@ResponseBody
	@RequestMapping("onduty")
	public String onduty(HttpServletRequest request, HttpServletResponse response) {
		Personnel person = personnelService.getById(request.getParameter("id"));
		person.setOnduty(request.getParameter("hDays"));
		personnelService.saveOrUpdate(person);
		return "success";
	}

	@ResponseBody
	@RequestMapping("add")
	public String add(Personnel personnel, HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "上传成功！");
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartHttpServletRequest.getFile("file");
		List<Personnel> list = personnelService.find(Restrictions.eq("job_num", personnel.getJob_num().trim()));
		if(list.size() > 0) {
			message.setMessage("工号已经存在！");
			message.setType(ReturnMessage.MESSAGE_ERROR);
			return message.toString();
		}
		if(file != null && StringHelper.isNotBlank(file.getOriginalFilename())) {
			String suffix = FileManager.getSuffix(file.getOriginalFilename());
			if(!".jpg".equals(suffix.trim()) && !"png".equals(suffix.trim())) {
				message.setType(ReturnMessage.MESSAGE_ERROR);
				message.setMessage("无效的头像图片！");
				return message.toString();
			}
			String savePath = request.getSession().getServletContext().getRealPath("UploadImages/");
			FileManager.makeDirectory(savePath);
			String fileName = FileManager.getNewFileName(suffix);
			File saveFile = new File(savePath, fileName);
			try {
				FileUtils.copyInputStreamToFile(file.getInputStream(), saveFile);
				String subFileName = FileManager.getFileNameWithOutSuffix(fileName)+"_th"+suffix;
				boolean b = FileManager.processImg(saveFile.getPath(), savePath + subFileName,
						file.getContentType());
				if (!b) {
					message.setMessage("上传成功，但是由于服务器环境出错，文件解析失败！");
				}
				personnel.setPhoto("UploadImages/" + fileName);
			} catch (IOException e) {
				e.printStackTrace();
				message.setType(ReturnMessage.MESSAGE_ERROR);
				message.setMessage("上传失败！");
				return message.toString();
			}
		}
		personnel.setId(null);
		personnel.setBind_status(1);
		personnel.setWork_status(1);
		personnel.setChange_time(new Date());
		if(personnel.getPassWork() == null || "".equals(personnel.getPassWork())){
			personnel.setPassWork(StringHelper.MD5("123456"));
		}
		personnelService.saveOrUpdate(personnel);
		return message.toString();
	}

	@ResponseBody
	@RequestMapping("editPerson")
	public String editPerson(Personnel personnel, HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "修改成功！");
		List<Personnel> list = personnelService.find(Restrictions.eq("job_num", personnel.getJob_num().trim())
				,Restrictions.ne("id", personnel.getId()));
		if(list.size() > 0) {
			message.setMessage("工号已经存在！");
			message.setType(ReturnMessage.MESSAGE_ERROR);
			return message.toString();
		}
		Personnel savedata = personnelService.getById(personnel.getId());
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartHttpServletRequest.getFile("file");
		if (file == null || StringHelper.isBlank(file.getOriginalFilename())) {
			String photo = savedata.getPhoto();
			personnel.setPhoto(photo);
		} else {
			String suffix = FileManager.getSuffix(file.getOriginalFilename());
			if(!".jpg".equals(suffix.trim()) && !"png".equals(suffix.trim())) {
				message.setType(ReturnMessage.MESSAGE_ERROR);
				message.setMessage("无效的头像图片！");
				return message.toString();
			}
			String serverPath = request.getSession().getServletContext().getRealPath("/");
			String savePath = request.getSession().getServletContext().getRealPath("UploadImages/");
			//删除之前的照片
			FileManager.deleteFile(serverPath, savedata.getPhoto());
			//删除缩略图
			FileManager.deleteFile(serverPath, savedata.getPhoto().substring(0,savedata.getPhoto().lastIndexOf("."))+"_th"+savedata.getPhoto().substring(savedata.getPhoto().lastIndexOf("."),savedata.getPhoto().length()));
			FileManager.makeDirectory(savePath);
			String fileName = FileManager.getNewFileName(suffix);
			File saveFile = new File(savePath, fileName);
			try {
				FileUtils.copyInputStreamToFile(file.getInputStream(), saveFile);
				String subFileName = FileManager.getFileNameWithOutSuffix(fileName)+"_th"+suffix;
				boolean b = FileManager.processImg(saveFile.getPath(), savePath + subFileName, file.getContentType());
				if (!b) {
					message.setMessage("上传成功，但是由于服务器环境出错，文件解析失败！");
				}
				personnel.setPhoto("UploadImages/" + fileName);
			} catch (IOException e) {
				e.printStackTrace();
				message.setType(ReturnMessage.MESSAGE_ERROR);
				message.setMessage("上传失败！");
				return message.toString();
			}
		}

		//savedata.setAge(personnel.getAge());
		savedata.setName(personnel.getName());
		savedata.setSex(personnel.getSex());
		savedata.setJob_num(personnel.getJob_num());
		savedata.setWatch_code(personnel.getWatch_code());
		//savedata.setWork(personnel.getWork());
		//savedata.setWork_position(personnel.getWork_position());
		//savedata.setWork_status(personnel.getWork_status());
		savedata.setPhoto(personnel.getPhoto());
		savedata.setBind_status(personnel.getBind_status());//???
		if(personnel.getPassWork() == null || "".equals(personnel.getPassWork())){
			personnel.setPassWork(StringHelper.MD5("123456"));
		}else if(!"******".equals(personnel.getPassWork())){
			savedata.setPassWork(StringHelper.MD5(personnel.getPassWork()));
		}
		
		personnelService.saveOrUpdate(savedata);
		if (personnel.getBind_status() == AndroidConstant.PERSON_BIND_STATUS_UNBOUND_KEY.intValue()) {
			List<AndroidMinaSession> amsList = AndroidMinaManager.getListByWatchCode(personnel.getWatch_code());
			for(AndroidMinaSession ams : amsList){
				if (ams != null && ams.getIoSession() != null) {
					/** 通知在线且已绑定的手表客户端已解绑 **/
					SocketDataSender.sendAndroid(ams.getIoSession(), AndroidConstant.SOCKET_DATA_WATCH_CONN + "unbound");
					AndroidMinaManager.remove(ams.getIoSession());
				}
			}
		}
		return message.toString();
	}

	@ResponseBody
	@RequestMapping("delete")
	public String delete(String id, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "删除成功！");
		if (id == null) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("删除失败！");
			return message.toString();
		}
		String[] ids = id.split(",");
		List<Personnel> persList = personnelService.find(Restrictions.in("id", ids));
		for(Personnel personnel : persList){
			sendAndroidUnBandByIdWatchCode(personnel.getWatch_code());
		}
		personnelService.delete(ids);
		
		//删除对应排班
		List<ArrangeDetial> arrangeDetialList = arrangeDetialService.find(Restrictions.in("person_id", ids));
		String[] aids = new String[arrangeDetialList.size()];
		for(int i=0;i<arrangeDetialList.size();i++){
			aids[i] = arrangeDetialList.get(i).getId();
		}
		arrangeDetialService.delete(aids);
		//清除对应排班人员
		for(String did : ids){
			List<ArrangeDetial> clearArrangeDetialList = arrangeDetialService.find(Restrictions.like("person_id", did, MatchMode.ANYWHERE));
			for(ArrangeDetial ad : clearArrangeDetialList){
				String personID = ad.getPerson_id();
				if(personID.contains("," + did)){
					personID = personID.replace("," + did, "");
				}else if(personID.contains(did)){
					personID = personID.replace(did, "");
				}
				ad.setPerson_id(personID);
				arrangeDetialService.saveOrUpdate(ad);
			}
		}
		return message.toString();
	}

	/**
	 * 给手表调用的方法 修改人员工作状态及更新人员状态统计数据
	 * 
	 * @param request
	 */
	@OpenAccess
	@ResponseBody
	@RequestMapping("watchUpdateWorkStatus")
	public String watchUpdateWorkStatus(HttpServletRequest request) {
		int statu = Integer.valueOf(request.getParameter("statu"));
		String watchCode = request.getParameter("watchCode");
		try{
			s = HttpUtil.get(CountInfo.UPDATE_WORK_STATUS+"workState="+statu+"&watchCode=" + watchCode,1500);
			if (!s.contains("修改成功")) {
//			return "人员信息不存在";
				return "修改状态失败。";
			}
		}catch (Exception e){
			logger.error("连接中台失败");
			EmployeeInformation employeeInformation = new EmployeeInformation();
			employeeInformation.setWorkState(String.valueOf(statu));
			employeeInformation.setWatchCode(watchCode);
			int flag = employeeInformationService.updateByWatchCode(employeeInformation);
			if (flag == 0){
				return "修改状态失败。";
			}
		}
//		List<Personnel> personList = personnelService.find(Restrictions.eq("watch_code", watchCode));
//		Personnel person = null;
//		if (!s.contains("修改成功")) {
////			return "人员信息不存在";
//			return "修改状态失败。";
//		}
//		else {
//			logger.error("人员信息不存在");
//			logger.error("通过watch_code：" + watchCode + "查询人员信息不存在");
//			return "人员信息不存在";
//		}
		Personnel person = new Personnel();
		try{
			s = HttpUtil.get(CountInfo.GET_PERSON_BY_WATCHCODE+"watchCode="+watchCode,1500);
			if(!"".equals(s)){
				cn.hutool.json.JSONObject object = new cn.hutool.json.JSONObject(s);
				person = JSONUtil.toBean(object,Personnel.class);
				person.setWork_status(statu);
			}
		}catch (Exception e){
			logger.error("连接中台失败");
			//根据WatchCode查人员
			EmployeeInformation employeeInformation = new EmployeeInformation();
			employeeInformation.setWatchCode(watchCode);
			List<EmployeeInformation> employeeInformationList = employeeInformationService.selectByWatchCode(employeeInformation);
			if (employeeInformationList.size() > 0) {
				Integer id = employeeInformationList.get(0).getId();
				person.setId(String.valueOf(id));
				person.setWork_status(statu);
			}

		}

		String statDay = DateManager.date2Str(DateManager.date_sdf);
		List<Stat> statList = statService.find(Restrictions.eq("statDay", statDay),
				Restrictions.eq("person_id", person.getId()));
		Stat stat = null;
		if (statList != null && statList.size() > 0) {
			stat = statList.get(0);
		}
		Date statTime = DateManager.str2Date(DateManager.date2Str(DateManager.datetimeFormat),
				DateManager.datetimeFormat);
		Date storageTime = null;
		if (stat != null) {
			stat.setUpdate_time(DateManager.getDate());
			if (stat.getBusy_time() != null) {
				storageTime = stat.getBusy_time();
			} else if (stat.getLeave_time() != null) {
				storageTime = stat.getLeave_time();
			} else if (stat.getFree_time() != null) {
				storageTime = stat.getFree_time();
			}
			if (storageTime != null) {
				int duration = DateManager.dateDiff('s', DateManager.DateToCalendar(statTime),
						DateManager.DateToCalendar(storageTime));
				if (person.getWork_status() == AndroidConstant.PERSONNEL_STATE_BUSYNESS_KEY.intValue()) {// 忙碌
					stat.setStatu_busy(stat.getStatu_busy() + duration);
				} else if (person.getWork_status() == AndroidConstant.PERSONNEL_STATE_TEMP_KEY.intValue()) {// 暂离
					stat.setStatu_leave(stat.getStatu_leave() + duration);
				} else if (person.getWork_status() == AndroidConstant.PERSONNEL_STATE_FREE_KEY.intValue()) {// 空闲
					stat.setStatu_free(stat.getStatu_free() + duration);
				}
			}
		} else {
			stat = new Stat();
			stat.setStatDay(statDay);
			stat.setPerson_id(person.getId());
			stat.setUpdate_time(DateManager.getDate());
			stat.setCreate_time(DateManager.getDate());
			stat.setStatu_busy(0);
			stat.setStatu_free(0);
			stat.setStatu_leave(0);
		}
		if(statu==person.getWork_status()){
			return "状态无变化";
		}
		if (statu == AndroidConstant.PERSONNEL_STATE_BUSYNESS_KEY.intValue()) {// 忙碌
			stat.setBusy_time(statTime);
			stat.setLeave_time(null);
			stat.setFree_time(null);
		} else if (statu == AndroidConstant.PERSONNEL_STATE_TEMP_KEY.intValue()) {// 暂离
			stat.setLeave_time(statTime);
			stat.setBusy_time(null);
			stat.setFree_time(null);
		} else if (statu == AndroidConstant.PERSONNEL_STATE_FREE_KEY.intValue()) {// 空闲
			stat.setFree_time(statTime);
			stat.setLeave_time(null);
			stat.setBusy_time(null);
		}
		statService.saveOrUpdate(stat);
//		person.setWork_status(statu);
//		person.setChange_time(new Date());
//		personnelService.saveOrUpdate(person);
		return "状态修改成功";
	}

	/**
	 * 给手表调用的方法 修改人员工作状态及更新人员状态统计数据
	 * 
	 * @param request
	 */
	@OpenAccess
	@ResponseBody
	@RequestMapping("watchGetStatTime")
	public String watchGetStatTime(HttpServletRequest request) {
		String watchCode = request.getParameter("watchCode");
//		List<Personnel> personList = personnelService.find(Restrictions.eq("watch_code", watchCode));
		String perStr = "";
		PersonVo person = new PersonVo();
		try{
			perStr = HttpUtil.get(CountInfo.GET_PERSON_BY_WATCHCODE+"watchCode="+watchCode,1500);
			if(!"".equals(perStr)){
				cn.hutool.json.JSONObject object = JSONUtil.parseObj(perStr);
				Object ob = object.get("changeTime");
				Date ChangeT =null;
				if (ob != null){
					ChangeT = new Date(Long.valueOf(ob.toString()));
				}else {
					ChangeT = new Date();
				}
				person.setChangeTime(ChangeT);
				String wStatus = object.get("workState").toString();
				System.out.println(Integer.valueOf(wStatus));
				person.setWorkState(Integer.parseInt(wStatus));
			} else {
				logger.error("人员信息不存在");
				logger.error("通过watch_code：" + watchCode + "查询人员信息不存在");
				return "人员信息不存在";
			}
		}catch (Exception e){
			logger.error("连接中台失败");
			//根据WatchCode查人员
			EmployeeInformation employeeInformation = new EmployeeInformation();
			employeeInformation.setWatchCode(watchCode);
			List<EmployeeInformation> employeeInformationList = employeeInformationService.selectByWatchCode(employeeInformation);
			if (employeeInformationList.size() > 0){
				Date ChangeT =null;
				Date ChangeTime = employeeInformationList.get(0).getChangeTime();
				if (ChangeTime != null){
					ChangeT = ChangeTime;
				}else {
					ChangeT = new Date();
				}
				person.setChangeTime(ChangeT);
				Integer WorkStatus = Integer.parseInt(employeeInformationList.get(0).getWorkState());
				person.setWorkState(WorkStatus);
			}else {
				logger.error("人员信息不存在");
				logger.error("通过watch_code：" + watchCode + "查询人员信息不存在");
				return "人员信息不存在";
			}

		}

		Date changeTime = person.getChangeTime();
		Calendar startTime = Calendar.getInstance();
		startTime.setTime(changeTime);
		Integer getSeconds = DateManager.dateDiff('s', Calendar.getInstance(), startTime);
		return getSeconds + "," + person.getWorkState();
	}

	/**
	 * 获取未绑定手表的人员
	 * 
	 * @return
	 */
	@OpenAccess
	@ResponseBody
	@RequestMapping("getPersonByBindStatus")
	public String getPerson(HttpServletRequest request) {
		Integer bindStatus = Integer.valueOf(request.getParameter("bindStatus"));
//		Query query = personnelService.createQuery("from Personnel t where t.bind_status = ?", bindStatus);
//		List<Personnel> list = query.list();
//		JSONArray json = JSONArray.fromObject(list);
//		return json.toString();
		List<EmployeeInformation> employeeInformationList;
		try{
			s = HttpUtil.get(CountInfo.GET_PERSON_LIST,2000);
			cn.hutool.json.JSONArray json = JSONUtil.parseArray(JSONUtil.parseObj(s).get("data"));
			employeeInformationList = JSONUtil.toList(json,EmployeeInformation.class);
		}catch (Exception e){
			logger.error("连接中台失败");
			//获取未绑定人员列表
			EmployeeInformation employeeInformation = new EmployeeInformation();
			employeeInformation.setBindState(bindStatus.shortValue());
			employeeInformation.setHallId(CountInfo.HALL_ID);
			employeeInformationList = employeeInformationService.selectNoboding(employeeInformation);
			if (employeeInformationList.size() > 0 ){
				JSONArray array = new JSONArray();
				array.add(employeeInformationList);
				s  = array.toString().substring(1,array.toString().length()-1);
			}
		}
		return  s;
	}

	@ResponseBody
	@RequestMapping("showJobLogic")
	public String showJobLogic(HttpServletRequest request) {

		return JSONArray.fromObject(AndroidConstant.PERSON_JOB_SALESMAN_MAP).toString();
	}

	@ResponseBody
	@RequestMapping("showBindStatusLogic")
	public String showBindStatusLogic(HttpServletRequest request) {

		return JSONArray.fromObject(AndroidConstant.PERSON_BIND_STATUS_BOUND_MAP).toString();
	}

	/**
	 * 给手表调用的方法 修改人员绑定状态
	 * 
	 * @param request
	 */
	@OpenAccess
	@ResponseBody
	@RequestMapping("watchUpdateBindStatus")
	public String watchUpdateBindStatus(HttpServletRequest request) {
		String uuid = request.getParameter("uuid");
		int status = Integer.valueOf(request.getParameter("status"));
		String id = request.getParameter("id");
		Integer i = Integer.parseInt(id);
//		Personnel person = personnelService.getById(id);
		String re ="";
		try{
			re = HttpUtil.get(CountInfo.UPDATE_PERSON_STATUS+"&id="+i+"&bindState="+0+"&watchCode="+uuid,1500);
			if (re.contains("修改成功")){
				return uuid;
			}
		}catch (Exception e){
			logger.error("连接中台失败");
			//修改人员绑定状态id="+i+"&&bindState=0&watchCode="+uuid
			EmployeeInformation employeeInformation = new EmployeeInformation();
			employeeInformation.setWatchCode(uuid);
//			employeeInformation.setBindState((short)0);
			employeeInformation.setId(i);
			int flag = employeeInformationService.updateById(employeeInformation);
			if (flag > 0){
				return uuid;
			}
		}
//		if (person != null) {
//			if(person.getBind_status()==0){
//				logger.error("人员已绑定");
//				return "fail";
//			}
//			person.setBind_status(status);
//			if (person.getWatch_code() == null || "".equals(person.getWatch_code())) {
//				person.setWatch_code(uuid);
//			} else {
//				uuid = person.getWatch_code();
//			}
//			personnelService.saveOrUpdate(person);
//		} else {
//			logger.error("人员信息不存在");
//			return "fail";
//		}
		return "fail";
	}
	
	/**
	 * 通过APP设备唯一ID获取设备绑定的人员信息（人员名称）
	 * 
	 * @param request
	 */
	@OpenAccess
	@ResponseBody
	@RequestMapping("getPersonnelWatchCode")
	public String getPersonnelWatchCode(HttpServletRequest request) {
		String uuid = request.getParameter("uuid");
		try{
			s = HttpUtil.get(CountInfo.GET_PERSON_BY_WATCHCODE+"watchCode="+uuid,2000);
			if(!"".equals(s)){
				cn.hutool.json.JSONObject object = new cn.hutool.json.JSONObject(s);
				System.out.println(object.get("name").toString());
				return object.get("name").toString();
			}
		}catch (Exception e){
			logger.error("连接中台失败");
			//根据WatchCode查人员
			EmployeeInformation employeeInformation = new EmployeeInformation();
			employeeInformation.setWatchCode(uuid);
			List<EmployeeInformation> employeeInformationList = employeeInformationService.selectByWatchCode(employeeInformation);
			if (employeeInformationList.size() > 0){
				String  name = employeeInformationList.get(0).getName();
				return name;
			}
		}
		return "fail";
	}
	
	@ResponseBody
	@RequestMapping("unBandById")
	public String unBandById(String id, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "  解绑成功！");
		if (id == null) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("解绑失败！");
			return message.toString();
		}
		Personnel personnel = personnelService.getById(id);
		if(personnel!=null&&personnel.getBind_status() == 1){
			message.setType(ReturnMessage.MESSAGE_NOTICE);
			message.setMessage(personnel.getName()+"未绑定任何设备，无需解绑！");
			return message.toString();
		}
		
		List<Message> msgList = messageService.find(Restrictions.eq("watch_code", personnel.getWatch_code()));
		String msgIds = "";
		for(Message msg : msgList){
			msgIds+=msg.getId()+",";
		}
		if(msgIds.length()>0){
			msgIds = msgIds.substring(0,msgIds.length() - 1);
			messageService.delete(msgIds);
		}
		personnel.setBind_status(AndroidConstant.PERSON_BIND_STATUS_UNBOUND_KEY);
		sendAndroidUnBandByIdWatchCode(personnel.getWatch_code());
		personnel.setWatch_code(null);
		personnel.setMy_work(null);
		personnelService.saveOrUpdate(personnel);
		return message.toString();
	}
	
	/**
	 *  通知在线且已绑定的手表客户端已解绑
	 * @param watchCode
	 */
	private void sendAndroidUnBandByIdWatchCode(String watchCode){

		List<AndroidMinaSession> amsList = AndroidMinaManager.getListByWatchCode(watchCode);
		List<IoSession> listi = Lists.newArrayList();
		if(amsList!=null&&amsList.size()>0){
			for(AndroidMinaSession ams : amsList){
				if (ams != null && ams.getIoSession() != null) {
					/** 通知在线且已绑定的手表客户端已解绑 **/
					SocketDataSender.sendAndroid(ams.getIoSession(), AndroidConstant.SOCKET_DATA_WATCH_CONN + "unbound");
					listi.add(ams.getIoSession());
				}
			}
			new Thread(new Runnable() {
				@Override
				public void run() {
					for(IoSession ios : listi){
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						AndroidMinaManager.remove(ios);
					}
				}
			});
		}
	}

}
