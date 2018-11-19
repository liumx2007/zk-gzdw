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
import com.zzqx.mvc.dao.StatDao;
import com.zzqx.mvc.entity.Stat;
import com.zzqx.mvc.service.StatService;
import com.zzqx.support.utils.StringHelper;

@Service("statService")
@Transactional
public class StatServiceImpl implements StatService {
	
	@Autowired
	private StatDao statDao;

	@Override
	public void executeHql(String hql, Object... values) {
		// TODO Auto-generated method stub
		statDao.batchExecute(hql, values);
		statDao.flush();
	}

	@Override
	public void saveOrUpdate(Stat t) {
		statDao.saveOrUpdate(t);
		statDao.flush();
	}

	@Override
	public Stat getById(String id) {
		// TODO Auto-generated method stub
		return statDao.findUnique(Restrictions.eq("id", id));
	}

	@Override
	public void delete(String... ids) {
		String str = Stream.of(ids).map(id -> "'" + id + "'").collect(Collectors.joining(","));
		if (StringHelper.isNotBlank(str)) {
			statDao.batchExecute("delete Personnel where id in (" + str + ")");
			statDao.flush();
		}
		
	}

	@Override
	public List<Stat> find(Criterion... criterions) {
		// TODO Auto-generated method stub
		return statDao.find(criterions);
	}

	@Override
	public List<Stat> getAll() {
		// TODO Auto-generated method stub
		return statDao.getAll();
	}

	@Override
	public List<Stat> get(String... ids) {
		// TODO Auto-generated method stub
		String str = Stream.of(ids).map(id -> "'" + id + "'").collect(Collectors.joining(","));
		if (StringHelper.isNotBlank(str)) {
			return statDao.find("from Stat where id in (" + str + ")");
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public Page<Stat> getByPage(Map<String, Object> map, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Page<Stat> page = new Page<>();
		page.pageNo(pageNo).pageSize(pageSize).autoCount(true);
		return statDao.findPage(page, map);
	}

	@Override
	public Page<Stat> getByPage(Map<String, Object> map, int pageNo, int pageSize, String orderBy, String order) {
		// TODO Auto-generated method stub
		Page<Stat> page = new Page<>();
		page.pageNo(pageNo).pageSize(pageSize).autoCount(true).orderBy(orderBy).setOrder(order);
		return statDao.findPage(page, map);
	}

	@Override
	public Query createQuery(String hql, Object... values) {
		return statDao.createQuery(hql, values);
	}

	@Override
	public Query createQuery(String hql, Map<String, ?> map) {
		return statDao.createQuery(hql, map);
	}

	

}
