package com.zzqx.mvc.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzqx.mvc.entity.Content;
import com.zzqx.mvc.entity.Folder;
import com.zzqx.mvc.javabean.ReturnMessage;
import com.zzqx.mvc.service.ContentService;
import com.zzqx.mvc.service.FolderService;
import com.zzqx.support.utils.StringHelper;
import com.zzqx.support.utils.file.FileManager;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;


@Controller
@RequestMapping(value = "/folder")
public class FolderController extends BaseController {

	@Autowired
	private FolderService folderService;
	@Autowired
	private ContentService contentService;
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("getByParent")
	public String getByParent(Folder parent) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		String parentId = StringHelper.isEmpty(parent.getId())?"0":parent.getId();
		List<Folder> list = folderService.createQuery("from Folder where parentId = '"+parentId+"' order by title asc").list();
		list.forEach(folder->{
			sb.append("{\"id\":\""+folder.getId()+"\"");
			if(folder.getIsSystem() == Folder.IS_SYSTEM) {
				sb.append(",\"text\":\"<span style='color:red;'>"+folder.getTitle()+"</span>\"");
			} else {
				sb.append(",\"text\":\""+folder.getTitle()+"\"");
			}
			sb.append(",\"iconCls\":\"icon-package\"");
			sb.append(",\"isSystem\":\""+folder.getIsSystem()+"\"");
			List<Folder> children1 = folderService.createQuery("from Folder where parentId = '"+folder.getId()+"' order by title asc").list();
			if(children1.size()>0) {
				sb.append(",\"state\":\"closed\"");
				sb.append(",\"children\":[");
				children1.forEach(child->{
					sb.append("{\"id\":\""+child.getId()+"\"");
					if(child.getIsSystem() == Folder.IS_SYSTEM) {
						sb.append(",\"text\":\"<span style='color:red;'>"+child.getTitle()+"</span>\"");
					} else {
						sb.append(",\"text\":\""+child.getTitle()+"\"");
					}
					sb.append(",\"iconCls\":\"icon-package\"");
					sb.append(",\"isSystem\":\""+child.getIsSystem()+"\"");
					List<Folder> children2 = folderService.createQuery("from Folder where parentId = '"+child.getId()+"' order by title asc").list();
					if(children2.size()>0) {
						sb.append(",\"state\":\"closed\"");
					}
					sb.append("},");
				});
				sb.delete(sb.length()-1, sb.length());
				sb.append("]");
			}
			sb.append("},");
		});
		sb.delete(sb.length()-1, sb.length());
		sb.append("]");
		return sb.toString();
	}
	
	@ResponseBody
	@RequestMapping("add")
	public String add(Folder folder, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "新建成功！");
		if(StringHelper.isEmpty(folder.getTitle()) || StringHelper.isEmpty(folder.getFolderName())) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("新建失败！");
			return message.toString();
		}
		folder.setTitle(folder.getTitle().trim());
		folder.setFolderName(folder.getFolderName().trim());
		String rootRealPath = request.getSession().getServletContext().getRealPath("/");
		Folder parent = folderService.getById(folder.getParentId());
		if(parent == null) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("新建失败！");
			return message.toString();
		}
		List<Folder> list1 = folderService.find(Restrictions.eq("parentId", folder.getParentId()), Restrictions.eq("title", folder.getTitle()));
		if(list1.size() > 0) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("新建失败，该资料夹下已经存在该标题的资料夹！");
			return message.toString();
		}
		List<Folder> list2 = folderService.find(
				Restrictions.eq("parentId", folder.getParentId()),
				Restrictions.eq("folderName", folder.getFolderName()));
		if(list2.size() > 0) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("新建失败，该资料夹下已经存在名为“"+folder.getFolderName()+"”的资料夹！");
			return message.toString();
		}
		folder.setPath(parent.getPath()+folder.getFolderName()+"/");
		folder.setIsSystem(Folder.IS_NOT_SYSTEM);
		folder.setAddTime(new Date());
		if("0".equals(parent.getParentId())) {
			folderService.saveOrUpdate(folder);
			folder.setRootId(folder.getId());
			folderService.saveOrUpdate(folder);
		} else {
			folder.setRootId(parent.getRootId());
			folderService.saveOrUpdate(folder);
		}
		FileManager.makeDirectory(rootRealPath+folder.getPath());
		return message.toString();
	}
	
	@ResponseBody
	@RequestMapping("edit")
	public String edit(Folder folder) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "修改成功！");
		if(StringHelper.isEmpty(folder.getId()) || StringHelper.isEmpty(folder.getTitle())) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("修改失败！");
			return message.toString();
		}
		List<Folder> list = folderService.find(
				Restrictions.eq("title", folder.getTitle()),
				Restrictions.ne("id", folder.getId()));
		if(list.size() > 0) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("修改失败，与其它资料夹同名！");
			return message.toString();
		}
		Folder f = folderService.getById(folder.getId());
		f.setTitle(folder.getTitle());
		folderService.saveOrUpdate(f);
		return message.toString();
	}
	
	@ResponseBody
	@RequestMapping("delete")
	public String delete(Folder folder, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "移除成功！");
		if(StringHelper.isEmpty(folder.getId())) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("移除失败！");
			return message.toString();
		}
		folder = folderService.getById(folder.getId());
		if(folder == null || folder.getIsSystem() == Folder.IS_SYSTEM) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("移除失败！");
			return message.toString();
		}
		List<Folder> folders = folderService.find(Restrictions.eq("parentId", folder.getId()));
		if(folders.size() > 0) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("移除失败，该资料夹下还存在子资料夹！");
			return message.toString();
		}
		List<Content> files = contentService.find(Restrictions.eq("folder", folder));
		if(files.size() > 0) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("移除失败，该资料夹下存在文件！");
			return message.toString();
		}
		String path = request.getSession().getServletContext().getRealPath(folder.getPath());
		FileManager.deleteDirectory(path);
		folderService.delete(folder.getId());
		return message.toString();
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("roots")
	public String roots() {
		List<Folder> folders = folderService.createQuery("from Folder where id = rootId order by title").list();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.setExcludes(new String[] { "contents" });
		JSONArray jsonArray = JSONArray.fromObject(folders, jsonConfig);
		return jsonArray.toString();
	}
}
