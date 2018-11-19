package com.zzqx.mvc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jetsum.core.orm.entity.Page;
import com.zzqx.mvc.dao.FolderDao;
import com.zzqx.mvc.entity.Folder;
import com.zzqx.mvc.service.FolderService;
import com.zzqx.support.utils.StringHelper;

@Service("folderService")
@Transactional
public class FolderServiceImpl implements FolderService {
	
	@Autowired
	private FolderDao folderDao;
	
	@Override
	public void delete(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			folderDao.batchExecute("delete Folder where id in ("+str+")");
			folderDao.flush();
		}
	}

	@Override
	public List<Folder> find(Criterion... criterions) {
		return folderDao.find(criterions);
	}
	
	@Override
	public List<Folder> get(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			return folderDao.find("from Folder where id in ("+str+")");
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public List<Folder> getAll() {
		return folderDao.find("from Folder order by date desc,apm asc");
	}

	@Override
	public Folder getById(String id) {
		return folderDao.findUnique(Restrictions.eq("id", id));
	}
	
	@Override
	public void saveOrUpdate(Folder Folder) {
		folderDao.saveOrUpdate(Folder);
		folderDao.flush();
	}

	@Override
	public Page<Folder> getByPage(Map<String, Object> map, int pageNo, int pageSize) {
		Page<Folder> page = new Page<>();
		page.pageNo(pageNo).pageSize(pageSize).autoCount(true);
		return folderDao.findPage(page, map);
	}

	@Override
	public Page<Folder> getByPage(Map<String, Object> map, int pageNo, int pageSize, String orderBy, String order) {
		Page<Folder> page = new Page<>();
		page.pageNo(pageNo).pageSize(pageSize).autoCount(true).orderBy(orderBy).setOrder(order);
		return folderDao.findPage(page, map);
	}

	@Override
	public void executeHql(String hql, Object... values) {
		folderDao.batchExecute(hql, values);
		folderDao.flush();
	}

	@Override
	public Query createQuery(String hql, Object... values) {
		return folderDao.createQuery(hql, values);
	}

	@Override
	public Query createQuery(String hql, Map<String, ?> map) {
		return folderDao.createQuery(hql, map);
	}

}
