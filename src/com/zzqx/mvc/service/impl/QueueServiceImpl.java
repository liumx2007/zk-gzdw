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
import com.zzqx.mvc.dao.QueueDao;
import com.zzqx.mvc.entity.Queue;
import com.zzqx.mvc.service.QueueService;
import com.zzqx.support.utils.StringHelper;

@Service("queueService")
@Transactional
public class QueueServiceImpl implements QueueService {
	
	@Autowired
	private QueueDao queueDao;
	
	@Override
	public void delete(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			queueDao.batchExecute("delete Queue where id in ("+str+")");
			queueDao.flush();
		}
	}

	@Override
	public List<Queue> find(Criterion... criterions) {
		return queueDao.find(criterions);
	}
	
	@Override
	public List<Queue> get(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			return queueDao.find("from Queue where id in ("+str+")");
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public List<Queue> getAll() {
		return queueDao.find("from Queue");
	}

	@Override
	public Queue getById(String id) {
		return queueDao.findUnique(Restrictions.eq("id", id));
	}

	@Override
	public void saveOrUpdate(Queue queue) {
		queueDao.saveOrUpdate(queue);
		queueDao.flush();
	}

	@Override
	public Page<Queue> getByPage(Map<String, Object> map, int pageNo, int pageSize) {
		Page<Queue> page = new Page<>();
		page.pageNo(pageNo).pageSize(pageSize).autoCount(true);
		return queueDao.findPage(page, map);
	}

	@Override
	public Page<Queue> getByPage(Map<String, Object> map, int pageNo, int pageSize, String orderBy, String order) {
		Page<Queue> page = new Page<>();
		page.pageNo(pageNo).pageSize(pageSize).autoCount(true).orderBy(orderBy).setOrder(order);
		return queueDao.findPage(page, map);
	}

	@Override
	public void executeHql(String hql, Object... values) {
		queueDao.batchExecute(hql, values);
		queueDao.flush();
	}

	@Override
	public Query createQuery(String hql, Object... values) {
		return queueDao.createQuery(hql, values);
	}

	@Override
	public Query createQuery(String hql, Map<String, ?> map) {
		return queueDao.createQuery(hql, map);
	}
}
