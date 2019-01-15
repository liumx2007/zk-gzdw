package com.zzqx.mvc.service.impl;

import com.jetsum.core.orm.entity.Page;
import com.zzqx.mvc.dao.YjDao;
import com.zzqx.mvc.entity.Yj;
import com.zzqx.mvc.service.YjService;
import com.zzqx.support.utils.StringHelper;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("yjService")
@Transactional
public class YjServiceImpl implements YjService {
	
	@Autowired
	private YjDao yjDao;
	
	@Override
	public void delete(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			yjDao.batchExecute("delete Yj where id in ("+str+")");
			yjDao.flush();
		}
	}

	@Override
	public List<Yj> find(Criterion... criterions) {
		return yjDao.find(criterions);
	}
	
	@Override
	public List<Yj> get(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			return yjDao.find("from Yj where id in ("+str+") order by flag desc,flag_time desc,add_time desc");
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public List<Yj> getAll() {
		return yjDao.find("from Yj");
	}

	@Override
	public Yj getById(String id) {
		return yjDao.findUnique(Restrictions.eq("id", id));
	}

	@Override
	public void saveOrUpdate(Yj yj) {
		yjDao.saveOrUpdate(yj);
		yjDao.flush();
	}

	@Override
	public Page<Yj> getByPage(Map<String, Object> map, int pageNo, int pageSize) {
		Page<Yj> page = new Page<>();
		page.pageNo(pageNo).pageSize(pageSize).autoCount(true);
		return yjDao.findPage(page, map);
	}

	@Override
	public Page<Yj> getByPage(Map<String, Object> map, int pageNo, int pageSize, String orderBy, String order) {
		Page<Yj> page = new Page<>();
		page.pageNo(pageNo).pageSize(pageSize).autoCount(true).orderBy(orderBy).setOrder(order);
		return yjDao.findPage(page, map);
	}

	@Override
	public void executeHql(String hql, Object... values) {
		yjDao.batchExecute(hql, values);
		yjDao.flush();
	}

	@Override
	public Query createQuery(String hql, Object... values) {
		return yjDao.createQuery(hql, values);
	}

	@Override
	public Query createQuery(String hql, Map<String, ?> map) {
		return yjDao.createQuery(hql, map);
	}

	//todo
	public  String test(){
			throw new RuntimeException();
	}
}
