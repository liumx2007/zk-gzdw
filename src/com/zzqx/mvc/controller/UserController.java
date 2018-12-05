package com.zzqx.mvc.controller;

import java.util.List;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzqx.mvc.annotation.OpenAccess;
import com.zzqx.mvc.entity.User;
import com.zzqx.mvc.javabean.ReturnMessage;
import com.zzqx.mvc.service.UserService;
import com.zzqx.support.utils.StringHelper;
//import com.zzqx.support.utils.net.SocketData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;


@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("all")
	public String all() {
		@SuppressWarnings("unchecked")
		List<User> list = userService.createQuery("from User order by role").list();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		return JSONArray.fromObject(list, jsonConfig).toString();
	}
	
	@ResponseBody
	@RequestMapping("get")
	public String get(String id) {
		User user = userService.getById(id);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		return JSONObject.fromObject(user, jsonConfig).toString();
	}
	
	@ResponseBody
	@RequestMapping("add")
	public String add(User user) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "添加成功！");
		if(StringHelper.isBlank(user.getUserName()) || StringHelper.isBlank(user.getPassword()) || 
				StringHelper.isBlank(user.getRealName())) {
			message.setMessage("添加失败");
			message.setType(ReturnMessage.MESSAGE_ERROR);
			return message.toString();
		}
		List<User> list = userService.find(Restrictions.eq("userName", user.getUserName()));
		if(list.size() > 0) {
			message.setMessage("用户名已经存在！");
			message.setType(ReturnMessage.MESSAGE_ERROR);
			return message.toString();
		}
		user.setRole(User.USER_ROLE_OPERATOR);
		user.setPassword(StringHelper.MD5(user.getPassword()));
		userService.saveOrUpdate(user);
		return message.toString();
	}
	
	@ResponseBody
	@RequestMapping("edit")
	public String edit(User user, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "修改成功！");
		if(StringHelper.isBlank(user.getId()) || StringHelper.isBlank(user.getUserName()) || 
				StringHelper.isBlank(user.getRealName())) {
			message.setMessage("修改失败");
			message.setType(ReturnMessage.MESSAGE_ERROR);
			return message.toString();
		}
		List<User> list = userService.find(Restrictions.eq("userName", user.getUserName()),
				Restrictions.ne("id", user.getId()));
		if(list.size() > 0) {
			message.setMessage("用户名已经存在！");
			message.setType(ReturnMessage.MESSAGE_ERROR);
			return message.toString();
		}
		User oldUser = userService.getById(user.getId());
		if(StringHelper.isNotBlank(user.getPassword())) {
			user.setPassword(StringHelper.MD5(user.getPassword()));
		} else {
			user.setPassword(oldUser.getPassword());
		}
		BeanUtils.copyProperties(user, oldUser);
		userService.saveOrUpdate(oldUser);
		HttpSession session = request.getSession();
		Object current = session.getAttribute("user");
		if(current != null && ((User) current).getId().equals(oldUser.getId())) {
			session.setAttribute("user", current);
		}
		return message.toString();
	}
	
	@ResponseBody
	@RequestMapping("delete")
	public String delete(String id, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "删除成功！");
		HttpSession session = request.getSession();
		User current = (User) session.getAttribute("user");
		if(current.getRole() != User.USER_ROLE_MANAGER) {
			message.setMessage("删除失败");
			message.setType(ReturnMessage.MESSAGE_ERROR);
			return message.toString();
		}
		Stream.of(id.split(",")).filter(s->!"0".equals(s)).forEach(s->userService.delete(s));
		return message.toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("login")
	public String login(User user, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS);
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(24*60*60);
		List<User> list = userService.find(
				Restrictions.eq("userName", user.getUserName()),
				Restrictions.eq("password", StringHelper.MD5(user.getPassword())));
		if(list.size() > 0) {
			session.setAttribute("user", list.get(0));
		} else {
			message.setType(ReturnMessage.MESSAGE_ERROR);
				message.setMessage("帐号或密码错误！");
		}
		return message.toString();
	}
}
