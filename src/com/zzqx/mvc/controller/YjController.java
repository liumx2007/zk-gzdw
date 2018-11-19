package com.zzqx.mvc.controller;

import com.jetsum.core.orm.entity.Page;
import com.zzqx.Global;
import com.zzqx.mvc.annotation.OpenAccess;
import com.zzqx.mvc.entity.Dkh;
import com.zzqx.mvc.entity.Order;
import com.zzqx.mvc.entity.Station;
import com.zzqx.mvc.entity.Yj;
import com.zzqx.mvc.javabean.ReturnData;
import com.zzqx.mvc.javabean.ReturnMessage;
import com.zzqx.mvc.service.DkhService;
import com.zzqx.mvc.service.StationService;
import com.zzqx.mvc.service.YjService;
import com.zzqx.support.framework.mina.Mina;
import com.zzqx.support.utils.StringHelper;
import com.zzqx.support.utils.file.FileManager;
import com.zzqx.support.utils.net.SocketDataSender;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping(value = "/yj")
public class YjController extends BaseController {
	
	@InitBinder
	 public void initBinder(WebDataBinder binder) {
		 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 dateFormat.setLenient(false);
		 //binder.registerCustomEditor(Date.class, "date",  new CustomDateEditor(dateFormat, true));
		 binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		 //true:允许输入空值，false:不能为空值, "date"变量名
	}
	
	@Autowired
	private YjService yjService;
	
	@Autowired
	private StationService stationService;
	
	@Autowired
	private DkhService dkhService;
	
	@OpenAccess
	@ResponseBody
	@RequestMapping(value = "get")
	public String get(String id) {
		Yj yj = yjService.getById(id);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		return JSONObject.fromObject(yj, jsonConfig).toString();
	}

	@OpenAccess
	@ResponseBody
	@RequestMapping("list")
	public String list(String page, String rows, Date sDate, Date eDate,String isSearch, HttpServletRequest request) {
		int pageNo = StringHelper.isEmpty(page)?1:Integer.parseInt(page);
		int pageSize =  StringHelper.isEmpty(rows)?10:Integer.parseInt(rows);
		Map<String, Object> map;
		if("1".equals(isSearch)) {
			map = getQueryParameter(request);
			map.put("addTime_ge", sDate);
			if(eDate != null) {
				Calendar para = Calendar.getInstance();  
		        para.setTime(eDate);  
		        para.set(Calendar.HOUR_OF_DAY, 23);
		        para.set(Calendar.MINUTE, 59);
		        para.set(Calendar.SECOND, 59); 
				map.put("addTime_le", para.getTime());
			}
		} else {
			map = new HashMap<String, Object>();
			map.put("isShow_eq", 1);
		}
		Page<Yj> thisPage = yjService.getByPage(map, pageNo, pageSize, "isShow,flag,flagTime,addTime", "desc,desc,desc,desc");
		List<Yj> list = thisPage.getResult();
		ReturnData data = new ReturnData(thisPage.getTotalCount(), list);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		return data.toString(jsonConfig);
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("getAll")
	public String getAll() {
		List<Yj> list = yjService.createQuery("from Yj order by isShow desc,flag desc,flagTime desc,addTime desc").list();
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray.toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("add")
	public String add(Yj yj, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "添加成功！");
		if(StringHelper.isBlank(yj.getContent())) {
			message.setMessage("内容不能为空！");
		} else {
			if(yj.getContent().length()>3000) {
				yj.setContent(yj.getContent().substring(0, 3000));
			}
			yj.setAddTime(new Date());
			yj.setFlag(0);
			yj.setIsShow(1);
			yj.setHasFk(0);
			yj.setFeedback("");
			yj.setStatus(0);
			yjService.saveOrUpdate(yj);
			SocketDataSender sender = new SocketDataSender();
			sender.boardcast("message", Mina.SOCKET_CLIENT_TYPE_CONTROLLER);
		}
		return message.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "edit")
	public String edit(Yj yj, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "修改成功！");
		if(StringHelper.isEmpty(yj.getContent())) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("修改失败，内容不能为空！");
			return message.toString();
		}
		Yj thisYj = yjService.getById(yj.getId());
		if(thisYj == null || StringHelper.isBlank(thisYj.getId())) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("修改失败！");
			return message.toString();
		}
		thisYj.setContent(yj.getContent());
		thisYj.setName(yj.getName());
		thisYj.setType(yj.getType());
		thisYj.setPhone(yj.getPhone());
		if(yj.getFeedback().length()>3000) {
			yj.setFeedback(yj.getFeedback().substring(0, 3000));
		} else {
			thisYj.setFeedback(yj.getFeedback());
		}
		thisYj.setHasFk(yj.getHasFk());
		thisYj.setBh(yj.getBh());
		if(yj.getHasFk() == 1) {
			thisYj.setFeedbackTime(new Date());
		}
		thisYj.setStatus(1);
		yjService.saveOrUpdate(thisYj);
		SocketDataSender sender = new SocketDataSender();
		sender.boardcast("message", Mina.SOCKET_CLIENT_TYPE_CONTROLLER);
		return message.toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping(value = "feedback")
	public String feedback(String id, String feedback, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "反馈成功！");
		Yj thisYj = yjService.getById(id);
		if(thisYj != null && StringHelper.isNotBlank(thisYj.getId())) {
			if(StringHelper.isNotBlank(feedback)) {
				thisYj.setFeedback(feedback);
				thisYj.setHasFk(1);
				thisYj.setFeedbackTime(new Date());
			} else {
				thisYj.setFeedback("");
				thisYj.setHasFk(0);
				thisYj.setFeedbackTime(null);
			}
			yjService.saveOrUpdate(thisYj);
		} else {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("反馈失败！");
		}
		return message.toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("getUnreadMessage")
	public String getUnreadMessage() {
		List<Yj> list = yjService.find(Restrictions.eq("status", 0));
		return JSONArray.fromObject(list).toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("updateStatus")
	public String updateStatus() {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "状态更新成功！");
		yjService.executeHql("update Yj set status = 1 where status = 0");
		SocketDataSender sender = new SocketDataSender();
		sender.boardcast("message", Mina.SOCKET_CLIENT_TYPE_CONTROLLER);
		return message.toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("toggle")
	public String toggle(Yj yj, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "操作成功！");
		if(StringHelper.isBlank(yj.getId())) {
			message.setMessage("无效的记录！");
			return message.toString();
		}
		if(1!=yj.getIsShow() && 0!=yj.getIsShow()) {
			message.setMessage("无效的显示状态！");
			return message.toString();
		}
		Yj thisYj = yjService.getById(yj.getId());
		thisYj.setIsShow(yj.getIsShow());
		thisYj.setFlag(yj.getFlag());
		if(thisYj.getFlag() == 1) {
			thisYj.setFlagTime(new Date());
		} else {
			thisYj.setFlagTime(null);
		}
		yjService.saveOrUpdate(thisYj);
		return message.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "delete")
	public String delete(String id, HttpServletRequest request) {
		yjService.delete(id.split(","));
		SocketDataSender sender = new SocketDataSender();
		sender.boardcast("message", Mina.SOCKET_CLIENT_TYPE_CONTROLLER);
		return new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "删除成功！").toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("getStations")
	public String stations() {
		List<Station> stations = stationService.getAll();
		JSONArray jsonArray = JSONArray.fromObject(stations);
		return jsonArray.toString();	
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping(value = "getDkhById")
	public String getDkhById(String id) {
		Dkh dkh = dkhService.getById(id);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		return JSONObject.fromObject(dkh, jsonConfig).toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("getDkhList")
	public String getDkhList() {
		List<Dkh> list = dkhService.getAll();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
	        Class.forName(Global.DBDRIVER);// 动态加载mysql驱动
	        conn = DriverManager.getConnection(Global.DBURL, Global.DBUSER, Global.DBPASS);
			for(Dkh dkh : list) {
				String pId = dkh.getPersonnelId();
				stmt = (PreparedStatement) conn.prepareStatement("select * from tb_personnel where id = '"+pId+"'");
				ResultSet rs = stmt.executeQuery();
				rs.last();//移到最后一行
				int count = rs.getRow();
				rs.beforeFirst();//移到初始位置
				if(count == 0) {
					dkhService.delete(dkh.getId());
				}
				if(rs.next()) {
					dkh.setName(rs.getString("name"));
					dkh.setJobNum(rs.getString("job_num"));
					dkh.setPhoto(rs.getString("photo"));
					dkhService.saveOrUpdate(dkh);
				}
			}
		} catch (Exception e) {
			
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return JSONArray.fromObject(list).toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("getPersonnelList")
	public String getPersonnelList() {
		List<Dkh> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
	        Class.forName(Global.DBDRIVER);// 动态加载mysql驱动
	        conn = DriverManager.getConnection(Global.DBURL, Global.DBUSER, Global.DBPASS);
			stmt = (PreparedStatement) conn.prepareStatement("select * from tb_personnel");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Dkh dkh = new Dkh();
				dkh.setName(rs.getString("name"));
				dkh.setPersonnelId(rs.getString("id"));
				list.add(dkh);
			}
		} catch (Exception e) {
			
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return JSONArray.fromObject(list).toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("addDkh")
	public String addDkh(Dkh dkh, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "添加成功！");
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
	        Class.forName(Global.DBDRIVER);// 动态加载mysql驱动
	        conn = DriverManager.getConnection(Global.DBURL, Global.DBUSER, Global.DBPASS);
	        System.out.println(dkh.getPersonnelId());
			stmt = (PreparedStatement) conn.prepareStatement("select * from tb_dkh where personnel_id = '"+dkh.getPersonnelId()+"'");
			ResultSet rs = stmt.executeQuery();
			rs.last();//移到最后一行
			int count = rs.getRow();
			System.out.println(count);
			rs.beforeFirst();//移到初始位置
			if(count > 0) {
				message.setMessage("添加失败，员工已经存在！");
				message.setType(ReturnMessage.MESSAGE_ERROR);
				return message.toString();
			}
			stmt = (PreparedStatement) conn.prepareStatement("select * from tb_personnel where id = '"+dkh.getPersonnelId()+"'");
			rs = stmt.executeQuery();
			rs.last();//移到最后一行
			count = rs.getRow();
			rs.beforeFirst();//移到初始位置
			if(count == 0) {
				message.setMessage("添加失败，不存在的员工！");
				message.setType(ReturnMessage.MESSAGE_ERROR);
				return message.toString();
			}
			if(rs.next()) {
				MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
				MultipartFile file = multipartHttpServletRequest.getFile("file");
				String suffix = FileManager.getSuffix(file.getOriginalFilename());
				if(file != null && StringHelper.isNotBlank(file.getOriginalFilename())) {
					String savePath = request.getSession().getServletContext().getRealPath("UploadImages");
					FileManager.makeDirectory(savePath);
					String fileName = FileManager.getNewFileName(suffix);
					File saveFile = new File(savePath, fileName+suffix);
					FileUtils.copyInputStreamToFile(file.getInputStream(), saveFile);
					dkh.setDkhPhoto("UploadImages/"+saveFile.getName());
				}
				dkh.setName(rs.getString("name"));
				dkh.setJobNum(rs.getString("job_num"));
				dkh.setPhoto(rs.getString("photo"));
				dkhService.saveOrUpdate(dkh);
			}
		} catch (Exception e) {
			
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return message.toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("editDkh")
	public String editDkh(Dkh dkh, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "修改成功！");
		Dkh thisDkh = dkhService.getById(dkh.getId());
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
	        Class.forName(Global.DBDRIVER);// 动态加载mysql驱动
	        conn = DriverManager.getConnection(Global.DBURL, Global.DBUSER, Global.DBPASS);
	        System.out.println(dkh.getPersonnelId());
			stmt = (PreparedStatement) conn.prepareStatement("select * from tb_dkh where personnel_id = '"+dkh.getPersonnelId()+"' and personnel_id != '"+thisDkh.getPersonnelId()+"'");
			ResultSet rs = stmt.executeQuery();
			rs.last();//移到最后一行
			int count = rs.getRow();
			System.out.println(count);
			rs.beforeFirst();//移到初始位置
			if(count > 0) {
				message.setMessage("修改失败，员工已经存在！");
				message.setType(ReturnMessage.MESSAGE_ERROR);
				return message.toString();
			}
			stmt = (PreparedStatement) conn.prepareStatement("select * from tb_personnel where id = '"+dkh.getPersonnelId()+"'");
			rs = stmt.executeQuery();
			rs.last();//移到最后一行
			count = rs.getRow();
			rs.beforeFirst();//移到初始位置
			if(count == 0) {
				message.setMessage("修改失败，不存在的员工！");
				message.setType(ReturnMessage.MESSAGE_ERROR);
				return message.toString();
			}
			if(rs.next()) {
				MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
				MultipartFile file = multipartHttpServletRequest.getFile("file");
				String suffix = FileManager.getSuffix(file.getOriginalFilename());
				if(file != null && StringHelper.isNotBlank(file.getOriginalFilename())) {
					String savePath = request.getSession().getServletContext().getRealPath("UploadImages");
					FileManager.makeDirectory(savePath);
					String fileName = FileManager.getNewFileName(suffix);
					File saveFile = new File(savePath, fileName+suffix);
					FileUtils.copyInputStreamToFile(file.getInputStream(), saveFile);
					thisDkh.setDkhPhoto("UploadImages/"+saveFile.getName());
				}
				thisDkh.setName(rs.getString("name"));
				thisDkh.setJobNum(rs.getString("job_num"));
				thisDkh.setPhoto(rs.getString("photo"));
				thisDkh.setRole(dkh.getRole());
				dkhService.saveOrUpdate(thisDkh);
			}
		} catch (Exception e) {
			
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return message.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "deleteDkh")
	public String deleteDkh(String id, HttpServletRequest request) {
		dkhService.delete(id.split(","));
		return new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "删除成功！").toString();
	}
	
	@Override
	protected Map<String, String> getQueryParameterType() {
		Map<String, String> map = new HashMap<>();
		map.put("isShow_eq", "Integer");
		map.put("type_eq", "Integer");
		map.put("hasFk_eq", "Integer");
		return map;
	}
}