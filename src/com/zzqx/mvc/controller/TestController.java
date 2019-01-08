package com.zzqx.mvc.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zzqx.mvc.annotation.OpenAccess;
import com.zzqx.mvc.commons.CountInfo;
import com.zzqx.mvc.dao.BhSchduMapper;
import com.zzqx.mvc.dao.EmployeeInformationMapper;
import com.zzqx.mvc.dao.EmployeeJobsMapper;
import com.zzqx.mvc.entity.BhSchdu;
import com.zzqx.mvc.entity.BhSchduExample;
import com.zzqx.mvc.entity.EmployeeInformation;
import com.zzqx.mvc.entity.Message;
import com.zzqx.mvc.service.EmployeeInformationService;
import com.zzqx.mvc.service.MessageService;
import com.zzqx.mvc.service.PersonnelService;
import com.zzqx.support.framework.mina.androidser.AndroidConstant;
import com.zzqx.support.framework.mina.androidser.AndroidMinaManager;
import com.zzqx.support.framework.mina.androidser.AndroidMinaSession;
import com.zzqx.support.utils.DateManager;
import com.zzqx.support.utils.net.SocketDataSender;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/test")
public class TestController extends BaseController {

	@Autowired
	BhSchduMapper bhSchduMapper;
	@Autowired
	EmployeeInformationMapper employeeInformationMapper;
	@Autowired
	EmployeeJobsMapper employeeJobsMapper;
	@Autowired
	private MessageService messageService;
	@Autowired
	private EmployeeInformationService employeeInformationService;
	@Autowired
	private PersonnelService personnelService;

	private String getData = "";

	@OpenAccess
	@ResponseBody
	@RequestMapping("get")
	public String get() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "{\"categories\":[\"一月\",\"二月\",\"三月\",\"四月\",\"五月\"],\"values\":[111,222,333,444,555]}";
	}

	@OpenAccess
	@RequestMapping("testMap")
	@ResponseBody
	public String test(Map<String,String> map){
		map.size();
		return null;
	}
	@OpenAccess
	@RequestMapping("testGetEmp")
	@ResponseBody
	public void doSynchronizeTask(){
		getData = HttpUtil.get(CountInfo.GET_SYNC_DAYA);
		JSONObject dataJson = JSONUtil.parseObj(getData);
		Object bhSchduObject = dataJson.get("ScheduList");
		Object employeeInformationObject = dataJson.get("employeeInformationList");
		JSONArray schJsonArray = JSONUtil.parseArray(bhSchduObject);
		JSONArray empJsonArray = JSONUtil.parseArray(employeeInformationObject);

		List<BhSchdu> schduList = JSONUtil.toList(schJsonArray,BhSchdu.class );
		List<EmployeeInformation> employeeInformationList = JSONUtil.toList(empJsonArray,EmployeeInformation.class );

		if(schduList !=null && schduList.size()>0){
			bhSchduMapper.deleteByExample(null);
			bhSchduMapper.batchInsertBhSchdu(schduList);
		}
		if(employeeInformationList != null && employeeInformationList.size()>0){
			employeeInformationMapper.deleteByExample(null);
			employeeInformationMapper.batchInsert(employeeInformationList);
		}

	}
	@OpenAccess
	@RequestMapping("testMsg")
	@ResponseBody
	public void sendAutoMessage_new(){
		// 清空岗位
		try{
			String clearJobs = HttpUtil.get(CountInfo.UPDATE_MY_WORK_BY_HALLID);
		}catch (Exception e){
			System.out.print("读取监控系统该数据失败，读取本地数据");
		}
		//获取今天排班信息
		List<BhSchdu> bhSchdus = new ArrayList<BhSchdu>();
		try{
			String schMsg = HttpUtil.get(CountInfo.GET_SCH_MSG);
			if(!"".equals(schMsg)){
				com.alibaba.fastjson.JSONObject jsa = com.alibaba.fastjson.JSONObject.parseObject(schMsg);
				Object Json = jsa.get("data");
				JSONArray schJson = JSONUtil.parseArray(Json.toString());
				bhSchdus = JSONUtil.toList(schJson,BhSchdu.class);
			}
		}catch (Exception e){
			System.out.print("读取监控系统该数据失败，读取本地数据");
			BhSchduExample bhSchduExample = new BhSchduExample();
			BhSchduExample.Criteria criteria = bhSchduExample.createCriteria();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String now = sdf.format(new Date());
			criteria.andScheduDateEqualTo(new Date(now));
			bhSchdus = bhSchduMapper.selectByExample(bhSchduExample);
		}
		String wordStr = "";
		for(int i = 0;i<bhSchdus.size();i++){
			BhSchdu schTemp = bhSchdus.get(i);
			EmployeeInformation employeeInformation = employeeInformationService.getById(schTemp.getEmployeeId().toString());
			//查询员工当天是否已经有日常信息
			List<Message> messageList = messageService.find(Restrictions.eq("watch_code", employeeInformation.getWatchCode()),
					Restrictions.eq("type", AndroidConstant.MESSAGE_TYPE_NORMAL_KEY),
					Restrictions.ilike("create_time", DateManager.date2Str(DateManager.date_sdf), MatchMode.ANYWHERE));
			Message msgTemp = null;
			if (messageList != null && messageList.size() > 0) {
				msgTemp = messageList.get(0);
			}
			if (msgTemp == null) {
				//查询是否已经连接服务器
				List<AndroidMinaSession> sessions = AndroidMinaManager.getClients();
				for(AndroidMinaSession sTemp:sessions){
					if(sTemp!=null){
						/**
						 * 插入日常消息
						 */
						personnelService.logicMsgCall(sTemp, AndroidConstant.MESSAGE_TYPE_NORMAL_KEY, sTemp.getWatchCode(),
								messageService);
						SocketDataSender.sendAndroid(sTemp.getIoSession(), "AutoMessage");
					}
				}
			}
		}
	}

}