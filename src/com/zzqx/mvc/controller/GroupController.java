package com.zzqx.mvc.controller;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzqx.mvc.entity.Group;
import com.zzqx.mvc.entity.Terminal;
import com.zzqx.mvc.javabean.ReturnMessage;
import com.zzqx.mvc.service.TerminalService;
import com.zzqx.mvc.service.GroupService;
import com.zzqx.support.utils.StringHelper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

@Controller
@RequestMapping(value = "/group")
public class GroupController extends BaseController {

	@Autowired
	private GroupService groupService;
	@Autowired
	private TerminalService terminalService;
	
	@ResponseBody
	@RequestMapping("get")
	public String get(String id) {
		Group group = groupService.getById(id);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		return JSONObject.fromObject(group, jsonConfig).toString();
	}
	
	@ResponseBody
	@RequestMapping("add")
	public String add(Group group) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "新建成功！");
		if(StringHelper.isEmpty(group.getName()) || StringHelper.isEmpty(group.getCodeName())) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("新建失败！");
			return message.toString();
		}
		group.setName(group.getName().trim());
		group.setCodeName(group.getCodeName().trim().toUpperCase());
		List<Group> list1 = groupService.find(Restrictions.eq("name", group.getName()));
		if(list1.size() > 0) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("新建失败，该设备组名称已经存在！");
			return message.toString();
		}
		List<Group> list2 = groupService.find(Restrictions.eq("codeName", group.getCodeName()));
		if(list2.size() > 0) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("新建失败，设备组别名已经存在！");
			return message.toString();
		}
		if(StringHelper.isNotBlank(group.getServerNodeMac())) {
			List<Group> list3 = groupService.find(Restrictions.eq("serverNodeMac", group.getServerNodeMac()));
			if(list3.size() > 0) {
				message.setType(ReturnMessage.MESSAGE_ERROR);
				message.setMessage("新建失败，节点服务器MAC地址已经被绑定！");
				return message.toString();
			}
		}
		group.setAddTime(new Date());
		groupService.saveOrUpdate(group);
		return message.toString();
	}
	
	@ResponseBody
	@RequestMapping("edit")
	public String edit(Group group) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "修改成功！");
		if(StringHelper.isEmpty(group.getId()) || StringHelper.isEmpty(group.getName()) || StringHelper.isEmpty(group.getCodeName())) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("修改失败！");
			return message.toString();
		}
		List<Group> list1 = groupService.find(
				Restrictions.eq("name", group.getName()),
				Restrictions.ne("id", group.getId()));
		if(list1.size() > 0) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("修改失败，与其它设备组同名！");
			return message.toString();
		}
		if(StringHelper.isNotBlank(group.getServerNodeMac())) {
			List<Group> list2 = groupService.find(Restrictions.eq("serverNodeMac", group.getServerNodeMac()), Restrictions.ne("id", group.getId()));
			if(list2.size() > 0) {
				message.setType(ReturnMessage.MESSAGE_ERROR);
				message.setMessage("修改失败，节点服务器MAC地址已经被绑定！");
				return message.toString();
			}
		}
		Group f = groupService.getById(group.getId());
		f.setName(group.getName());
		f.setServerNodeMac(group.getServerNodeMac());
		f.setCodeName(f.getCodeName().toUpperCase());
		groupService.saveOrUpdate(f);
		return message.toString();
	}
	
	@ResponseBody
	@RequestMapping("delete")
	public String delete(Group group) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "删除成功！");
		if(StringHelper.isEmpty(group.getId())) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("删除失败！");
			return message.toString();
		}
		group = groupService.getById(group.getId());
		if(group == null) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("设备组不存在！");
			return message.toString();
		}
		List<Terminal> terminals = terminalService.find(Restrictions.eq("group", group));
		if(terminals.size() > 0) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("删除失败，该设备组存在设备！");
			return message.toString();
		}
		groupService.delete(group.getId());
		return message.toString();
	}
	
	@ResponseBody
	@RequestMapping("getAll")
	public String getAll() {
		List<Group> groups = groupService.getAll();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONArray jsonArray = JSONArray.fromObject(groups, jsonConfig);
		return jsonArray.toString();
	}
}
