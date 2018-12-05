package com.zzqx.mvc.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zzqx.mvc.entity.Picture;
import com.zzqx.support.utils.file.CodeManager;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.jetsum.core.orm.entity.Page;
import com.zzqx.mvc.annotation.OpenAccess;
import com.zzqx.mvc.entity.Order;
import com.zzqx.mvc.entity.User;
import com.zzqx.mvc.javabean.ReturnData;
import com.zzqx.mvc.javabean.ReturnMessage;
import com.zzqx.mvc.service.OrderService;
import com.zzqx.mvc.service.PictureService;
import com.zzqx.support.utils.StringHelper;
import com.zzqx.support.utils.file.FileManager;
import com.zzqx.support.utils.file.PropertiesHelper;
import com.zzqx.support.utils.net.SocketDataSender;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

@Controller
@RequestMapping(value = "/order")
public class OrderController extends BaseController {
	
	 @InitBinder
	 public void initBinder(WebDataBinder binder) {
		 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 dateFormat.setLenient(false);
		 //binder.registerCustomEditor(Date.class, "date",  new CustomDateEditor(dateFormat, true));
		 binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		 //true:允许输入空值，false:不能为空值, "date"变量名
	}

	@Autowired
	private OrderService orderService;
	@Autowired
	private PictureService scanPictureService;
	
	@OpenAccess
	@RequestMapping(method = RequestMethod.GET)
	public String order(ModelMap model) {
		List<Order> list = orderService.getAll();
		model.addAttribute("list", list);
		return "order/order";
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping(value = "get")
	public String get(String id) {
		Order order = orderService.getById(id);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		return JSONObject.fromObject(order, jsonConfig).toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping(value = "visit")
	public String visit(String id, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "预约访问状态更新成功！");
		Order order = orderService.getById(id);
		Order lastOrder = orderService.getCurrent();
		if(order==null || StringHelper.isNotBlank(id)) {
			message.setMessage("该预约访问不存在！");
			message.setType(ReturnMessage.MESSAGE_ERROR);
			return message.toString();
		}
		Order current;
		List<Order> list = orderService.find(Restrictions.eq("status", Order.STATUS_CURRENT));
		if(list.size() > 0) {
			current = list.get(0);
			current.setStatus(Order.STATUS_VISITED);
			orderService.saveOrUpdate(current);
		}
		if(StringHelper.isBlank(id)) {
			GregorianCalendar curDate = new GregorianCalendar();
			String apm = "上午";
			if(curDate.get(Calendar.AM_PM) == Calendar.PM) {
				apm = "下午";
			}
			Date date = new Date();
			@SuppressWarnings("unchecked")
			List<Order> todayNots = orderService.createQuery(
					"from Order where status = ? and apm = ? and date=:date order by date asc,addTime asc", Order.STATUS_NOT, apm)
					.setDate("date", date).list();
			if(todayNots.size() > 0) {
				order = todayNots.get(0);
				order.setStatus(Order.STATUS_CURRENT);
			} else {
				message.setMessage(new SimpleDateFormat("yyyy-MM-dd").format(date)+" "+apm+"，没有处于预约中状态的客户！");
				message.setType(ReturnMessage.MESSAGE_NOTICE);
				return message.toString();
			}
		} else {
			order.setStatus(Order.STATUS_CURRENT);
		}
		orderService.saveOrUpdate(order);
		String realPath = request.getSession().getServletContext().getRealPath("/");
		if(lastOrder != null) {
			FileManager.deleteFile(realPath, lastOrder.getQrcode());
			Set<Picture> pictures = lastOrder.getPictures();
			pictures.forEach(picture->{
				FileManager.deleteFile(realPath, picture.getFilePath());
				FileManager.deleteFile(realPath, picture.getSubFilePath());
				scanPictureService.delete(id);
			});
		}
		SocketDataSender sender = new SocketDataSender();
		PropertiesHelper props = new PropertiesHelper("config");
		String ipsString = props.readValueTrim("picture.scan.notice.terminal");
		if(ipsString != null) {
			for(String ip : ipsString.split(",")) {
				sender.sendToTerminal(ip, props.readValueTrim("picture.scan.notice.message.add"));
			}
		}
		message.setData(order);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		return message.toString(jsonConfig);
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping(value="list")
	public String list(String page, String rows, Date sDate, Date eDate, HttpServletRequest request) {
		int pageNo = StringHelper.isEmpty(page)?1:Integer.parseInt(page);
		int pageSize =  StringHelper.isEmpty(rows)?10:Integer.parseInt(rows);
		Map<String, Object> map = getQueryParameter(request);
		map.put("date_ge", sDate);
		map.put("date_le", eDate);
		Page<Order> thisPage = orderService.getByPage(map, pageNo, pageSize, "status,date,apm", "desc,desc,asc");
		List<Order> list = thisPage.getResult();
		ReturnData data = new ReturnData(thisPage.getTotalCount(), list);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		return data.toString(jsonConfig);
	}
	
	@OpenAccess
	@RequestMapping(value="/code/{id}", method = RequestMethod.GET)
	public String code(@PathVariable("id") String id, ModelMap model) {
		model.addAttribute("order", orderService.getById(id));
		return "order/code";
	}
	
	@SuppressWarnings("unchecked")
	@OpenAccess
	@ResponseBody
	@RequestMapping("add")
	public String add(Order order, String code, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Object codeObject = session.getAttribute("code");
		if(user == null && codeObject == null) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("预约失败！");
			return message.toString();
		}
		if(user == null && (StringHelper.isEmpty(code) || !codeObject.toString().equalsIgnoreCase(code))) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("验证码有误！");
			return message.toString();
		}
		if(StringHelper.isEmpty(order.getName()) || order.getDate() == null || 
				StringHelper.isEmpty(order.getApm())) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("预约失败！");
			return message.toString();
		}
		if(user == null) {
			List<Order> list = orderService.find(Restrictions.eq("date", order.getDate()), 
					Restrictions.eq("apm", order.getApm()));
			if(list.size()>0) {
				message.setType(ReturnMessage.MESSAGE_ERROR);
				message.setMessage("预约失败！该时间段已经有人预约，请刷新页面换个时间参观。");
				return message.toString();
			}
		}
		String savePath = FileManager.getNormalPath("files/code");
		String realPath = request.getSession().getServletContext().getRealPath(savePath);
		String codeName = FileManager.getNewFileName("png");
		File codeFile = new File(realPath, codeName);
		order.setQrcode(savePath+codeName);
		order.setAddTime(new Date());
		order.setStatus(Order.STATUS_NOT);
		if(Order.OPERATOR_CUSTOMER.equals(order.getOperator()) || order.getOperator() == null || user == null) {
			order.setOperator(Order.OPERATOR_CUSTOMER);
		} else {
			order.setOperator(user.getRealName());
		}
		@SuppressWarnings("rawtypes")
		Map hints = new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		PropertiesHelper props = new PropertiesHelper("config");
		String sideLengthStr = props.readValue("order.code.side");
		int sideLength = 500;
		if(StringHelper.isNumeric(sideLengthStr)) {
			sideLength = Integer.valueOf(sideLengthStr);
			sideLength = sideLength > 800 ? 800 : sideLength;
		}
		try {
			boolean b = codeFile.createNewFile();
			if(!b) {
				message.setType(ReturnMessage.MESSAGE_ERROR);
				message.setMessage("生成二维码失败，预约不成功！");
				message.setData(order.getId());
				return message.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		orderService.saveOrUpdate(order);
		CodeManager.getInstance().encode(order.getId(), codeFile, BarcodeFormat.QR_CODE, sideLength, sideLength, hints);
		message.setType(ReturnMessage.MESSAGE_SUCCESS);
		message.setMessage("预约成功！");
		message.setData(order.getId());
		session.removeAttribute("code");
		return message.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "edit")
	public String edit(Order order, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "修改成功！");
		if(StringHelper.isEmpty(order.getName()) || StringHelper.isEmpty(order.getTelephone())
				|| StringHelper.isEmpty(order.getApm()) || order.getDate() == null) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("修改失败");
			return message.toString();
		}
		if(user == null) {
			List<Order> list1 = orderService.find(Restrictions.eq("date", order.getDate()), 
					Restrictions.eq("apm", order.getApm()),
					Restrictions.ne("id", order.getId()));
			if(list1.size()>0) {
				message.setType(ReturnMessage.MESSAGE_ERROR);
				message.setMessage("预约失败！该时间段已经有人预约，请刷新页面换个时间参观。");
				return message.toString();
			}
		}
		List<Order> list2 = orderService.find(Restrictions.eq("status", order.getStatus()), 
				Restrictions.ne("id", order.getId()));
		if(list2.size() > 0) {
			Order oo = list2.get(0);
			oo.setStatus(Order.STATUS_VISITED);
			orderService.saveOrUpdate(oo);
		}
		Order o = orderService.getById(order.getId());
		order.setAddTime(o.getAddTime());
		order.setQrcode(o.getQrcode());
		order.setOperator(o.getOperator());
		BeanUtils.copyProperties(order, o);
		orderService.saveOrUpdate(o);
		return message.toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping(value = "endVisit")
	public String endVisit() {
		Order order = orderService.getCurrent();
		order.setStatus(Order.STATUS_VISITED);
		orderService.saveOrUpdate(order);
		SocketDataSender sender = new SocketDataSender();
		PropertiesHelper props = new PropertiesHelper("config");
		String ipsString = props.readValueTrim("picture.scan.notice.terminal");
		if(ipsString != null) {
			for(String ip : ipsString.split(",")) {
				sender.sendToTerminal(ip, props.readValueTrim("picture.scan.notice.message"));
			}
		}
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "状态更新成功！");
		return message.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "delete")
	public String delete(String id, HttpServletRequest request) {
		List<Order> list = orderService.get(id.split(","));
		String realPath = request.getSession().getServletContext().getRealPath("/");
		list.forEach(order -> {
			FileManager.deleteFile(realPath, order.getQrcode());
		});
		orderService.delete(id.split(","));
		return new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "删除成功！").toString();
	}

	@Override
	protected Map<String, String> getQueryParameterType() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("status_eq", "Integer");
		return map;
	}
	
}
