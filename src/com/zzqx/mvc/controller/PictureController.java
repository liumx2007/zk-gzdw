package com.zzqx.mvc.controller;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zzqx.mvc.entity.Picture;
import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jetsum.core.orm.entity.Page;
import com.zzqx.mvc.annotation.OpenAccess;
import com.zzqx.mvc.entity.Order;
import com.zzqx.mvc.javabean.ReturnMessage;
import com.zzqx.mvc.service.OrderService;
import com.zzqx.mvc.service.PictureService;
import com.zzqx.support.utils.StringHelper;
import com.zzqx.support.utils.file.FileManager;
import com.zzqx.support.utils.file.PropertiesHelper;
import com.zzqx.support.utils.net.SocketDataSender;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

@Controller
@RequestMapping(value = "/picture")
public class PictureController extends BaseController {

	@Autowired
	private PictureService pictureService;
	@Autowired
	private OrderService orderService;
	
	@SuppressWarnings("unchecked")
	@OpenAccess
	@ResponseBody
	@RequestMapping("get") 
	public String get(String page, String rows, String orderId, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "获取成功！");
		int pageNo = StringHelper.isBlank(page)?-1:Integer.parseInt(page);
		int pageSize =  StringHelper.isBlank(rows)?-1:Integer.parseInt(rows);
		List<Picture> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		Order order;
		if(StringHelper.isNotBlank(orderId)) {
			order = orderService.getById(orderId);
		} else {
			order = orderService.getCurrent();
		}
		map.put("order_eq", order);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sdf.parse(sdf.format(new Date()));
			map.put("addTime_gt", date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if((pageNo == -1 || pageSize == -1) && order != null && StringHelper.isNotBlank(order.getId())) {
			list = pictureService.createQuery("from Picture where order=:order_eq and addTime >:addTime_gt order by addTime desc", map).list();
		} else if(pageNo > 0 && pageSize > 0){
			Page<Picture> thisPage = pictureService.getByPage(map, pageNo, pageSize, "addTime", "desc");
			list = thisPage.getResult();
		}
		String basePath = this.getBasePath(request);
		list.forEach(picture->{picture.setFilePath(basePath+picture.getFilePath());picture.setSubFilePath(basePath+picture.getSubFilePath());});
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		message.setData(JSONArray.fromObject(list, jsonConfig));
		return message.toString();
	}
	
	@SuppressWarnings("unchecked")
	@OpenAccess
	@ResponseBody
	@RequestMapping("getCurrent") 
	public String getCurrent(String page, String rows, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "获取成功！");
		int pageNo = StringHelper.isBlank(page)?-1:Integer.parseInt(page);
		int pageSize =  StringHelper.isBlank(rows)?-1:Integer.parseInt(rows);
		List<Picture> list;
		Map<String, Object> map = new HashMap<>();
		Order order = orderService.getCurrent();
		if(order != null) {
			map.put("order_eq", order);
		} else {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("未设置当前参观！");
			return message.toString();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sdf.parse(sdf.format(new Date()));
			map.put("addTime_gt", date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(pageNo == -1 || pageSize == -1) {
			list = pictureService.createQuery("from Picture where order=:order_eq and addTime >:addTime_gt order by addTime desc", map).list();
		} else {
			Page<Picture> thisPage = pictureService.getByPage(map, pageNo, pageSize, "addTime", "desc");
			list = thisPage.getResult();
		}
		String basePath = this.getBasePath(request);
		list.forEach(picture->{picture.setFilePath(basePath+picture.getFilePath());picture.setSubFilePath(basePath+picture.getSubFilePath());});
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		message.setData(JSONArray.fromObject(list, jsonConfig));
		return message.toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("upload") 
	public String upload(HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "上传成功！");
		String savePath = FileManager.getNormalPath("files/picture");
		String realPath = request.getSession().getServletContext().getRealPath(savePath);
		FileManager.makeDirectory(realPath);
		String fileName;
		if("application/octet-stream".equals(request.getContentType())) {
			fileName = FileManager.getNewFileName("jpg");
			File file = new File(realPath+fileName);
			FileOutputStream outputStream;
			BufferedInputStream inputStream;
			try {
				byte[] bytes = new byte[1024];
				int v;
				inputStream = new BufferedInputStream(request.getInputStream());
				outputStream = new FileOutputStream(file);
				while((v=inputStream.read(bytes)) != -1){
					outputStream.write(bytes, 0, v);
				}
				outputStream.flush();
				outputStream.close();
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
				message.setMessage("上传失败！");
				message.setType(ReturnMessage.MESSAGE_ERROR);
				return message.toString();
			}
		} else if(StringHelper.startsWith(request.getContentType(), "multipart/form-data")){
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = multipartHttpServletRequest.getFile("file");
			if(file == null || StringHelper.isBlank(file.getOriginalFilename())) {
				message.setType(ReturnMessage.MESSAGE_ERROR);
				message.setMessage("上传失败！");
				return message.toString();
			}
			String suffix = FileManager.getSuffix(file.getOriginalFilename());
			suffix = StringHelper.isBlank(suffix)?".jpg":suffix;
			fileName = FileManager.getNewFileName(suffix);
			try {
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath+fileName));
			} catch (IOException e) {
				e.printStackTrace();
				message.setMessage("上传失败！");
				message.setType(ReturnMessage.MESSAGE_ERROR);
				return message.toString();
			}
		} else {
			message.setMessage("上传失败！");
			message.setType(ReturnMessage.MESSAGE_ERROR);
			return message.toString();
		}
		String subFileName = FileManager.getFileNameWithOutSuffix(fileName)+"_th.jpg";
		FileManager.processImg(realPath+fileName, realPath+subFileName, "image/*");
		Order order = orderService.getCurrent();
		Picture picture = new Picture();
		picture.setOrder(order);
		picture.setAddTime(new Date());
		picture.setFileName(fileName);
		picture.setFilePath(savePath+fileName);
		picture.setSubFileName(subFileName);
		picture.setSubFilePath(savePath+subFileName);
		picture.setType(Picture.TYPE_CAMERA);
		pictureService.saveOrUpdate(picture);
		SocketDataSender sender = new SocketDataSender();
		PropertiesHelper props = new PropertiesHelper("config");
		String ipsString = props.readValueTrim("picture.scan.notice.terminal");
		if(ipsString != null) {
			for(String ip : ipsString.split(",")) {
				sender.sendToTerminal(ip, props.readValueTrim("picture.scan.notice.message.add"));
			}
		}
		message.setData(picture.getId());
		return message.toString();
	}

	@OpenAccess
	@ResponseBody
	@RequestMapping("upload2")
	public String upload2(HttpServletRequest request) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = sdf.format(new Date());
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "上传成功！");
		String savePath = FileManager.getNormalPath("files/screenCapture")+dateString+"/";
		String realPath = request.getSession().getServletContext().getRealPath(savePath);
		FileManager.makeDirectory(realPath);
		String fileName;
		if("application/octet-stream".equals(request.getContentType())) {
			fileName = FileManager.getNewFileName("jpg");
			File file = new File(realPath+fileName);
			FileOutputStream outputStream;
			BufferedInputStream inputStream;
			try {
				byte[] bytes = new byte[1024];
				int v;
				inputStream = new BufferedInputStream(request.getInputStream());
				outputStream = new FileOutputStream(file);
				while((v=inputStream.read(bytes)) != -1){
					outputStream.write(bytes, 0, v);
				}
				outputStream.flush();
				outputStream.close();
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
				message.setMessage("上传失败！");
				message.setType(ReturnMessage.MESSAGE_ERROR);
				return message.toString();
			}
		} else if(StringHelper.startsWith(request.getContentType(), "multipart/form-data")){
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = multipartHttpServletRequest.getFile("file");
			if(file == null || StringHelper.isBlank(file.getOriginalFilename())) {
				message.setType(ReturnMessage.MESSAGE_ERROR);
				message.setMessage("上传失败！");
				return message.toString();
			}
			String suffix = FileManager.getSuffix(file.getOriginalFilename());
			suffix = StringHelper.isBlank(suffix)?".jpg":suffix;
			fileName = FileManager.getNewFileName(suffix);
			try {
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath+fileName));
			} catch (IOException e) {
				e.printStackTrace();
				message.setMessage("上传失败！");
				message.setType(ReturnMessage.MESSAGE_ERROR);
				return message.toString();
			}
		} else {
			message.setMessage("上传失败！");
			message.setType(ReturnMessage.MESSAGE_ERROR);
			return message.toString();
		}
		String subFileName = FileManager.getFileNameWithOutSuffix(fileName)+"_th.jpg";
		FileManager.processImg(realPath+fileName, realPath+subFileName, "image/*");
		Order order = orderService.getCurrent();
		Picture picture = new Picture();
		picture.setOrder(order);
		picture.setAddTime(new Date());
		picture.setFileName(fileName);
		picture.setFilePath(savePath+fileName);
		picture.setSubFileName(subFileName);
		picture.setSubFilePath(savePath+subFileName);
		picture.setType(Picture.TYPE_SCREENCAPTURE);
		pictureService.saveOrUpdate(picture);
		SocketDataSender sender = new SocketDataSender();
		String terminalMark = request.getParameter("target");
		String noticeMessage = picture.getFilePath();
		if(terminalMark != null) {
			for(String mark : terminalMark.split(",")) {
				sender.sendToTerminal(mark, noticeMessage);
			}
		}
		message.setData(picture.getId());
		return message.toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("delete")
	public String delete(String id, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "删除成功！");
		Picture picture = pictureService.getById(id);
		String savePath = request.getSession().getServletContext().getRealPath("");
		FileManager.deleteFile(savePath, picture.getFilePath());
		FileManager.deleteFile(savePath, picture.getSubFilePath());
		pictureService.delete(id);
		SocketDataSender sender = new SocketDataSender();
		PropertiesHelper props = new PropertiesHelper("config");
		String ipsString = props.readValueTrim("picture.scan.notice.terminal");
		if(ipsString != null) {
			for(String ip : ipsString.split(",")) {
				sender.sendToTerminal(ip, props.readValueTrim("picture.scan.notice.message.remove"));
			}
		}
		return message.toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("clear")
	public String clear(String orderId, String type, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "清空成功！");
		List<Picture> pictures;
		if(StringHelper.isBlank(orderId)){
			if(StringHelper.isBlank(type)) {
				pictures = pictureService.getAll();
			} else if(StringHelper.isNumeric(type)){
				pictures = pictureService.find(Restrictions.eq("type", Integer.valueOf(type)));
			} else {
				message.setType(ReturnMessage.MESSAGE_ERROR);
				message.setMessage("清空失败，未知的图片分类！");
				return message.toString();
			}
		} else {
			Order order = orderService.getById(orderId);
			if(order != null) {
				pictures = pictureService.find(Restrictions.eq("order", order));
			} else {
				message.setType(ReturnMessage.MESSAGE_ERROR);
				message.setMessage("清空失败，预约不存在！");
				return message.toString();
			}
		}
		String savePath = request.getSession().getServletContext().getRealPath("");
		for(Picture picture : pictures) {
			FileManager.deleteFile(savePath, picture.getFilePath());
			FileManager.deleteFile(savePath, picture.getSubFilePath());
		}
		String[] ids = pictures.stream().map(Picture::getId).toArray(String[]::new);
		pictureService.delete(ids);
		SocketDataSender sender = new SocketDataSender();
		PropertiesHelper props = new PropertiesHelper("config");
		String ipsString = props.readValueTrim("picture.scan.notice.terminal");
		if(ipsString != null) {
			for(String ip : ipsString.split(",")) {
				sender.sendToTerminal(ip, props.readValueTrim("picture.scan.notice.message.clear"));
			}
		}
		return message.toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("getURLById")
	public String getURLById(String id, HttpServletRequest request) {
		Picture picture = pictureService.getById(id);
		if(picture == null) {
			return "";
		}
		String basePath = this.getBasePath(request);
		return basePath+picture.getFilePath();
	}

	@Override
	protected Map<String, String> getQueryParameterType() {
		Map<String, String> map = new HashMap<>();
		map.put("orderId_eq", "Integer");
		return map;
	}
}
