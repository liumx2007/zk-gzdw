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
import com.zzqx.mvc.dao.ArrangeDetialDao;
import com.zzqx.mvc.entity.ArrangeDate;
import com.zzqx.mvc.entity.ArrangeDetial;
import com.zzqx.mvc.service.ArrangeDateService;
import com.zzqx.mvc.service.ArrangeDetialService;
import com.zzqx.support.utils.StringHelper;

@Service("arrangeDetialService")
@Transactional
public class ArrangeDetialServiceImpl implements ArrangeDetialService {
	@Autowired
	private ArrangeDetialDao arrangeDetialDao;
	@Override
	public void executeHql(String hql, Object... values) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveOrUpdate(ArrangeDetial t) {
		arrangeDetialDao.saveOrUpdate(t);
	}

	@Override
	public ArrangeDetial getById(String id) {
		// TODO Auto-generated method stub
		List<ArrangeDetial> adList = arrangeDetialDao.findBy("id", id);
		if(adList!=null&&adList.size()>0){
			return adList.get(0);
		}
		return null;
	}

	@Override
	public void delete(String... ids) {
		String str = Stream.of(ids).map(id -> "'" + id + "'").collect(Collectors.joining(","));
		if (StringHelper.isNotBlank(str)) {
			arrangeDetialDao.batchExecute("delete ArrangeDetial where id in (" + str + ")");
			arrangeDetialDao.flush();
		}
	}

	@Override
	public List<ArrangeDetial> find(Criterion... criterions) {
		// TODO Auto-generated method stub
		return arrangeDetialDao.find(criterions);
	}

	@Override
	public List<ArrangeDetial> getAll() {
		return arrangeDetialDao.getAll();
	}

	@Override
	public List<ArrangeDetial> get(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			return arrangeDetialDao.find("from ArrangeDetial where id in ("+str+")");
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public Page<ArrangeDetial> getByPage(Map<String, Object> map, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ArrangeDetial> getByPage(Map<String, Object> map, int pageNo, int pageSize, String orderBy,
			String order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query createQuery(String hql, Object... values) {
		return arrangeDetialDao.createQuery(hql, values);
	}

	@Override
	public Query createQuery(String hql, Map<String, ?> map) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
