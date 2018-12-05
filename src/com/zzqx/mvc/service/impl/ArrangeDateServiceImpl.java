package com.zzqx.mvc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jetsum.core.orm.entity.Page;
import com.zzqx.mvc.dao.ArrangeDateDao;
import com.zzqx.mvc.entity.ArrangeDate;
import com.zzqx.mvc.service.ArrangeDateService;
import com.zzqx.support.utils.StringHelper;

@Service("arrangeDateService")
@Transactional
public class ArrangeDateServiceImpl implements ArrangeDateService {
 
	@Autowired
	private ArrangeDateDao arrangeDateDao;
	@Override
	public void executeHql(String hql, Object... values) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveOrUpdate(ArrangeDate t) {
		arrangeDateDao.saveOrUpdate(t);
	}

	@Override
	public ArrangeDate getById(String id) {
		return arrangeDateDao.get(id);
	}

	@Override
	public void delete(String... ids) {
		String str = Stream.of(ids).map(id -> "'" + id + "'").collect(Collectors.joining(","));
		if (StringHelper.isNotBlank(str)) {
			arrangeDateDao.batchExecute("delete ArrangeDate where id in (" + str + ")");
			arrangeDateDao.flush();
		}
	}

	@Override
	public List<ArrangeDate> find(Criterion... criterions) {
		return arrangeDateDao.find(criterions);
	}

	@Override
	public List<ArrangeDate> getAll() {
		return arrangeDateDao.getAll();
	}

	@Override
	public List<ArrangeDate> get(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			return arrangeDateDao.find("from ArrangeDate where id in ("+str+")");
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public Page<ArrangeDate> getByPage(Map<String, Object> map, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ArrangeDate> getByPage(Map<String, Object> map, int pageNo, int pageSize, String orderBy,
			String order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query createQuery(String hql, Object... values) {
		return arrangeDateDao.createQuery(hql, values);
	}

	@Override
	public Query createQuery(String hql, Map<String, ?> map) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
	
}
