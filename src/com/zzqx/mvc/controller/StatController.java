package com.zzqx.mvc.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zzqx.mvc.annotation.OpenAccess;
import com.zzqx.mvc.entity.Content;
import com.zzqx.mvc.entity.Folder;
import com.zzqx.mvc.entity.Personnel;
import com.zzqx.mvc.entity.Stat;
import com.zzqx.mvc.entity.TerminalContent;
import com.zzqx.mvc.entity.User;
import com.zzqx.mvc.javabean.ReturnMessage;
import com.zzqx.mvc.service.PersonnelService;
import com.zzqx.mvc.service.StatService;
import com.zzqx.mvc.service.UserService;
import com.zzqx.support.utils.DateManager;
import com.zzqx.support.utils.StringHelper;
//import com.zzqx.support.utils.net.SocketData;
import com.zzqx.support.utils.file.FileManager;
import com.zzqx.support.utils.net.SocketDataSender;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;


@Controller
@RequestMapping(value = "/stat")
public class StatController extends BaseController {

	@Autowired
	private StatService statService;
	@Autowired
	private PersonnelService personnelService;
	
	@ResponseBody
	@RequestMapping("statByDate")
	public String statByMonth(HttpServletRequest request,HttpServletResponse response) {
		String startStr = request.getParameter("start");
		String endStr = request.getParameter("end");
		Date startDate = new Date();
		Date endDate = new Date();
		try {
			startDate = DateManager.date_sdf.parse(startStr);
			endDate = DateManager.date_sdf.parse(endStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		ArrayList<Personnel> list = new ArrayList<Personnel>();
		list = (ArrayList<Personnel>) personnelService.getAll();
		List<String> names = new ArrayList<String>();
		List<Double> free = new ArrayList<Double>();
		List<Double> busy = new ArrayList<Double>();
		List<Double> leave = new ArrayList<Double>();
		List<Double> precent = new ArrayList<Double>();
		for(int i = 0;i<list.size();i++){
			names.add(list.get(i).getName());
			Query query = statService.createQuery("select sum(s.statu_busy),sum(s.statu_free),sum(s.statu_leave) from Stat s where s.person_id=? and s.statDay between ? and ?",list.get(i).getId(),startStr,endStr);
//			query.setDate(1, startDate);
//			query.setDate(2, endDate);
//			@SuppressWarnings("unused")
//			List<Stat> statList = statService.find(Restrictions.between("statDay", startDate, endStr),Restrictions.eq("person_id", list.get(i).getId()));
			
			if(query.list().size() == 0){
				free.add(0d);
				busy.add(0d);
				leave.add(0d);
			}else{
				Object[] obj = (Object[]) query.list().get(0);
				double busyTime = 0,freeTime = 0,leaveaTime = 0, pre = 0;
				if(obj[0] != null){
					busyTime = Integer.parseInt(obj[0].toString());
					busy.add(busyTime/60d);
				}else{
					busy.add(0d);
				}
				if(obj[1] != null){
					freeTime = Integer.parseInt(obj[1].toString());
					free.add(freeTime/60d);
				}else{
					free.add(0d);
				}
				if(obj[2] != null){
					leaveaTime = Integer.parseInt(obj[2].toString());
					leave.add(leaveaTime/60d);
				}else{
					leave.add(0d);
				}
				if(busyTime+freeTime+leaveaTime != 0){
					pre = busyTime*100/(busyTime+freeTime+leaveaTime);
					precent.add(pre);
				}else{
					precent.add(0d);
				}
				
				
			}
		}
		JSONObject json = new JSONObject();
		json.put("names", names.toArray());
		json.put("free", free.toArray());
		json.put("busy", busy.toArray());
		json.put("leave", leave.toArray());
		json.put("precent", precent.toArray());
		return json.toString();
	}
	@ResponseBody
	@RequestMapping("statByDay")
	public String statByDay(HttpServletRequest request,HttpServletResponse response) {
		String year = request.getParameter("syear");
		String mon = request.getParameter("smon");
		int days = Integer.parseInt(request.getParameter("days")) +1;
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 1;i<days;i++){
			String day;
			if(i<10){
				day = "0" + i;
			}else{
				day = i+"";
			}
			Query query = statService.createQuery("select t from Stat t where t.statDay like ?", year+"-"+mon+"-"+day);
			int num = query.list().size();
			list.add(num);
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		return list.toString();
	}
	
	


}
