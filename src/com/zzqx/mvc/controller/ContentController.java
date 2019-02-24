package com.zzqx.mvc.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.zzqx.mvc.annotation.OpenAccess;
import com.zzqx.mvc.commons.CountInfo;
import com.zzqx.mvc.entity.Content;
import com.zzqx.mvc.entity.Folder;
import com.zzqx.mvc.entity.Queue;
import com.zzqx.mvc.entity.TerminalContent;
import com.zzqx.mvc.javabean.ReturnMessage;
import com.zzqx.mvc.service.ContentService;
import com.zzqx.mvc.service.FolderService;
import com.zzqx.mvc.service.QueueService;
import com.zzqx.mvc.service.TerminalContentService;
import com.zzqx.mvc.vo.ContentStatusVo;
import com.zzqx.support.utils.StringHelper;
import com.zzqx.support.utils.file.FileManager;
import com.zzqx.support.utils.file.PropertiesHelper;
import com.zzqx.support.utils.net.HttpClientUploadFile;
import com.zzqx.support.utils.net.SocketDataSender;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Controller
@RequestMapping(value = "/content")
public class ContentController extends BaseController {

	@Autowired
	private ContentService contentService;
	@Autowired
	private FolderService folderService;
	@Autowired
	private TerminalContentService terminalContentService;
	@Autowired
	private QueueService queueService;
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("get")
	public String get(String id) {
		Content content = contentService.getById(id);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.setExcludes(new String[] { "terminalContents", "contents"});
		return JSONObject.fromObject(content, jsonConfig).toString();
	}
	
	@ResponseBody
	@RequestMapping("add")
	public String add(String localFile, Content content, HttpServletRequest request,HttpServletResponse response) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "上传成功！");
		if(content.getFolder() == null || StringHelper.isEmpty(content.getFolder().getId())) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("上传失败！");
			return message.toString();
		}
		MultipartFile file = null;
		Folder folder = folderService.getById(content.getFolder().getId());
		if(content.getWay() == Content.WAY_UPLOAD) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			 file = multipartHttpServletRequest.getFile("file");
			// 19-1-28
//			try{
//				//登录监控获取token
//				CountInfo countInfo = new CountInfo();
//				// 设置请求的参数
//				JSONObject jsonParam = new JSONObject();
//				jsonParam.put("username", countInfo.USER_NAME);
//				jsonParam.put("password", countInfo.PASSWORD);
//				String json = jsonParam.toString();
//				String data = HttpRequest.post(countInfo.DW_SYSTEM_LOGIN)
//						.body(json,"application/json")
//						.execute().body();
//				cn.hutool.json.JSONObject object = JSONUtil.parseObj(data);
//				Object userInfo = object.get("data");
//				cn.hutool.json.JSONObject object1 = JSONUtil.parseObj(userInfo);
//				String token = (String) object1.get("token");
//				System.out.println("------------------"+token);
////				String userId = (String) object1.get("employeeId");
//				//再上传file，存储对应的文件表
//				HttpClientUploadFile httpClientUploadFile = new HttpClientUploadFile();
//				String s = httpClientUploadFile.httpClientUploadFile(file,token);
//				System.out.println("---------------------"+s);
//			}catch (Exception e){
//				System.out.println("-------------网络中断，文件上传监控失败！------------");
//			}

			if(file == null || StringHelper.isBlank(file.getOriginalFilename())) {
				message.setType(ReturnMessage.MESSAGE_ERROR);
				message.setMessage("上传失败！");
				return message.toString();
			}
			//文件大小
			content.setSize(file.getSize());
			//文件格式
			String suffix = FileManager.getSuffix(file.getOriginalFilename());
			content.setSuffix(suffix);
			//本地全路径
			String savePath = request.getSession().getServletContext().getRealPath(folder.getPath());
			FileManager.makeDirectory(savePath);
			File saveFile = new File(savePath, file.getOriginalFilename());
			String fileName = FileManager.getFileNameWithOutSuffix(file.getOriginalFilename());
			int i = 1;
			while(saveFile.exists()) {
				if(FileManager.getFileNameWithOutSuffix(saveFile.getName()).endsWith("_"+i)) {
					fileName = fileName.substring(0, fileName.lastIndexOf("_"))+"_"+(++i);
				} else {
					fileName = fileName + "_" +i;
				}
				saveFile = new File(savePath, fileName+suffix);
			}
			content.setFileName(saveFile.getName());
			content.setType(FileManager.getFileTypeName(saveFile.getName()));
			content.setMappingName("");
			try {
				FileUtils.copyInputStreamToFile(file.getInputStream(), saveFile);
				if("视频文件".equals(content.getType()) || "图片文件".equals(content.getType())) {
					String subFileName = fileName + "_th";
					File subFile = new File(savePath, subFileName+".jpg");
					i = 1;
					while(subFile.exists()) {
						if(FileManager.getFileNameWithOutSuffix(subFile.getName()).endsWith("_"+i)) {
							subFileName = subFileName.substring(0, subFileName.lastIndexOf("_"))+"_"+(++i);
						} else {
							subFileName = subFileName + "_" +i;
						}
						subFile = new File(savePath, subFileName+".jpg");
					}
					//
					boolean b = FileManager.processImg(savePath+fileName+suffix, savePath+subFileName+".jpg", file.getContentType());
					if(!b) {
						message.setMessage("上传成功，但是由于服务器环境出错，文件解析失败！");
					} else {
						content.setSubFileName(subFile.getName());
						content.setSubFileSize(subFile.length());
					}
				} else if(content.getType().equals("便携式文档")) {//pdf
					String subFileName = fileName;
					File subFile = new File(savePath, subFileName+".swf");
					i = 1;
					while(subFile.exists()) {
						if(FileManager.getFileNameWithOutSuffix(subFile.getName()).endsWith("_"+i)) {
							subFileName = subFileName.substring(0, subFileName.lastIndexOf("_"))+"_"+(++i);
						} else {
							subFileName = subFileName + "_" +i;
						}
						subFile = new File(savePath, subFileName+".swf");
					}
					boolean pdf2Swf = FileManager.pdf2Swf(savePath+fileName+suffix, savePath+subFileName+".swf");
					if(!pdf2Swf) {
						message.setMessage("上传成功，但是由于服务器环境出错，文件解析失败！");
					} else {
						content.setSubFileName(subFile.getName());
						content.setSubFileSize(subFile.length());
					}
				} else if(content.getType().equals("word文本文档") 
						|| content.getType().equals("excel表格文档") 
						|| content.getType().equals("演示文档")) {
					String subFileName = fileName;
					File subFile = new File(savePath, subFileName+".swf");
					String tempFileName = FileManager.getNewFileName(".pdf");
					boolean office2Pdf = FileManager.office2Pdf(savePath+fileName+suffix, savePath+tempFileName);
					if(office2Pdf) {
						i = 1;
						while(subFile.exists()) {
							if(FileManager.getFileNameWithOutSuffix(subFile.getName()).endsWith("_"+i)) {
								subFileName = subFileName.substring(0, subFileName.lastIndexOf("_"))+"_"+(++i);
							} else {
								subFileName = subFileName + "_" +i;
							}
							subFile = new File(savePath, subFileName+".swf");
						}
						boolean pdf2Swf = FileManager.pdf2Swf(savePath+tempFileName, savePath+subFileName+".swf");
						if(!pdf2Swf) {
							message.setMessage("上传成功，但是由于服务器环境出错，文件解析失败！");
						} else {
							content.setSubFileName(subFile.getName());
							content.setSubFileSize(subFile.length());
						}
						File tempFile = new File(savePath, tempFileName);
						tempFile.delete();
					} else {
						message.setMessage("上传成功，但是由于服务器环境出错，文件解析失败！");
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				message.setType(ReturnMessage.MESSAGE_ERROR);
				message.setMessage("上传失败！");
				return message.toString();
			}
		} else {
			if(StringHelper.isBlank(localFile)) {
				message.setType(ReturnMessage.MESSAGE_ERROR);
				message.setMessage("上传失败！");
				return message.toString();
			}
			content.setSize(0L);
			content.setSuffix(FileManager.getSuffix(localFile));
			content.setFileName(localFile);
			content.setType(FileManager.getFileTypeName(localFile));
			if(localFile.startsWith("http://") || localFile.startsWith("https://")
					|| localFile.startsWith("ftp://")) {
				content.setType("网址");
			}
			if(".exe".equalsIgnoreCase(content.getSuffix())) {
				if(StringHelper.isBlank(content.getMappingName())) {
					File f = new File(localFile);
					content.setMappingName(f.getName());
				}
			}
		}
		content.setMappingName(content.getMappingName().trim());
		content.setAddTime(new Date());
		content.setUpdateTime(new Date());
		content.setSerialNumber(1);
		Object obj = contentService.createQuery("select max(serialNumber) from Content").uniqueResult();
		if(obj != null) {
			content.setSerialNumber(Integer.parseInt(obj.toString())+1);
		}
		int max = contentService.getMaxSort(folder.getId());
		content.setSort(max+1);
		content.setIsSystem(Content.IS_NOT_SYSTEM);
		Set<Content> contents = folder.getContents();
		contents.add(content);
		folder.setContents(contents);
		folderService.saveOrUpdate(folder);
		try{
			//登录监控获取token
			CountInfo countInfo = new CountInfo();
			// 设置请求的参数
			JSONObject jsonParam = new JSONObject();
			jsonParam.put("username", countInfo.USER_NAME);
			jsonParam.put("password", countInfo.PASSWORD);
			String json = jsonParam.toString();
			String data = HttpRequest.post(countInfo.DW_SYSTEM_LOGIN)
							.body(json,"application/json")
							.execute().body();
			cn.hutool.json.JSONObject object = JSONUtil.parseObj(data);
			Object userInfo = object.get("data");
			cn.hutool.json.JSONObject object1 = JSONUtil.parseObj(userInfo);
			String token = (String) object1.get("token");
			System.out.println("------------------"+token);
//				String userId = (String) object1.get("employeeId");
			//再上传file，存储对应的文件表
			HttpClientUploadFile httpClientUploadFile = new HttpClientUploadFile();
			String s = httpClientUploadFile.httpClientUploadFile(file,token);
			System.out.println("---------------------"+s);
			//文件信息存储监控
					cn.hutool.json.JSONObject jsonObject_1 = JSONUtil.parseObj(s);
			Object data_1 = jsonObject_1.get("data");
			cn.hutool.json.JSONObject jsonObject_2 = new cn.hutool.json.JSONObject(data_1);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("fileName",content.getFileName());
			jsonObject.put("filePath",jsonObject_2.get("path"));
			jsonObject.put("fileSize",jsonObject_2.get("fileSize"));
//			if (content.getType()){
//
//			}
			jsonObject.put("fileType","2");
			jsonObject.put("fileUrl",jsonObject_2.get("url"));
			jsonObject.put("hallId",countInfo.HALL_ID);
			jsonObject.put("id",content.getId());
			jsonObject.put("reviewMark","1");
			String s_1 = HttpUtil.createPost(countInfo.DW_FILE_INFO_SAVE)
							.body(jsonObject.toString(),"application/json")
							.execute().body();
			System.out.println(s_1);
		}catch (Exception e){
			System.out.println("-------------网络中断，文件上传监控失败！------------");
		}
		return message.toString();
	}
	
	@ResponseBody
	@RequestMapping("edit")
	public String edit(String localFile, Content content, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "修改成功！");
		if(StringHelper.isEmpty(content.getId())) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("修改失败！");
			return message.toString();
		}
		Content fc = contentService.getById(content.getId());
		String savePath = request.getSession().getServletContext().getRealPath(fc.getFolder().getPath());
		FileManager.deleteFile(savePath, fc.getFileName());
		if(StringHelper.isNotBlank(fc.getSubFileName())) {
			for(String subFileName : fc.getSubFileName().split(";")) {
				File subFile = new File(savePath, subFileName);
				if(subFile.exists()) {
					subFile.delete();
				}
			}
		}
		fc.setSubFileName("");
		if(content.getWay() == Content.WAY_UPLOAD) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = multipartHttpServletRequest.getFile("file");
			if(file != null && !file.getOriginalFilename().isEmpty()) {
				fc.setSize(file.getSize());
				String suffix = FileManager.getSuffix(file.getOriginalFilename());
				fc.setSuffix(suffix);
				FileManager.makeDirectory(savePath);
				String fileName = FileManager.getFileNameWithOutSuffix(file.getOriginalFilename());
				File saveFile = new File(savePath, file.getOriginalFilename());
				int i = 1;
				while(saveFile.exists() && !fc.getFileName().equals(saveFile.getName())) {
					if(FileManager.getFileNameWithOutSuffix(saveFile.getName()).endsWith("_"+i)) {
						fileName = fileName.substring(0, fileName.lastIndexOf("_"))+"_"+(++i);
					} else {
						fileName = fileName + "_" +i;
					}
					saveFile = new File(savePath, fileName+suffix);
				}
				fc.setType(FileManager.getFileTypeName(saveFile.getName()));
				fc.setUpdateTime(new Date());
				try {
					FileUtils.copyInputStreamToFile(file.getInputStream(), saveFile);
					if("视频文件".equals(fc.getType()) || "图片文件".equals(fc.getType())) {
						String subFileName = fileName + "_th";
						File subFile = new File(savePath, subFileName+".jpg");
						i = 1;
						while(subFile.exists()) {
							if(FileManager.getFileNameWithOutSuffix(subFile.getName()).endsWith("_"+i)) {
								subFileName = subFileName.substring(0, subFileName.lastIndexOf("_"))+"_"+(++i);
							} else {
								subFileName = subFileName + "_" +i;
							}
							subFile = new File(savePath, subFileName+".jpg");
						}
						FileManager.processImg(savePath+fileName+suffix, savePath+subFileName+".jpg", file.getContentType());
						fc.setSubFileName(subFileName+".jpg");
					} else if(fc.getType().equals("便携式文档")) {//pdf
						String subFileName = fileName;
						File subFile = new File(savePath, subFileName+".swf");
						i = 1;
						while(subFile.exists()) {
							if(FileManager.getFileNameWithOutSuffix(subFile.getName()).endsWith("_"+i)) {
								subFileName = subFileName.substring(0, subFileName.lastIndexOf("_"))+"_"+(++i);
							} else {
								subFileName = subFileName + "_" +i;
							}
							subFile = new File(savePath, subFileName+".swf");
						}
						FileManager.pdf2Swf(savePath+fileName+suffix, savePath+subFileName+".swf");
						fc.setSubFileName(subFile.getName());
					} else if(fc.getType().equals("word文本文档") 
							|| fc.getType().equals("excel表格文档") 
							|| fc.getType().equals("演示文档")) {
						String subFileName = fileName;
						File subFile = new File(savePath, subFileName+".swf");
						String tempFileName = FileManager.getNewFileName(".pdf");
						FileManager.office2Pdf(savePath+fileName+suffix, savePath+tempFileName);
						i = 1;
						while(subFile.exists()) {
							if(FileManager.getFileNameWithOutSuffix(subFile.getName()).endsWith("_"+i)) {
								subFileName = subFileName.substring(0, subFileName.lastIndexOf("_"))+"_"+(++i);
							} else {
								subFileName = subFileName + "_" +i;
							}
							subFile = new File(savePath, subFileName+".swf");
						}
						FileManager.pdf2Swf(savePath+tempFileName, savePath+subFileName+".swf");
						File tempFile = new File(savePath, tempFileName);
						tempFile.delete();
						fc.setSubFileName(subFile.getName());
					}
				} catch (IOException e) {
					e.printStackTrace();
					message.setType(ReturnMessage.MESSAGE_ERROR);
					message.setMessage("修改失败！");
					return message.toString();
				}
				fc.setFileName(saveFile.getName());
			}
		} else {
			if(StringHelper.isBlank(localFile)) {
				message.setType(ReturnMessage.MESSAGE_ERROR);
				message.setMessage("上传失败！");
				return message.toString();
			}
			fc.setSize(0L);
			fc.setSuffix(FileManager.getSuffix(localFile));
			fc.setFileName(localFile);
			fc.setType(FileManager.getFileTypeName(localFile));
			fc.setMappingName("");
			if(localFile.startsWith("http://") || localFile.startsWith("https://")
					|| localFile.startsWith("ftp://")) {
				fc.setType("网址");
			}
			if(".exe".equalsIgnoreCase(fc.getSuffix())) {
				if(StringHelper.isBlank(content.getMappingName())) {
					File f = new File(localFile);
					fc.setMappingName(f.getName());
				} else {
					fc.setMappingName(content.getMappingName());
				}
			}
			content.setMappingName(content.getMappingName().trim());
		}
		fc.setWay(content.getWay());
		fc.setTitle(content.getTitle());
		fc.setUpdateTime(new Date());
		contentService.saveOrUpdate(fc);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		fc.getTerminalContents().forEach(terminalContent->{
			SocketDataSender socket = new SocketDataSender();
			socket.sendToClient(terminalContent.getTerminal().getCodeName(), "play:"+JSONArray.fromObject(terminalContent.getTerminal().getTerminalContents(), jsonConfig).toString());
		});
		return message.toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("getByFolder")
	public String getByFolder(Folder folder, String[] fileType) {
		if(StringHelper.isEmpty(folder.getId())) {
			return null;
		}
		String hql = "from Content where 1=1";
		if(fileType != null) {
			String types = Stream.of(fileType).flatMap(type->Stream.of(type.split(","))).filter(StringHelper::isNotBlank)
					.map(type->"'"+type+"'").collect(Collectors.joining(","));
			if(StringHelper.isNotBlank(types)) {
				hql += " and type in ("+types+")";
			}
		}
		hql += " and folder = ? order by sort";
		@SuppressWarnings("unchecked")
		List<Content> files = contentService.createQuery(hql, folder).list();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.setExcludes(new String[] { "terminalContents", "contents"});
		return JSONArray.fromObject(files, jsonConfig).toString();
	}
	
	@ResponseBody
	@RequestMapping("getByRoot")
	public String getByRoot(Folder folder) {
		if(StringHelper.isEmpty(folder.getId())) {
			return null;
		}
		@SuppressWarnings("unchecked")
		List<Content> files = contentService.createQuery(
				"from Content where checkStatus = 1 and folder in (from Folder where rootId = ?) order by folder,sort", folder.getId()).list();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.setExcludes(new String[] { "terminalContents", "contents"});
		return JSONArray.fromObject(files, jsonConfig).toString();
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("delete")
	public String delete(String id, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "删除成功！");
		if(id == null) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("删除失败！");
			return message.toString();
		}
		String[] ids = id.split(",");
		String str = Stream.of(ids).map(s->"'"+s+"'").collect(Collectors.joining(","));
		List<Content> list = contentService.createQuery("from Content where id in ("+str+") and isSystem != "+Content.IS_SYSTEM).list();
		if(ids.length != list.size()) {
			message.setMessage("部分文件禁止删除，删除操作被屏蔽！");
		}
		list.forEach(content->{
			if(content.getWay() == Content.WAY_UPLOAD) {
				String savePath = request.getSession().getServletContext().getRealPath(content.getFolder().getPath());
				FileManager.deleteFile(savePath, content.getFileName());
				if(StringHelper.isNotBlank(content.getSubFileName())) {
					for(String subFileName : content.getSubFileName().split(";")) {
						File subFile = new File(savePath, subFileName);
						if(subFile.exists()) {
							subFile.delete();
						}
					}
				}
			}
			List<TerminalContent> terminalContents = terminalContentService.find(Restrictions.eq("content", content));
			terminalContents.forEach(terminalContent->{
				SocketDataSender socket = new SocketDataSender();
				socket.sendToClient(terminalContent.getTerminal().getCodeName(), "cancel");
			});
		});
		List<String> deleteIds = list.stream().filter(content->content.getIsSystem()!=Content.IS_SYSTEM)
				.map(Content::getId).collect(Collectors.toList());
		contentService.delete(deleteIds.toArray(new String[deleteIds.size()]));
		return message.toString();
	}
	
	@ResponseBody
	@RequestMapping("sortContent")
	public String sortContent(String direction, String contentId) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "排序成功！");
		if(StringHelper.isBlank(direction) || StringHelper.isBlank(contentId)) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("排序失败！");
			return message.toString();
		}
		Content source = contentService.getById(contentId);
		Content target = new Content();
		int sourceSort = source.getSort();
		if("1".equals(direction)) {
			target = contentService.getCloseSmall(contentId);
		} else if("-1".equals(direction)) {
			target = contentService.getCloseLarge(contentId);
		}
		if(target != null) {
			source.setSort(target.getSort());
			target.setSort(sourceSort);
			contentService.saveOrUpdate(target);
			contentService.saveOrUpdate(source);
		}
		return message.toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("getAdContents")
	public String getAdContents(String currentContentId) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MINUTE, -1000);
		Date date = calendar.getTime();
		List<Queue> queueList = queueService.createQuery("from Queue where addTime > ?", date).list();
		List<String> tagsList = new ArrayList<>();
		if(queueList.isEmpty()) {
			return "";
		}
		String[] tagsArray = queueList.stream().map(Queue::getConsTag).map(consTag->consTag.split(",")).reduce((a,b)->{
			return Stream.concat(Stream.of(a), Stream.of(b)).toArray(String[]::new);
		}).get();
		tagsList = arraySort(tagsArray);
		PropertiesHelper prop = new PropertiesHelper("config");
		String folderId = prop.readValueTrim("queue.folder.id");
		if(StringHelper.isBlank(folderId)) {
			return "";
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.setExcludes(new String[] { "terminalContents", "contents"});
		for(String tag : tagsList) {
			List<Folder> folders = folderService.find(Restrictions.eq("parentId", folderId), Restrictions.eq("title", tag));
			if(folders.isEmpty()) {
				continue;
			}
			Folder folder = folders.get(0);
			List<Content> list = contentService.createQuery("from Content where folder = ? order by addTime desc", folder).list();
			if(list.size() > 0) {
				int index = -1;
				for(int i = 0;i<list.size();i++) {
					Content c = list.get(i);
					if(c.getId().equals(currentContentId)) {
						index = i;
						break;
					}
				}
				Content thisContent = index == -1 ? list.get(0) : index == list.size()-1 ? list.get(0) : list.get(index+1);
				return JSONObject.fromObject(thisContent, jsonConfig).toString();
			}
		}
		return "";
	}
	
	private static List<String> arraySort(String[] arr) {  
        // 定义map，存放数组中的字符及出现次数  
        Map<String, Integer> map = new HashMap<String, Integer>();  
          
        // 遍历数组，将字符及出现次数存放map中  
        for (String str : arr) {  
            Integer count = map.get(str);  
            if (null != count) {  
                map.put(str, count + 1);  
            } else {  
                map.put(str, 1);  
            }  
        }  
          
        // 定义list，存放map中的entry  
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>();  
        list.addAll(map.entrySet());  

        // 对list中的entry，按照value值进行降序排列  
        Collections.sort(list, new Comparator<Entry<String, Integer>>(){  
            public int compare(Entry<String, Integer> arg0, Entry<String, Integer> arg1) {  
                return arg1.getValue().compareTo(arg0.getValue());  
            }  
        });
        // 定义StringBuffer，存放返回的字符串 
        List<String> sortList = new ArrayList<>();
        for (Entry<String, Integer> entry : list) {
        	sortList.add(entry.getKey());
        }
        return sortList;
    }
	//未审核数据
	@OpenAccess
    @RequestMapping("/nostatus")
	@ResponseBody
	public String selectByCheckStatus(){
		@SuppressWarnings("unchecked")
		List<Content> files = contentService.createQuery(
				"from Content where checkStatus = ?", 0).list();
		com.alibaba.fastjson.JSONArray ja = new com.alibaba.fastjson.JSONArray();
		ja.add(files);
		String re = ja.toString().substring(1,ja.toString().length()-1);
		return 	re;
//		JsonConfig jsonConfig = new JsonConfig();
//		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
//		jsonConfig.setExcludes(new String[] { "terminalContents", "contents"});
//		return JSONArray.fromObject(files, jsonConfig).toString();
	}
	//审核状态修改
	@OpenAccess
	@RequestMapping("/changestatus")
	@ResponseBody
	public String changeStatus(ContentStatusVo contentStatusVo){
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "修改成功！");
		contentService.changeStatus(contentStatusVo);
    	return message.toString();
	}
}
