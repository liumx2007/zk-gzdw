package com.zzqx.mvc.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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

import com.zzqx.Global;
import com.zzqx.mvc.annotation.OpenAccess;
import com.zzqx.mvc.entity.Personnel;
import com.zzqx.mvc.entity.Subject;
import com.zzqx.mvc.entity.SubjectWrong;
import com.zzqx.mvc.entity.User;
import com.zzqx.mvc.javabean.ReturnMessage;
import com.zzqx.mvc.javabean.SubjectWrongData;
import com.zzqx.mvc.service.SubjectService;
import com.zzqx.mvc.service.SubjectWrongService;
import com.zzqx.support.utils.StringHelper;
//import com.zzqx.support.utils.net.SocketData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;


@Controller
@RequestMapping(value = "/hBuilder")
public class HbuilderController extends BaseController {

	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private SubjectWrongService subjectWrongService;
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("checkPersonnel")
	public String checkPersonnel(String userName, String password, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS);
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
	        Class.forName(Global.DBDRIVER);// 动态加载mysql驱动
	        conn = DriverManager.getConnection(Global.DBURL, Global.DBUSER, Global.DBPASS);
	        stmt = (PreparedStatement) conn.prepareStatement("select * from tb_personnel where job_num = '"+userName+"' and pass_work = '"+StringHelper.MD5(password)+"'");
	        ResultSet rs = stmt.executeQuery();
			rs.last();//移到最后一行
			int count = rs.getRow();
			if(count == 0) {
				message.setType(ReturnMessage.MESSAGE_ERROR);
				message.setMessage("用户名或密码错误！");
			} else {
				Personnel personnel = new Personnel();
				personnel.setId(rs.getString("id"));
				personnel.setName(rs.getString("name"));
				personnel.setPhoto(rs.getString("photo"));
				personnel.setSex(rs.getString("sex"));
				personnel.setJob_num(rs.getString("job_num"));
				message.setData(personnel);
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
	@RequestMapping("editPassword")
	public String editPassword(String userName, String password, String newPassword, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "修改成功");
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
	        Class.forName(Global.DBDRIVER);// 动态加载mysql驱动
	        conn = DriverManager.getConnection(Global.DBURL, Global.DBUSER, Global.DBPASS);
	        stmt = (PreparedStatement) conn.prepareStatement("select * from tb_personnel where job_num = '"+userName+"' and pass_work = '"+StringHelper.MD5(password)+"'");
	        ResultSet rs = stmt.executeQuery();
			rs.last();//移到最后一行
			int count = rs.getRow();
			if(count == 0) {
				message.setType(ReturnMessage.MESSAGE_ERROR);
				message.setMessage("验证失败");
			} else {
				stmt.close();
				stmt = (PreparedStatement) conn.prepareStatement("update tb_personnel set pass_work = '"+StringHelper.MD5(newPassword)+"' where job_num = '"+userName+"'");
				int result = stmt.executeUpdate();
				if(result == 0) {
					message.setMessage("修改失败");
					message.setType(ReturnMessage.MESSAGE_ERROR);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
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
	@RequestMapping("getSubject")
	public String getSubject(String id, HttpServletRequest request) {
		Subject subject = new Subject();
		if(StringHelper.isNotBlank(id)) {
			subject = subjectService.getById(id);
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		return JSONObject.fromObject(subject, jsonConfig).toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("getSubjects")
	public String getSubjects(String modal, String type, String number, HttpServletRequest request) {
		String order = "random".equalsIgnoreCase(modal) ? " order by rand()" : " order by addTime";
		String where = StringHelper.isBlank(type) ? "" : " where type = " + type;
		List<Subject> subjects;
		if(StringHelper.isBlank(number)) {
			int count = ((Long)subjectService.createQuery("select count(*) from Subject").uniqueResult()).intValue();
			subjects = subjectService.createQuery("from Subject"+where+order).setMaxResults(count).list();
		} else if(StringHelper.isNumeric(number) && Integer.valueOf(number) > 0) {
			subjects = subjectService.createQuery("from Subject"+where+order).setMaxResults(Integer.valueOf(number)).list();
		} else {
			subjects = new ArrayList<>();
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		return JSONArray.fromObject(subjects, jsonConfig).toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("getPersonnel")
	public String getPersonnel(String userName, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS);
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
	        Class.forName(Global.DBDRIVER);// 动态加载mysql驱动
	        conn = DriverManager.getConnection(Global.DBURL, Global.DBUSER, Global.DBPASS);
	        stmt = (PreparedStatement) conn.prepareStatement("select * from tb_personnel where job_num = '"+userName+"'");
	        ResultSet rs = stmt.executeQuery();
			rs.last();//移到最后一行
			int count = rs.getRow();
			if(count == 0) {
				message.setType(ReturnMessage.MESSAGE_ERROR);
				message.setMessage("无此用户！");
			} else {
				Personnel personnel = new Personnel();
				personnel.setId(rs.getString("id"));
				personnel.setName(rs.getString("name"));
				personnel.setPhoto(rs.getString("photo"));
				personnel.setSex(rs.getString("sex"));
				personnel.setJob_num(rs.getString("job_num"));
				message.setData(personnel);
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
	@RequestMapping("rightSubject")
	public String rightSubject(SubjectWrong subjectWrong, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "操作成功！");
		List<SubjectWrong> list = subjectWrongService.find(Restrictions.eq("personnelId", subjectWrong.getPersonnelId()), Restrictions.eq("subjectId", subjectWrong.getSubjectId()));
		String[] ids = list.stream().map(sw->sw.getId()).toArray(String[]::new);
		subjectWrongService.delete(ids);
		return message.toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("wrongSubject")
	public String wrongSubject(SubjectWrong subjectWrong, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "添加成功！");
		List<SubjectWrong> list = subjectWrongService.find(Restrictions.eq("personnelId", subjectWrong.getPersonnelId()), Restrictions.eq("subjectId", subjectWrong.getSubjectId()));
		if(list.size() > 0) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("添加失败，答题者已经存在该错误记录！");
			return message.toString();
		}
		Subject subject = subjectService.getById(subjectWrong.getSubjectId());
		if(subject == null || StringHelper.isBlank(subject.getId())) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("未知的题目！");
			return message.toString();
		}
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
	        Class.forName(Global.DBDRIVER);// 动态加载mysql驱动
	        conn = DriverManager.getConnection(Global.DBURL, Global.DBUSER, Global.DBPASS);
	        stmt = (PreparedStatement) conn.prepareStatement("select * from tb_personnel where id = '"+subjectWrong.getPersonnelId()+"'");
	        ResultSet rs = stmt.executeQuery();
			rs.last();//移到最后一行
			int count = rs.getRow();
			if(count == 0) {
				message.setType(ReturnMessage.MESSAGE_ERROR);
				message.setMessage("用户不存在！");
				return message.toString();
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
		subjectWrong.setAddTime(new Date());
		subjectWrongService.saveOrUpdate(subjectWrong);
		return message.toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("getWrongSubjectsNumber")
	public String getWrongSubjectsNumber(String personnelId, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS);
		Long number = (Long) subjectWrongService.createQuery("select count(*) from SubjectWrong where personnelId='"+personnelId+"'").uniqueResult();
		message.setData(number);
		return message.toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("getWrongSubjects")
	public String getWrongSubjects(String personnelId, HttpServletRequest request) {
		List<SubjectWrong> wrongSubjects = subjectWrongService.createQuery("from SubjectWrong where personnelId='"+personnelId+"' order by addTime desc").list();
		Personnel personnel = new Personnel();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
	        Class.forName(Global.DBDRIVER);// 动态加载mysql驱动
	        conn = DriverManager.getConnection(Global.DBURL, Global.DBUSER, Global.DBPASS);
	        stmt = (PreparedStatement) conn.prepareStatement("select * from tb_personnel where id = '"+personnelId+"'");
	        ResultSet rs = stmt.executeQuery();
	        if(rs.next()) {
				personnel.setId(rs.getString("id"));
				personnel.setName(rs.getString("name"));
				personnel.setPhoto(rs.getString("photo"));
				personnel.setSex(rs.getString("sex"));
				personnel.setJob_num(rs.getString("job_num"));
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
		List<SubjectWrongData> list = new ArrayList<>();
		for(SubjectWrong subjectWrong : wrongSubjects) {
			SubjectWrongData data = new SubjectWrongData();
			Subject subject = subjectService.getById(subjectWrong.getSubjectId());
			data.setPersonnel(personnel);
			data.setId(subjectWrong.getId());
			data.setAddTime(subjectWrong.getAddTime());
			data.setSubject(subject);
			list.add(data);
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		return JSONArray.fromObject(list, jsonConfig).toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("removeWrongSubject")
	public String removeWrongSubject(String id, String personnelId, HttpServletRequest request) {
		System.out.println(personnelId);
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS);
		if(StringHelper.isNotBlank(id)) {
			subjectWrongService.delete(id.split(","));
		}
		if(StringHelper.isNotBlank(personnelId)) {
			subjectWrongService.executeHql("delete from SubjectWrong where personnelId = '"+personnelId+"'");
		}
		return message.toString();
	}
}
