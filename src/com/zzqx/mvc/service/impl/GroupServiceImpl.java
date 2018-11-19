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
import com.zzqx.mvc.dao.GroupDao;
import com.zzqx.mvc.entity.Group;
import com.zzqx.mvc.service.GroupService;
import com.zzqx.support.utils.StringHelper;

@Service("groupService")
@Transactional
public class GroupServiceImpl implements GroupService {
	
	@Autowired
	private GroupDao groupDao;
	
	@Override
	public void delete(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			groupDao.batchExecute("delete Group where id in ("+str+")");
			groupDao.flush();
		}
	}

	@Override
	public List<Group> find(Criterion... criterions) {
		return groupDao.find(criterions);
	}
	
	@Override
	public List<Group> get(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			return groupDao.find("from Group where id in ("+str+")");
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public List<Group> getAll() {
		return groupDao.find("from Group order by addTime");
	}

	@Override
	public Group getById(String id) {
		return groupDao.findUnique(Restrictions.eq("id", id));
	}
	
	@Override
	public void saveOrUpdate(Group Group) {
		groupDao.saveOrUpdate(Group);
		groupDao.flush();
	}

	@Override
	public Page<Group> getByPage(Map<String, Object> map, int pageNo, int pageSize) {
		Page<Group> page = new Page<>();
		page.pageNo(pageNo).pageSize(pageSize).autoCount(true);
		return groupDao.findPage(page, map);
	}

	@Override
	public Page<Group> getByPage(Map<String, Object> map, int pageNo, int pageSize, String orderBy, String order) {
		Page<Group> page = new Page<>();
		page.pageNo(pageNo).pageSize(pageSize).autoCount(true).orderBy(orderBy).setOrder(order);
		return groupDao.findPage(page, map);
	}

	@Override
	public void executeHql(String hql, Object... values) {
		groupDao.batchExecute(hql, values);
		groupDao.flush();
	}

	@Override
	public Query createQuery(String hql, Object... values) {
		return groupDao.createQuery(hql, values);
	}

	@Override
	public Query createQuery(String hql, Map<String, ?> map) {
		return groupDao.createQuery(hql, map);
	}

	@Override
	public Group getByCodeName(String codeName) {
		return groupDao.findUnique(Restrictions.eq("codeName", codeName));
	}
}
