package com.zzqx.mvc.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zzqx.mvc.annotation.OpenAccess;
import com.zzqx.mvc.commons.CountInfo;
import com.zzqx.mvc.dao.*;
import com.zzqx.mvc.entity.*;
import com.zzqx.mvc.javabean.R;
import com.zzqx.mvc.service.EmployeeInformationService;
import com.zzqx.mvc.service.MessageService;
import com.zzqx.mvc.service.PersonnelService;
import com.zzqx.support.framework.mina.androidser.AndroidConstant;
import com.zzqx.support.framework.mina.androidser.AndroidMinaManager;
import com.zzqx.support.framework.mina.androidser.AndroidMinaSession;
import com.zzqx.support.utils.DateManager;
import com.zzqx.support.utils.machine.hardware.Hardware;
import com.zzqx.support.utils.machine.hardware.HardwareHandler;
import com.zzqx.support.utils.net.SocketDataSender;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

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
	@Autowired
	TerminalMybatisMapper terminalMybatisMapper;
	@Autowired
	HardwareMapper hardwareMapper;

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
		CountInfo countInfo = new CountInfo();
		getData = HttpUtil.get(countInfo.GET_SYNC_DAYA);
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
//			employeeInformationMapper.deleteByExample(null);
			employeeInformationMapper.delOrBaoAn();
			employeeInformationMapper.batchInsert(employeeInformationList);
		}

	}
	@OpenAccess
	@RequestMapping("testMsg")
	@ResponseBody
	public void sendAutoMessage_new(){
		// 清空岗位
		try{
			CountInfo countInfo = new CountInfo();
			String clearJobs = HttpUtil.get(countInfo.UPDATE_MY_WORK_BY_HALLID);
		}catch (Exception e){
			System.out.print("读取监控系统该数据失败，读取本地数据");
		}finally {
			//清空本地岗位 排除保安人员

		}
		//获取今天排班信息
		List<BhSchdu> bhSchdus = new ArrayList<BhSchdu>();
		try{
			CountInfo countInfo = new CountInfo();
			String schMsg = HttpUtil.get(countInfo.GET_SCH_MSG);
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
	@OpenAccess
	@RequestMapping("delEmp")
	@ResponseBody
   public R del(){
		employeeInformationMapper.delOrBaoAn();
	   return R.ok();
   }
	@OpenAccess
	@RequestMapping("hardware")
	@ResponseBody
	public R hardware(String mac){
		List<Hardware> hardware = HardwareHandler.getHardwareList(mac);
		if (hardware.size() > 0) {
			return R.ok().put("data",hardware);
		}
		return R.error();
	}
	/**
	 * 服务端A->服务端B之间文件以及数据传输接收测试
	 */
	@OpenAccess
	@RequestMapping(value = "/singleFile",method = RequestMethod.POST)
	@ResponseBody
	public R fileUpload(HttpServletRequest request,
							 HttpServletResponse response,
							 MultipartFile file,
							 Integer businessEnumType,//上传文件的业务类型
							 String userId,//用户ID(不传默认用户是 0，后面通过接口获取用户信息)
							 Integer retainFileName, //保留文件名类型 0 不保留文件名  1 保留文件名 2 保留文件名，文件重复覆盖
							 String saveToDb,   //是否保存到公共表
							 Integer sortNumber,     //文件顺序
							 String serverToken
	) {
		return R.ok();
	}

	/**
	 * 测试调用
	 */
	@OpenAccess
	@RequestMapping(value = "/testHttpUtil")
	@ResponseBody
	public R testHttpUtil(){
		Map<String,Object> map = new HashMap<>();
		map.put("hallId","2");
		map.put("terminalName","电动驾驶");
		map.put("ipAddress","173.60.1.15");
		map.put("macAddress","30-9C-23-C2-0C-DF");
		map.put("code","15");
		map.put("alias","15");
		String s  = HttpUtil.post("http://127.0.0.1:8092/api/employeeInformation/addOrUpdatePlayTerminal",map);
//		Map<String,Object> map = new HashMap<>();
//		map.put("username","banzhang");
//		map.put("password","admin");
//		String s = HttpUtil.post("http://www.zzqxs.com:8091/sys/login/restful",map);
//		String ss = HttpRequest.get("http://127.0.0.1:8092/api/employeeInformation/addOrUpdatePlayTerminal?hallId=2&terminalName=电动驾驶&ipAddress=173.60.1.15&macAddress=30-9C-23-C2-0C-DF&code=15&alias=15").contentType("application/json;charset=UTF-8").execute().body();
		return  R.ok();
	}

	/**
	 * 设备信息保存
	 */
	@OpenAccess
	@RequestMapping(value = "/testSaveHardware")
	@ResponseBody
	public void doSaveHardware(){
		//获取开机状态的设备
		TerminalMybatisExample terminalMybatisExample = new TerminalMybatisExample();
		CountInfo countInfo =new CountInfo();
		TerminalMybatisExample.Criteria  criteria= terminalMybatisExample.createCriteria();
		criteria.andHallIdEqualTo(countInfo.HALL_ID).andStatusEqualTo("true");
		List<TerminalMybatis> list =terminalMybatisMapper.selectByExample(terminalMybatisExample);
//        Hardware hardware = null ;
		list.forEach(terminalMybatis -> {
			List<com.zzqx.support.utils.machine.hardware.Hardware> list_1 = HardwareHandler.getHardwareList(terminalMybatis.getMac());
			if(list_1.size() > 0){
				List<com.zzqx.mvc.entity.Hardware> hardwareList = new CopyOnWriteArrayList<>();
				list_1.forEach(hardware_1 -> {
					com.zzqx.mvc.entity.Hardware hardware = new com.zzqx.mvc.entity.Hardware();
					BeanUtils.copyProperties(hardware_1,hardware);
					hardware.setMac(terminalMybatis.getMac());
					hardware.setCreateTime(new Date());
					hardware.setHallId(countInfo.HALL_ID);
					hardwareList.add(hardware);
				});
				//保存数据到本地数据库
				hardwareMapper.batchInsert(hardwareList);
			}
		});
	}
}