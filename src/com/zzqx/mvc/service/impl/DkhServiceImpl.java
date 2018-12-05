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
import com.zzqx.mvc.dao.DkhDao;
import com.zzqx.mvc.entity.Dkh;
import com.zzqx.mvc.service.DkhService;
import com.zzqx.support.utils.StringHelper;

@Service("dkhService")
@Transactional
public class DkhServiceImpl implements DkhService {
	
	@Autowired
	private DkhDao dkhDao;
	
	@Override
	public void delete(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			dkhDao.batchExecute("delete Dkh where id in ("+str+")");
			dkhDao.flush();
		}
	}

	@Override
	public List<Dkh> find(Criterion... criterions) {
		return dkhDao.find(criterions);
	}
	
	@Override
	public List<Dkh> get(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			return dkhDao.find("from Dkh where id in ("+str+")");
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public List<Dkh> getAll() {
		return dkhDao.getAll();
	}

	@Override
	public Dkh getById(String id) {
		return dkhDao.findUnique(Restrictions.eq("id", id));
	}
	
	@Override
	public void saveOrUpdate(Dkh Dkh) {
		dkhDao.saveOrUpdate(Dkh);
		dkhDao.flush();
	}

	@Override
	public Page<Dkh> getByPage(Map<String, Object> map, int pageNo, int pageSize) {
		Page<Dkh> page = new Page<>();
		page.pageNo(pageNo).pageSize(pageSize).autoCount(true);
		return dkhDao.findPage(page, map);
	}

	@Override
	public Page<Dkh> getByPage(Map<String, Object> map, int pageNo, int pageSize, String orderBy, String order) {
		Page<Dkh> page = new Page<>();
		page.pageNo(pageNo).pageSize(pageSize).autoCount(true).orderBy(orderBy).setOrder(order);
		return dkhDao.findPage(page, map);
	}

	@Override
	public void executeHql(String hql, Object... values) {
		dkhDao.batchExecute(hql, values);
		dkhDao.flush();
	}

	@Override
	public Query createQuery(String hql, Object... values) {
		return dkhDao.createQuery(hql, values);
	}

	@Override
	public Query createQuery(String hql, Map<String, ?> map) {
		return dkhDao.createQuery(hql, map);
	}
}
