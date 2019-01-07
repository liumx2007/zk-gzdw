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
import com.zzqx.mvc.entity.EmployeeInformation;
import com.zzqx.mvc.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

}