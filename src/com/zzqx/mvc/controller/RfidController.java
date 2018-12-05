package com.zzqx.mvc.controller;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzqx.mvc.annotation.OpenAccess;
import com.zzqx.mvc.entity.Rfid;
import com.zzqx.mvc.javabean.ReturnMessage;
import com.zzqx.mvc.service.RfidService;
import com.zzqx.support.utils.StringHelper;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/rfid")
public class RfidController extends BaseController {

	@Autowired
	private RfidService rfidService;
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("new")
	public String newRfid(String rfid) {
		Rfid.lastRfid = rfid;
		return "success";
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("get")
	public String get(String rfid, String type) {
		List<Rfid> list = rfidService.find(Restrictions.eq("rfid", rfid));
		Rfid it = new Rfid();
		if(list.size() > 0) {
			it = list.get(0);
		}
		String returnMessage = it.getName();
		if(StringHelper.isNotBlank(type) && "json".equalsIgnoreCase(type.trim())) {
			returnMessage = JSONObject.fromObject(it).toString();
		}
		return returnMessage;
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("getLastRfid")
	public String getLastRfid() {
		return Rfid.lastRfid;
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("save")
	public String save(Rfid rfid) {
		System.out.println("开始录入。。。。。。。。。。");
		ReturnMessage message = new ReturnMessage();
		message.setType(ReturnMessage.MESSAGE_SUCCESS);
		List<Rfid> list = rfidService.find(Restrictions.eq("rfid", rfid.getRfid()));
		Rfid it = new Rfid();
		if(list.size() > 0) {
			it = list.get(0);
		}
		rfid.setId(it.getId());
		BeanUtils.copyProperties(rfid, it);
		rfidService.saveOrUpdate(it);
		message.setMessage("添加成功！");
		Rfid.lastRfid="";
		return message.toString();
	}
}
