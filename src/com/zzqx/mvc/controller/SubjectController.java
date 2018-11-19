package com.zzqx.mvc.controller;

import com.jetsum.core.orm.entity.Page;
import com.zzqx.Global;
import com.zzqx.mvc.annotation.OpenAccess;
import com.zzqx.mvc.entity.Answer;
import com.zzqx.mvc.entity.Subject;
import com.zzqx.mvc.javabean.ReturnData;
import com.zzqx.mvc.javabean.ReturnMessage;
import com.zzqx.mvc.service.AnswerService;
import com.zzqx.mvc.service.SubjectService;
import com.zzqx.support.utils.StringHelper;
import com.zzqx.support.utils.file.FileManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping(value = "/subject")
public class SubjectController extends BaseController {
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private AnswerService answerService;
	
	@OpenAccess
	@ResponseBody
	@RequestMapping(value = "get")
	public String get(String id) {
		Subject subject = subjectService.getById(id);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		return JSONObject.fromObject(subject, jsonConfig).toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("list")
	public String list(String page, String rows, HttpServletRequest request) {
		int pageNo = StringHelper.isEmpty(page)?1:Integer.parseInt(page);
		int pageSize =  StringHelper.isEmpty(rows)?10:Integer.parseInt(rows);
		Map<String, Object> map = getQueryParameter(request);
		Page<Subject> thisPage = subjectService.getByPage(map, pageNo, pageSize, "type", "asc");
		List<Subject> list = thisPage.getResult();
		ReturnData data = new ReturnData(thisPage.getTotalCount(), list);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		return data.toString(jsonConfig);
	}
	
	@ResponseBody
	@RequestMapping("add")
	public String add(Subject subject, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "添加成功！");
		subject.setType(0);
		if(StringHelper.isBlank(subject.getContent())) {
			message.setMessage("题目不能为空！");
		} else {
			subjectService.saveOrUpdate(subject);
		}
		return message.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "edit")
	public String edit(Subject subject, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "修改成功！");
		if(StringHelper.isEmpty(subject.getContent())) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("修改失败，题目不能为空！");
			return message.toString();
		}
		Subject thisSubject = subjectService.getById(subject.getId());
		if(thisSubject == null || StringHelper.isBlank(thisSubject.getId())) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("修改失败！");
			return message.toString();
		}
		thisSubject.setContent(subject.getContent());
		thisSubject.setExplainDetail(subject.getExplainDetail());
		subjectService.saveOrUpdate(thisSubject);
		return message.toString();
	}
	
	@RequestMapping("answers")
	public String answers(String id, ModelMap map) {
		map.put("subjectId", id);
		return "subject/answers";
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("getAnswers")
	public String getAnswers(String subjectId, HttpServletRequest request) {
		Subject subject = subjectService.getById(subjectId);
		List<Answer> answers;
		if(subject == null) {
			answers = new ArrayList<Answer>();
		} else {
			answers = answerService.createQuery("from Answer where subject = ? order by sort", subject).list();
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		return JSONArray.fromObject(answers, jsonConfig).toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("getAnswer")
	public String getAnswer(String id, HttpServletRequest request) {
		Answer answer = answerService.getById(id);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		return JSONObject.fromObject(answer, jsonConfig).toString();
	}
	
	@ResponseBody
	@RequestMapping("addAnswer")
	public String addAnswer(String subjectId, Answer answer, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "修改成功！");
		if(answer == null) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			return message.toString();
		}
		Subject subject = subjectService.getById(subjectId);
		List<Answer> answerList = answerService.find(Restrictions.eq("subject", subject), Restrictions.eq("content", answer.getContent().trim()));
		if(answerList.size() > 0) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("该答案已经存在！");
			return message.toString();
		}
		int max = answerService.getMaxSort(subject.getId());
		answer.setSort(max+1);
		Set<Answer> answers = subject.getAnswers();
		answers.add(answer);
		answerService.saveOrUpdate(answer);
		List<Answer> rightAnswers = answerService.find(Restrictions.eq("subject", subject), Restrictions.eq("isRight", 1));
		if(rightAnswers.size() > 1 && subject.getType() != 2) {
			subject.setType(2);
			subjectService.saveOrUpdate(subject);
		} else if(rightAnswers.size() == 1 && subject.getType() != 1) {
			subject.setType(1);
			subjectService.saveOrUpdate(subject);
		} else if(rightAnswers.size() == 0) {
			subject.setType(0);
			subjectService.saveOrUpdate(subject);
		}
		return message.toString();
	}
	
	@ResponseBody
	@RequestMapping("editAnswer")
	public String editAnswer(Answer answer, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "修改成功！");
		if(StringHelper.isEmpty(answer.getContent())) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("修改失败，题目不能为空！");
			return message.toString();
		}
		Answer thisAnswer = answerService.getById(answer.getId());
		if(thisAnswer == null || StringHelper.isBlank(thisAnswer.getId())) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("修改失败！");
			return message.toString();
		}
		thisAnswer.setContent(answer.getContent());
		thisAnswer.setIsRight(answer.getIsRight());
		answerService.saveOrUpdate(thisAnswer);
		Subject subject = thisAnswer.getSubject();
		List<Answer> rightAnswers = answerService.find(Restrictions.eq("subject", subject), Restrictions.eq("isRight", 1));
		if(rightAnswers.size() > 1 && subject.getType() != 2) {
			subject.setType(2);
			subjectService.saveOrUpdate(subject);
		} else if(rightAnswers.size() == 1 && subject.getType() != 1) {
			subject.setType(1);
			subjectService.saveOrUpdate(subject);
		} else if(rightAnswers.size() == 0) {
			subject.setType(0);
			subjectService.saveOrUpdate(subject);
		}
		return message.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "delete")
	public String delete(String id, HttpServletRequest request) {
		Subject subject;
		if(id.split(",").length > 0) {
			Answer answer = answerService.getById(id.split(",")[0]);
			subject = answer.getSubject();
		} else {
			return new ReturnMessage(ReturnMessage.MESSAGE_ERROR, "删除失败！").toString();
		}
		subjectService.delete(id.split(","));
		List<Answer> rightAnswers = answerService.find(Restrictions.eq("subject", subject), Restrictions.eq("isRight", 1));
		if(rightAnswers.size() > 1 && subject.getType() != 2) {
			subject.setType(2);
			subjectService.saveOrUpdate(subject);
		} else if(rightAnswers.size() == 1 && subject.getType() != 1) {
			subject.setType(1);
			subjectService.saveOrUpdate(subject);
		} else if(rightAnswers.size() == 0) {
			subject.setType(0);
			subjectService.saveOrUpdate(subject);
		}
		return new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "删除成功！").toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "deleteAnswers")
	public String deleteAnswer(String id, HttpServletRequest request) {
		answerService.delete(id.split(","));
		return new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "删除成功！").toString();
	}
	
	@ResponseBody
	@RequestMapping("sortAnswer")
	public String sortAnswer(String direction, String id) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "排序成功！");
		if(StringHelper.isBlank(direction) || StringHelper.isBlank(id)) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("排序失败！");
			return message.toString();
		}
		Answer source = answerService.getById(id);
		Answer target = new Answer();
		int sourceSort = source.getSort();
		if("1".equals(direction)) {
			target = answerService.getCloseSmall(id);
		} else if("-1".equals(direction)) {
			target = answerService.getCloseLarge(id);
		}
		if(target != null) {
			source.setSort(target.getSort());
			target.setSort(sourceSort);
			answerService.saveOrUpdate(target);
			answerService.saveOrUpdate(source);
		}
		return message.toString();
	}
	
	@ResponseBody
	@RequestMapping("addBatch")
	public String addBatch(HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "添加成功！");
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartHttpServletRequest.getFile("file");
		if(file == null || StringHelper.isBlank(file.getOriginalFilename())) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("上传失败！");
			return message.toString();
		}
		String suffix = FileManager.getSuffix(file.getOriginalFilename());
		if(!".csv".equalsIgnoreCase(suffix) && !".xls".equalsIgnoreCase(suffix) && !".xlsx".equalsIgnoreCase(suffix)) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("上传失败！");
			return message.toString();
		}
		List<Subject> subjects = extract(file);
		Connection conn = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		try {
	        Class.forName(Global.DBDRIVER);// 动态加载mysql驱动
	        conn = DriverManager.getConnection(Global.DBURL, Global.DBUSER, Global.DBPASS);
	        stmt1 = (PreparedStatement) conn.prepareStatement("insert into tb_subject(id, content, explain_detail, type) values (?,?,?,?)");
	        stmt2 = (PreparedStatement) conn.prepareStatement("insert into tb_answer(id, content, is_right, sort, subject_id) values (?,?,?,?,?)");
			for(int i=0;i<subjects.size();i++) {
				Subject subject = subjects.get(i);
				String subjectId = UUID.randomUUID().toString();
				stmt1.setString(1, subjectId);
				stmt1.setString(2, subject.getContent());
				stmt1.setString(3, subject.getExplainDetail());
				stmt1.setInt(4, subject.getType());
				stmt1.addBatch();
				for(Answer answer : subject.getAnswers()) {
					stmt2.setString(1, UUID.randomUUID().toString());
					stmt2.setString(2, answer.getContent());
					stmt2.setInt(3, answer.getIsRight());
					stmt2.setInt(4, answer.getSort());
					stmt2.setString(5, subjectId);
					stmt2.addBatch();
				}
			}
			stmt1.executeBatch();
			stmt2.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(stmt1 != null) {
				try {
					stmt1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt2 != null) {
				try {
					stmt2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return message.toString();
	}
	
	private List<Subject> extract(MultipartFile file) {
		List<Subject> subjects = new ArrayList<>();
		if(file != null) {
	        try {
				Class.forName(Global.DBDRIVER);
	        	Workbook book = null;
	        	if(file.getOriginalFilename().toLowerCase().endsWith(".xlsx")) {
					book = new XSSFWorkbook(file.getInputStream());
	        	} else if(file.getOriginalFilename().toLowerCase().endsWith(".xls")) {
	        		book = new HSSFWorkbook(file.getInputStream());
	        	}
	        	if(book != null) {
		    		int sheetsCount = book.getNumberOfSheets();
		    		for (int i = 0; i < sheetsCount; i++) {
		    			Sheet sheet = book.getSheetAt(i);
		    			if(sheet == null) {
		    				continue;
		    			}
		    			int sheetMergeCount = sheet.getNumMergedRegions();
		    			for(int j = 0 ; j < sheetMergeCount ; j++){   
		    				CellRangeAddress ca = sheet.getMergedRegion(j);
		    				int firstColumn = ca.getFirstColumn();
		    				int firstRow = ca.getFirstRow();
		    				int lastColumn = ca.getLastColumn();
		    				int lastRow = ca.getLastRow();
		    		        Row fRow = sheet.getRow(firstRow);
		                    Cell fCell = fRow.getCell(firstColumn);
		    				Subject subject = new Subject();
		    				String subjectContent = getCellValue(fCell).trim();
		    				if(StringHelper.isBlank(subjectContent)) {
		    					continue;
		    				}
		    				subject.setContent(subjectContent);
		    				subject.setAnswers(new HashSet<>());
		    				int right = 0;
		    				for(int k=0;k<lastRow-firstRow+1;k++) {
		    					Row thisRow = sheet.getRow(firstRow+k);
		    					Cell thisCell = thisRow.getCell(lastColumn+1);
		    					Cell nextCell = thisRow.getCell(lastColumn+2);
		    					Answer answer = new Answer();
		    					String answerContent = getCellValue(thisCell).trim();
		    					if(StringHelper.isBlank(answerContent)) {
			    					continue;
			    				}
		    					answer.setContent(answerContent);
		    					int isRight = Double.valueOf(getCellValue(nextCell).trim()).intValue()==1?1:0;
		    					answer.setIsRight(isRight);
		    					right += isRight;
		    					answer.setSort(k+1);
		    					subject.getAnswers().add(answer);
		    				}
		    				subject.setType(right>1?2:1);
		    				if(right > 0) {
		    					subjects.add(subject);
		    				}
		    			}
		    		}
	        	}
	        } catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return subjects;
	}
	
	private String getCellValue(Cell cell){
		String value = "";
	    if(cell == null) return value;    
	    if(cell.getCellType() == Cell.CELL_TYPE_STRING){    
	    	value= cell.getStringCellValue();    
	    }else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){    
	    	value= String.valueOf(cell.getBooleanCellValue());    
	    }else if(cell.getCellType() == Cell.CELL_TYPE_FORMULA){    
	    	value= cell.getCellFormula() ;    
	    }else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
	    	value= String.valueOf(cell.getNumericCellValue());    
	    }
	    return value;    
	}
	
	@Override
	protected Map<String, String> getQueryParameterType() {
		Map<String, String> map = new HashMap<>();
		map.put("type_eq", "Integer");
		return map;
	}
}