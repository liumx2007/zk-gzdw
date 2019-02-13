package com.zzqx.mvc.controller;

import cn.hutool.http.HttpUtil;
import com.zzqx.mvc.annotation.OpenAccess;
import com.zzqx.mvc.commons.CountInfo;
import com.zzqx.mvc.entity.*;
import com.zzqx.mvc.javabean.R;
import com.zzqx.mvc.javabean.ReturnMessage;
import com.zzqx.mvc.javabean.TerminalInfo;
import com.zzqx.mvc.service.ContentService;
import com.zzqx.mvc.service.GroupService;
import com.zzqx.mvc.service.TerminalContentService;
import com.zzqx.mvc.service.TerminalService;
import com.zzqx.support.utils.StringHelper;
import com.zzqx.support.utils.machine.hardware.Hardware;
import com.zzqx.support.utils.machine.hardware.HardwareHandler;
import com.zzqx.support.utils.net.SocketDataSender;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping(value = "/terminal")
public class TerminalController extends BaseController {
	
	@Autowired
	private GroupService groupService;
	@Autowired
	private TerminalService terminalService;
	@Autowired
	private ContentService contentService;
	@Autowired
	private TerminalContentService terminalContentService;

	@OpenAccess
	@ResponseBody
	@RequestMapping("group")
	public String group(Group group) {
		List<Terminal> terminals = terminalService.getByGroup(group);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.setExcludes(new String[] { "group" , "terminal"});
		return JSONArray.fromObject(terminals, jsonConfig).toString();
	}
	
	@ResponseBody
	@RequestMapping("get")
	public String get(String id) {
		Terminal terminal = terminalService.getById(id);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.setExcludes(new String[] { "group" , "terminalContents"});
		return JSONObject.fromObject(terminal, jsonConfig).toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("add")
	public String add(Terminal terminal, String groupId, String groupCodeName, String isServerNode) {
		ReturnMessage message = new ReturnMessage();
		message.setType(ReturnMessage.MESSAGE_ERROR);
		Group group = new Group();
		if(StringHelper.isNotBlank(groupId)) {
			group = groupService.getById(groupId);
		} else if(StringHelper.isNotBlank(groupCodeName)) {
			group = groupService.getByCodeName(groupCodeName);
		} else if(StringHelper.isBlank(groupCodeName)) {
			List<Group> groups = groupService.getAll();
			if(groups.size() != 0) {
				group = groups.get(0);
			}
		}
		groupId = group.getId();
		if(StringHelper.isBlank(groupId)) {
			message.setMessage("添加失败，未知的设备分组！");
			return message.toString();
		}
		Set<Terminal> terminals = group.getTerminals();
		if(StringHelper.isNotBlank(terminal.getIp()) && StringHelper.isNotBlank(terminal.getMac()) && StringHelper.isBlank(terminal.getCodeName())) {
			List<Terminal> list = terminalService.find(
					Restrictions.or(Restrictions.and(Restrictions.eq("ip", terminal.getIp()), Restrictions.eq("group", group)), Restrictions.eq("mac", terminal.getMac())));
			Terminal t;
			if(list.size() > 0) {
				t = list.get(0);
				t.setIp(terminal.getIp());
				t.setMac(terminal.getMac());
			} else {
				t = terminal;
				if(!"true".equals(isServerNode)) {
					Object maxSerialNumberObject = terminalService.createQuery("select max(serialNumber) from Terminal").uniqueResult();
					if(maxSerialNumberObject != null) {
						t.setSerialNumber(Integer.parseInt(maxSerialNumberObject.toString())+1);
					} else {
						t.setSerialNumber(1);
					}
				}
				t.setCodeName(group.getCodeName()+"-"+t.getIp().substring(t.getIp().lastIndexOf(".")+1));
				List<Terminal> ts = terminalService.find(Restrictions.eq("codeName", t.getCodeName()));
				if(ts.size() > 0) {
					t.setCodeName(t.getCodeName()+"_"+ts.size());
				}
				t.setName("");
				t.setStatus("true");
			}
			//
			CountInfo countInfo = new CountInfo();
			t.setHallId(countInfo.HALL_ID);
			terminals.add(t);
			if("true".equals(isServerNode)) {
				group.setServerNodeMac(terminal.getMac());
				t.setSerialNumber(0);
				t.setRemark("节点服务器");
			} else {
				if(t.getSerialNumber() == 0) {
					Object maxSerialNumberObject = terminalService.createQuery("select max(serialNumber) from Terminal").uniqueResult();
					if(maxSerialNumberObject != null) {
						t.setSerialNumber(Integer.parseInt(maxSerialNumberObject.toString())+1);
					} else {
						t.setSerialNumber(1);
					}
					group.setServerNodeMac("");
				}
			}

			group.setTerminals(terminals);
			groupService.saveOrUpdate(group);
//			terminalService.saveOrUpdate(t);
		} else {
			if(StringHelper.isEmpty(terminal.getName()) || StringHelper.isEmpty(terminal.getIp()) || 
					StringHelper.isEmpty(terminal.getMac())) {
				message.setMessage("添加失败！");
				return message.toString();
			}
			List<Terminal> list1 = terminalService.find(Restrictions.and(Restrictions.eq("group", group), Restrictions.eq("serialNumber", terminal.getSerialNumber())));
			if(list1.size() > 0) {
				message.setMessage("添加失败，终端编号已经存在！");
				return message.toString();
			}
			List<Terminal> list2 = terminalService.find(Restrictions.eq("codeName", terminal.getCodeName()));
			if(list2.size() > 0) {
				message.setMessage("添加失败，代号已经存在！");
				return message.toString();
			}
			List<Terminal> list3 = terminalService.find(Restrictions.and(Restrictions.eq("group", group), Restrictions.eq("name", terminal.getName())));
			if(list3.size() > 0) {
				message.setMessage("添加失败，终端名称已经存在！");
				return message.toString();
			}
			List<Terminal> list4 = terminalService.find(Restrictions.and(Restrictions.eq("group", group), Restrictions.eq("ip", terminal.getIp())));
			if(list4.size() > 0) {
				message.setMessage("添加失败，ip地址已经存在！");
				return message.toString();
			}
			List<Terminal> list5 = terminalService.find(Restrictions.eq("mac", terminal.getMac()));
			if(list5.size() > 0) {
				message.setMessage("添加失败，mac地址已经存在！");
				return message.toString();
			}
			terminal.setCodeName(terminal.getCodeName().toUpperCase());
			terminal.setStatus("false");
			CountInfo countInfo = new CountInfo();
			terminal.setHallId(countInfo.HALL_ID);
			terminals.add(terminal);
			group.setTerminals(terminals);
			groupService.saveOrUpdate(group);
			//terminalService.saveOrUpdate(terminal);
		}
		message.setType(ReturnMessage.MESSAGE_SUCCESS);
		message.setMessage("添加成功！");
		return message.toString();
	}
	
	@ResponseBody
	@RequestMapping("edit")
	public String edit(Terminal terminal) {
		ReturnMessage message = new ReturnMessage();
		message.setType(ReturnMessage.MESSAGE_ERROR);
		if(StringHelper.isEmpty(terminal.getName()) || StringHelper.isEmpty(terminal.getIp()) || 
				StringHelper.isEmpty(terminal.getMac()) || terminal.getGroup() == null || StringHelper.isEmpty(terminal.getGroup().getId())) {
			message.setMessage("修改失败！");
			return message.toString();
		}
		List<Terminal> list1 = terminalService.find(Restrictions.eq("serialNumber", terminal.getSerialNumber()), Restrictions.ne("id", terminal.getId()), Restrictions.eq("group", terminal.getGroup()));
		if(list1.size() > 0) {
			message.setMessage("修改失败，终端编号已经存在！");
			return message.toString();
		}
		List<Terminal> list2 = terminalService.find(Restrictions.eq("codeName", terminal.getCodeName()), Restrictions.ne("id", terminal.getId()));
		if(list2.size() > 0) {
			message.setMessage("添加失败，代号已经存在！");
			return message.toString();
		}
		List<Terminal> list3 = terminalService.find(Restrictions.eq("name", terminal.getName()), Restrictions.ne("id", terminal.getId()), Restrictions.eq("group", terminal.getGroup()));
		if(list3.size() > 0) {
			message.setMessage("修改失败，终端名称已经存在！");
			return message.toString();
		}
		List<Terminal> list4 = terminalService.find(Restrictions.eq("ip", terminal.getIp()), Restrictions.ne("id", terminal.getId()), Restrictions.eq("group", terminal.getGroup()));
		if(list4.size() > 0) {
			message.setMessage("修改失败，ip地址已经存在！");
			return message.toString();
		}
		List<Terminal> list5 = terminalService.find(Restrictions.eq("mac", terminal.getMac()), Restrictions.ne("id", terminal.getId()));
		if(list5.size() > 0) {
			message.setMessage("修改失败，mac地址已经存在！");
			return message.toString();
		}
		Terminal t = terminalService.getById(terminal.getId());
		terminal.setStatus(t.getStatus());
		BeanUtils.copyProperties(terminal, t);
		//19-1-29 设备更新状态
		t.setUpdateStatus(2);
		terminalService.saveOrUpdate(t);
		message.setType(ReturnMessage.MESSAGE_SUCCESS);
		message.setMessage("修改成功！");
		return message.toString();
	}

	@OpenAccess
	@ResponseBody
	@RequestMapping("delete")
	public String delete(String id) {
		ReturnMessage message = new ReturnMessage();
		if(id == null) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("删除失败！");
			return message.toString();
		}
		String[] ids = id.split(",");
		terminalService.delete(ids);
		//19-1-29 删除监控系统数据
		try{
			CountInfo countInfo = new CountInfo();
			String s = HttpUtil.get(countInfo.DW_TERMINAL_DELETE + "?terminalId="+id);
		}catch (Exception e){
			//监控删除出行问题，记录未删除ID，
			//todo 可以采用加表处理

		}
		message.setType(ReturnMessage.MESSAGE_SUCCESS);
		message.setMessage("删除成功！");
		return message.toString();
	}
	
	@RequestMapping("setContent")
	public String setContent(String id, ModelMap map) {
		Terminal terminal = terminalService.getById(id);
		map.put("terminal", terminal);
		return "terminal/setContent";
	}
	
	@RequestMapping("manageContent")
	public String manageContent(String id, ModelMap map) {
		Terminal terminal = terminalService.getById(id);
		map.put("terminal", terminal);
		return "terminal/manageContent";
	}
	
	@ResponseBody
	@RequestMapping("distributeContent")
	public String distributeContent(String terminalId, String contentIds) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "终端内容设置成功！");
		Terminal terminal = terminalService.getById(terminalId);
		List<Content> contents = contentService.get(contentIds.split(","));
		if(terminal == null || contents == null) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("终端内容设置失败！");
			return message.toString();
		}
		terminalContentService.executeHql("delete TerminalContent where terminal = ?", terminal);
		contents.forEach(content->{
			TerminalContent terminalContent = new TerminalContent();
			terminalContent.setTerminal(terminal);
			terminalContent.setContent(content);
			terminalContent.setModel(TerminalContent.MODEL_ONCE);
			int max = terminalContentService.getMaxSort(terminal.getId());
			terminalContent.setSort(max+1);
			terminalContentService.saveOrUpdate(terminalContent);
		});
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.setExcludes(new String[] { "terminal" });
		SocketDataSender socket = new SocketDataSender();
		socket.sendToClient(terminal.getCodeName(), "play:"+JSONArray.fromObject(terminal.getTerminalContents(), jsonConfig).toString());
		return message.toString();
	}
	
	@ResponseBody
	@RequestMapping("cancelContent")
	public String cancelContent(String terminalId) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "终端播放内容取消成功！");
		Terminal terminal = terminalService.getById(terminalId);
		terminalContentService.executeHql("delete TerminalContent where terminal = ?", terminal);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.setExcludes(new String[] { "terminalContents","group" });
		SocketDataSender socket = new SocketDataSender();
		socket.sendToClient(terminal.getCodeName(), "cancel");
		return message.toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("refresh")
	public String refresh(String id, String code, String mac) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "刷新内容成功！");
		if(id == null && code == null) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("刷新失败！");
			return message.toString();
		}
		id = StringHelper.isEmpty(id)?"":id;
		code = StringHelper.isEmpty(code)?"":code;
		mac = StringHelper.isEmpty(mac)?"":mac;
		List<Terminal> terminals1 = terminalService.get(id.split(","));
		String str = Stream.of(code.split(",")).filter(StringHelper::isNotBlank).map(name->"'"+name+"'").collect(Collectors.joining(","));
		String macString = Stream.of(mac.split(",")).filter(StringHelper::isNotBlank).map(name->"'"+name+"'").collect(Collectors.joining(","));
		List<Terminal> terminals2 = new ArrayList<>();
		List<Terminal> terminals3 = new ArrayList<>();
		if(StringHelper.isNotBlank(str)) {
			terminals2 = terminalService.createQuery("from Terminal where codeName in ("+str+")").list();
		}
		if(StringHelper.isNotBlank(macString)) {
			terminals3 = terminalService.createQuery("from Terminal where mac in ("+macString+")").list();
		}
//		List<Terminal> list = Stream.concat(terminals1.stream(), terminals2.stream()).collect(Collectors.toList());
		List<Terminal> list = new ArrayList<>();
		list.addAll(terminals1);
		list.addAll(terminals2);
		list.addAll(terminals3);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.setExcludes(new String[] { "terminal" });
		list.forEach(terminal->{
			SocketDataSender socket = new SocketDataSender();
			socket.sendToClient(terminal.getCodeName(), "play:"+JSONArray.fromObject(terminal.getTerminalContents(), jsonConfig).toString());
		});
		return message.toString();
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("terminalContents")
	public String terminalContents(String terminalId) {
		Terminal terminal = terminalService.getById(terminalId);
		List<TerminalContent> terminalContents = terminalContentService.createQuery(
				"from TerminalContent where terminal = ? order by sort", terminal).list();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.setExcludes(new String[] { "terminal" , "terminalContents"});
		return JSONArray.fromObject(terminalContents, jsonConfig).toString();
	}
	
	@ResponseBody
	@RequestMapping("sortContent")
	public String sortContent(String direction, String terminalContentId) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "排序成功！");
		if(StringHelper.isBlank(direction) || StringHelper.isBlank(terminalContentId)) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("排序失败！");
			return message.toString();
		}
		TerminalContent source = terminalContentService.getById(terminalContentId);
		TerminalContent target = new TerminalContent();
		int sourceSort = source.getSort();
		if("1".equals(direction)) {
			target = terminalContentService.getCloseSmall(terminalContentId);
		} else if("-1".equals(direction)) {
			target = terminalContentService.getCloseLarge(terminalContentId);
		}
		if(target != null) {
			source.setSort(target.getSort());
			target.setSort(sourceSort);
			terminalContentService.saveOrUpdate(target);
			terminalContentService.saveOrUpdate(source);
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
			jsonConfig.setExcludes(new String[] { "terminal" });
			SocketDataSender socket = new SocketDataSender();
			socket.sendToClient(target.getTerminal().getCodeName(), "play:"+JSONArray.fromObject(target.getTerminal().getTerminalContents(), jsonConfig).toString());
		}
		return message.toString();
	}
	
	@ResponseBody
	@RequestMapping("removeContent")
	public String removeContent(String terminalContentId) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "删除成功！");
		TerminalContent terminalContent = terminalContentService.getById(terminalContentId);
		Terminal terminal = terminalContent.getTerminal();
		terminalContentService.delete(terminalContentId);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.setExcludes(new String[] { "terminal" });
		SocketDataSender socket = new SocketDataSender();
		socket.sendToClient(terminal.getCodeName(), "play:"+JSONArray.fromObject(terminal.getTerminalContents(), jsonConfig).toString());
		return message.toString();
	}
	
	@ResponseBody
	@RequestMapping("setModel")
	public String setModel(String terminalContentId, String model) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "模式设置成功！");
		TerminalContent terminalContent = terminalContentService.getById(terminalContentId);
		terminalContent.setModel(Integer.valueOf(model));
		terminalContentService.saveOrUpdate(terminalContent);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.setExcludes(new String[] { "terminal" });
		SocketDataSender socket = new SocketDataSender();
		socket.sendToClient(terminalContent.getTerminal().getCodeName(), "play:"+JSONArray.fromObject(terminalContent.getTerminal().getTerminalContents(), jsonConfig).toString());
		return message.toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("getRunningTerminalsHardwaresInfo")
	public String getRunningTerminalsHardwaresInfo(String groupId, String groupCodeName) {
		List<TerminalInfo> terminalInfoList = new ArrayList<>();
		Group group = new Group();
		if(StringHelper.isNotBlank(groupId)) {
			group = groupService.getById(groupId);
		} else if(StringHelper.isNotBlank(groupCodeName)) {
			group = groupService.getByCodeName(groupCodeName);
		} else if(StringHelper.isBlank(groupCodeName)) {
			List<Group> groups = groupService.getAll();
			if(groups.size() != 0) {
				group = groups.get(0);
			}
		}
		groupId = group.getId();
		if(StringHelper.isNotBlank(groupId)) {
			List<Terminal> list = terminalService.find(Restrictions.eq("group", group), Restrictions.eq("status", "true"));
			list.forEach(terminal -> {
				TerminalInfo terminalInfo = new TerminalInfo();
				terminalInfo.setName(terminal.getName());
				terminalInfo.setCodeName(terminal.getCodeName());
				terminalInfo.setIp(terminal.getIp());
				terminalInfo.setMac(terminal.getMac());
//				terminalInfo.setHardwares(HardwareHandler.getHardwares(terminal.getMac()));
				terminalInfo.setHardwares(HardwareHandler.getHardwareList(terminal.getMac()));
				terminalInfoList.add(terminalInfo);
			});
		}
		return JSONArray.fromObject(terminalInfoList).toString();
	}


	/**
	 * 获取所有设备 以及设备信息
	 */
	@OpenAccess
	@RequestMapping("terminalList")
	@ResponseBody
	public List<TerminalInfo> getList(){
		List<TerminalInfo> terminalInfoList = new ArrayList<>();
		List<TerminalMybatis> list = terminalService.getList();
		list.forEach(terminalMybatis -> {
			TerminalInfo terminalInfo = new TerminalInfo();
			terminalInfo.setId(terminalMybatis.getId());
			terminalInfo.setName(terminalMybatis.getName());
			terminalInfo.setStatus(terminalMybatis.getStatus());
			terminalInfo.setCodeName(terminalMybatis.getCodeName());
			terminalInfo.setIp(terminalMybatis.getIp());
			terminalInfo.setMac(terminalMybatis.getMac());
			terminalInfo.setHardwares(HardwareHandler.getHardwareList(terminalMybatis.getMac()));
			terminalInfoList.add(terminalInfo);
		});
		return terminalInfoList;
	}

	/**
	 * 通过mac地址获取当前设备的信息
	 */
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
	 * 新增---监控调用接口
	 */
	@OpenAccess
	@RequestMapping("insert")
	@ResponseBody
	public R save(TerminalMybatis terminalMybatis){
		terminalService.insertBySelect(terminalMybatis);
		return  R.ok();
	}
	/**
	 * 更新--监控调用接口
	 */
	@OpenAccess
	@RequestMapping("update")
	@ResponseBody
	public R update(TerminalMybatis terminalMybatis){
		terminalService.updateBySelect(terminalMybatis);
		return  R.ok();
	}

}
