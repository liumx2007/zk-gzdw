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
import com.zzqx.mvc.dao.OrderDao;
import com.zzqx.mvc.entity.Order;
import com.zzqx.mvc.service.OrderService;
import com.zzqx.support.utils.StringHelper;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public void delete(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			orderDao.batchExecute("delete Order where id in ("+str+")");
			orderDao.flush();
		}
	}

	@Override
	public List<Order> find(Criterion... criterions) {
		return orderDao.find(criterions);
	}
	
	@Override
	public List<Order> get(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			return orderDao.find("from Order where id in ("+str+")");
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public List<Order> getAll() {
		return orderDao.find("from Order order by date desc,apm asc");
	}

	@Override
	public Order getById(String id) {
		return orderDao.findUnique(Restrictions.eq("id", id));
	}

	@Override
	public void saveOrUpdate(Order Order) {
		orderDao.saveOrUpdate(Order);
		orderDao.flush();
	}

	@Override
	public Page<Order> getByPage(Map<String, Object> map, int pageNo, int pageSize) {
		Page<Order> page = new Page<>();
		page.pageNo(pageNo).pageSize(pageSize).autoCount(true);
		return orderDao.findPage(page, map);
	}

	@Override
	public Page<Order> getByPage(Map<String, Object> map, int pageNo, int pageSize, String orderBy, String order) {
		Page<Order> page = new Page<>();
		page.pageNo(pageNo).pageSize(pageSize).autoCount(true).orderBy(orderBy).setOrder(order);
		return orderDao.findPage(page, map);
	}

	@Override
	public void executeHql(String hql, Object... values) {
		orderDao.batchExecute(hql, values);
		orderDao.flush();
	}

	@Override
	public Query createQuery(String hql, Object... values) {
		return orderDao.createQuery(hql, values);
	}

	@Override
	public Order getCurrent() {
		return orderDao.findUnique(Restrictions.eq("status", Order.STATUS_CURRENT));
	}
	
	@Override
	public Query createQuery(String hql, Map<String, ?> map) {
		return orderDao.createQuery(hql, map);
	}
}
