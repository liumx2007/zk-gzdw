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
import com.zzqx.mvc.dao.SubjectWrongDao;
import com.zzqx.mvc.entity.SubjectWrong;
import com.zzqx.mvc.service.SubjectWrongService;
import com.zzqx.support.utils.StringHelper;

@Service("subjectWrongService")
@Transactional
public class SubjectWrongServiceImpl implements SubjectWrongService {
	
	@Autowired
	private SubjectWrongDao subjectWrongDao;
	
	@Override
	public void delete(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			subjectWrongDao.batchExecute("delete SubjectWrong where id in ("+str+")");
			subjectWrongDao.flush();
		}
	}

	@Override
	public List<SubjectWrong> find(Criterion... criterions) {
		return subjectWrongDao.find(criterions);
	}
	
	@Override
	public List<SubjectWrong> get(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			return subjectWrongDao.find("from SubjectWrong where id in ("+str+")");
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public List<SubjectWrong> getAll() {
		return subjectWrongDao.find("from SubjectWrong");
	}

	@Override
	public SubjectWrong getById(String id) {
		return subjectWrongDao.findUnique(Restrictions.eq("id", id));
	}

	@Override
	public void saveOrUpdate(SubjectWrong subjectWrong) {
		subjectWrongDao.saveOrUpdate(subjectWrong);
		subjectWrongDao.flush();
	}

	@Override
	public Page<SubjectWrong> getByPage(Map<String, Object> map, int pageNo, int pageSize) {
		Page<SubjectWrong> page = new Page<>();
		page.pageNo(pageNo).pageSize(pageSize).autoCount(true);
		return subjectWrongDao.findPage(page, map);
	}

	@Override
	public Page<SubjectWrong> getByPage(Map<String, Object> map, int pageNo, int pageSize, String orderBy, String order) {
		Page<SubjectWrong> page = new Page<>();
		page.pageNo(pageNo).pageSize(pageSize).autoCount(true).orderBy(orderBy).setOrder(order);
		return subjectWrongDao.findPage(page, map);
	}

	@Override
	public void executeHql(String hql, Object... values) {
		subjectWrongDao.batchExecute(hql, values);
		subjectWrongDao.flush();
	}

	@Override
	public Query createQuery(String hql, Object... values) {
		return subjectWrongDao.createQuery(hql, values);
	}

	@Override
	public Query createQuery(String hql, Map<String, ?> map) {
		return subjectWrongDao.createQuery(hql, map);
	}
}
