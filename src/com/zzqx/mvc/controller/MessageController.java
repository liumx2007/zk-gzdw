package com.zzqx.mvc.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzqx.mvc.annotation.OpenAccess;
import com.zzqx.mvc.entity.Message;
import com.zzqx.mvc.service.MessageService;
import com.zzqx.mvc.service.PersonnelService;
import com.zzqx.mvc.service.WorkPositionService;
import com.zzqx.support.framework.mina.androidser.AndroidConstant;
import com.zzqx.support.utils.DateManager;

import net.sf.json.JSONArray;


@Controller
@RequestMapping(value = "/message")
public class MessageController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private PersonnelService personnelService;
	@Autowired
	private WorkPositionService workPositionService;
	@OpenAccess
	@ResponseBody
	@RequestMapping("getMsgByPerson")
	public String getMsgByPerson(HttpServletRequest request,HttpServletResponse response) {
		
		String watchCode = request.getParameter("watchCode");
		List<Integer> status = new ArrayList<Integer>();
		status.add(AndroidConstant.MESSAGE_STATE_READ_KEY);
		status.add(AndroidConstant.MESSAGE_STATE_UNREAD_KEY);
//		List<Message> list = messageService.find(Restrictions.eq("watch_code",watchCode),
//							Restrictions.in("statu", status),
//							Restrictions.ilike("create_time", DateManager.date2Str(DateManager.date_sdf),MatchMode.ANYWHERE));
		Query query = messageService.createQuery("from Message t where t.watch_code=? and t.statu not in (?,?) and t.create_time like ? order by t.ordertime desc", new Object[]{watchCode,AndroidConstant.MESSAGE_STATE_PROCESSED_KEY,AndroidConstant.MESSAGE_STATE_EXPIRED_KEY,DateManager.date2Str(DateManager.date_sdf)+"%"});
		List<Message> list = query.list();
		JSONArray json = JSONArray.fromObject(list);
		return json.toString();
	}
	
	
	
	/**
	 * 给手表调用的方法
	 * 修改消息状态
	 * @param request
	 */
	@OpenAccess
	@ResponseBody
	@RequestMapping("watchUpdateStatus")
	public String watchUpdateStatus(HttpServletRequest request) {
		int statu = Integer.valueOf(request.getParameter("statu"));
		String msgId = request.getParameter("msgId");
		logger.info("收到将要修改的消息状态key参数:"+statu);
		logger.info("收到将要修改的消息ID参数："+msgId);
		Message msg = messageService.getById(msgId);
		if(msg!=null){
			if(msg.getStatu()==statu){
				return "消息，状态无变化";
			}
			msg.setStatu(statu);
			msg.setEdit_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			messageService.saveOrUpdate(msg);
			logger.info(msgId+"消息，状态修改成功！");
		}
		return "消息，状态修改成功";
	}

}
