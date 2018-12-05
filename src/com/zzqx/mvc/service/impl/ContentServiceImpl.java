package com.zzqx.mvc.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.zzqx.mvc.dao.FolderDao;
import com.zzqx.mvc.dto.FolderContentDto;
import com.zzqx.mvc.vo.ContentStatusVo;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jetsum.core.orm.entity.Page;
import com.zzqx.mvc.dao.ContentDao;
import com.zzqx.mvc.entity.Content;
import com.zzqx.mvc.service.ContentService;
import com.zzqx.support.utils.StringHelper;

@Service("contentService")
@Transactional
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private ContentDao contentDao;

	@Autowired
	private FolderDao folderDao;
	
	@Override
	public void delete(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			contentDao.batchExecute("delete Content where id in ("+str+")");
			contentDao.flush();
		}
	}

	@Override
	public List<Content> find(Criterion... criterions) {
		return contentDao.find(criterions);
	}
	
	@Override
	public List<Content> get(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			return contentDao.find("from Content where id in ("+str+")");
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public List<Content> getAll() {
		return contentDao.find("from Content order by date desc,apm asc");
	}

	@Override
	public Content getById(String id) {
		return contentDao.findUnique(Restrictions.eq("id", id));
	}

	@Override
	public void saveOrUpdate(Content content) {
		contentDao.saveOrUpdate(content);
		contentDao.flush();
	}

	@Override
	public Page<Content> getByPage(Map<String, Object> map, int pageNo, int pageSize) {
		Page<Content> page = new Page<>();
		page.pageNo(pageNo).pageSize(pageSize).autoCount(true);
		return contentDao.findPage(page, map);
	}

	@Override
	public Page<Content> getByPage(Map<String, Object> map, int pageNo, int pageSize, String orderBy, String order) {
		Page<Content> page = new Page<>();
		page.pageNo(pageNo).pageSize(pageSize).autoCount(true).orderBy(orderBy).setOrder(order);
		return contentDao.findPage(page, map);
	}

	@Override
	public void executeHql(String hql, Object... values) {
		contentDao.batchExecute(hql, values);
		contentDao.flush();
	}

	@Override
	public Query createQuery(String hql, Object... values) {
		return contentDao.createQuery(hql, values);
	}

	@Override
	public Query createQuery(String hql, Map<String, ?> map) {
		return contentDao.createQuery(hql, map);
	}

	@Override
	public int getMinSort(String folderId) {
		String hql = "select min(sort) from Content where folder=(from Folder where id = '"+folderId+"')";
		Object obj = contentDao.createQuery(hql).uniqueResult();
		if(obj==null)
			return 0;
		else {
			return Integer.parseInt(obj.toString());
		}
	}

	@Override
	public int getMaxSort(String folderId) {
		String hql = "select max(sort) from Content where folder=(from Folder where id = '"+folderId+"')";
		Object obj = contentDao.createQuery(hql).uniqueResult();
		if(obj==null)
			return 0;
		else {
			return Integer.parseInt(obj.toString());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Content getCloseSmall(String id) {
		Content content = contentDao.get(id);
		if(content == null) {
			return null;
		}
		List<Content> list = contentDao.createQuery(
				"from Content where sort<? and folder = ? order by sort desc", 
				content.getSort(), content.getFolder()).list();
		if(list.size()>0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Content getCloseLarge(String id) {
		Content content = contentDao.get(id);
		if(content == null) {
			return null;
		}
		List<Content> list = contentDao.createQuery(
				"from Content where sort>? and folder = ? order by sort asc", 
				content.getSort(), content.getFolder()).list();
		if(list.size()>0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	//未审核的数据
	public List<FolderContentDto> selectByCheckStatus() {
		List<FolderContentDto> folderContentDtoList = folderDao.createQuery(
				"select f.id, f.parent_id, f.root_id,f.title,f.folder_name, f.path, c.file_name, c.type, c.suffix, c.add_time, c.check_status, c.check_name  from Folder f left join Content c on f.id = c.folder_id where  c.check_status = ?",
				0).list();
			return folderContentDtoList;
	}

	@Override
	public void changeStatus(ContentStatusVo contentStatusVo) {
		String id = contentStatusVo.getId();
		Content content = getById(id);
		try {
			BeanUtils.copyProperties(content,contentStatusVo);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		saveOrUpdate(content);
	}

}
