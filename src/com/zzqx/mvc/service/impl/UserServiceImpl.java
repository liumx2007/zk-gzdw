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
import com.zzqx.mvc.dao.UserDao;
import com.zzqx.mvc.entity.User;
import com.zzqx.mvc.service.UserService;
import com.zzqx.support.utils.StringHelper;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public void delete(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			userDao.batchExecute("delete User where id in ("+str+")");
			userDao.flush();
		}
	}

	@Override
	public List<User> find(Criterion... criterions) {
		return userDao.find(criterions);
	}
	
	@Override
	public List<User> get(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			return userDao.find("from User where id in ("+str+")");
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

	@Override
	public User getById(String id) {
		return userDao.findUnique(Restrictions.eq("id", id));
	}

	@Override
	public void saveOrUpdate(User user) {
		userDao.saveOrUpdate(user);
		userDao.flush();
	}

	@Override
	public Page<User> getByPage(Map<String, Object> map, int pageNo, int pageSize) {
		return null;
	}

	@Override
	public Page<User> getByPage(Map<String, Object> map, int pageNo, int pageSize, String orderBy, String order) {
		return null;
	}

	@Override
	public void executeHql(String hql, Object... values) {
		userDao.batchExecute(hql);
		userDao.flush();
	}

	@Override
	public Query createQuery(String hql, Object... values) {
		return userDao.createQuery(hql, values);
	}
	
	@Override
	public Query createQuery(String hql, Map<String, ?> map) {
		return userDao.createQuery(hql, map);
	}
}
