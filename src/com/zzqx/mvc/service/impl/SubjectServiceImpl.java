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
import com.zzqx.mvc.dao.SubjectDao;
import com.zzqx.mvc.entity.Subject;
import com.zzqx.mvc.service.SubjectService;
import com.zzqx.support.utils.StringHelper;

@Service("subjectService")
@Transactional
public class SubjectServiceImpl implements SubjectService {
	
	@Autowired
	private SubjectDao subjectDao;
	
	@Override
	public void delete(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			subjectDao.batchExecute("delete Subject where id in ("+str+")");
			subjectDao.flush();
		}
	}

	@Override
	public List<Subject> find(Criterion... criterions) {
		return subjectDao.find(criterions);
	}
	
	@Override
	public List<Subject> get(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			return subjectDao.find("from Subject where id in ("+str+")");
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public List<Subject> getAll() {
		return subjectDao.getAll();
	}

	@Override
	public Subject getById(String id) {
		return subjectDao.findUnique(Restrictions.eq("id", id));
	}
	
	@Override
	public void saveOrUpdate(Subject Subject) {
		subjectDao.saveOrUpdate(Subject);
		subjectDao.flush();
	}

	@Override
	public Page<Subject> getByPage(Map<String, Object> map, int pageNo, int pageSize) {
		Page<Subject> page = new Page<>();
		page.pageNo(pageNo).pageSize(pageSize).autoCount(true);
		return subjectDao.findPage(page, map);
	}

	@Override
	public Page<Subject> getByPage(Map<String, Object> map, int pageNo, int pageSize, String orderBy, String order) {
		Page<Subject> page = new Page<>();
		page.pageNo(pageNo).pageSize(pageSize).autoCount(true).orderBy(orderBy).setOrder(order);
		return subjectDao.findPage(page, map);
	}

	@Override
	public void executeHql(String hql, Object... values) {
		subjectDao.batchExecute(hql, values);
		subjectDao.flush();
	}

	@Override
	public Query createQuery(String hql, Object... values) {
		return subjectDao.createQuery(hql, values);
	}

	@Override
	public Query createQuery(String hql, Map<String, ?> map) {
		return subjectDao.createQuery(hql, map);
	}
}
