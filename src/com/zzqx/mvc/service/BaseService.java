package com.zzqx.mvc.service;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.criterion.Criterion;

import com.jetsum.core.orm.entity.Page;

public interface BaseService<T> {

	void executeHql(String hql, Object... values);

	void saveOrUpdate(T t);

	T getById(String id);

	void delete(String... ids);

	List<T> find(Criterion... criterions);

	List<T> getAll();

	List<T> get(String... ids);

	Page<T> getByPage(Map<String, Object> map, int pageNo, int pageSize);

	Page<T> getByPage(Map<String, Object> map, int pageNo, int pageSize, String orderBy, String order);

	Query createQuery(String hql, Object... values);

	Query createQuery(String hql, Map<String, ?> map);
}