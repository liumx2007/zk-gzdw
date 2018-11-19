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
import com.zzqx.mvc.dao.ItemDao;
import com.zzqx.mvc.entity.Item;
import com.zzqx.mvc.service.ItemService;
import com.zzqx.support.utils.StringHelper;

@Service("itemService")
@Transactional
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemDao itemDao;
	
	@Override
	public void delete(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			itemDao.batchExecute("delete Item where id in ("+str+")");
			itemDao.flush();
		}
	}

	@Override
	public List<Item> find(Criterion... criterions) {
		return itemDao.find(criterions);
	}
	
	@Override
	public List<Item> get(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			return itemDao.find("from Item where id in ("+str+")");
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public List<Item> getAll() {
		return itemDao.find("from Item order by date desc,apm asc");
	}

	@Override
	public Item getById(String id) {
		return itemDao.findUnique(Restrictions.eq("id", id));
	}
	
	@Override
	public void saveOrUpdate(Item Item) {
		itemDao.saveOrUpdate(Item);
		itemDao.flush();
	}

	@Override
	public Page<Item> getByPage(Map<String, Object> map, int pageNo, int pageSize) {
		Page<Item> page = new Page<>();
		page.pageNo(pageNo).pageSize(pageSize).autoCount(true);
		return itemDao.findPage(page, map);
	}

	@Override
	public Page<Item> getByPage(Map<String, Object> map, int pageNo, int pageSize, String orderBy, String order) {
		Page<Item> page = new Page<>();
		page.pageNo(pageNo).pageSize(pageSize).autoCount(true).orderBy(orderBy).setOrder(order);
		return itemDao.findPage(page, map);
	}

	@Override
	public void executeHql(String hql, Object... values) {
		itemDao.batchExecute(hql, values);
		itemDao.flush();
	}

	@Override
	public Query createQuery(String hql, Object... values) {
		return itemDao.createQuery(hql, values);
	}

	@Override
	public Query createQuery(String hql, Map<String, ?> map) {
		return itemDao.createQuery(hql, map);
	}
}
