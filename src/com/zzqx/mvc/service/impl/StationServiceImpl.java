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
import com.zzqx.mvc.dao.StationDao;
import com.zzqx.mvc.entity.Station;
import com.zzqx.mvc.service.StationService;
import com.zzqx.support.utils.StringHelper;

@Service("stationService")
@Transactional
public class StationServiceImpl implements StationService {
	
	@Autowired
	private StationDao stationDao;
	
	@Override
	public void delete(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			stationDao.batchExecute("delete Station where id in ("+str+")");
			stationDao.flush();
		}
	}

	@Override
	public List<Station> find(Criterion... criterions) {
		return stationDao.find(criterions);
	}
	
	@Override
	public List<Station> get(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			return stationDao.find("from Station where id in ("+str+")");
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public List<Station> getAll() {
		return stationDao.find("from Station");
	}

	@Override
	public Station getById(String id) {
		return stationDao.findUnique(Restrictions.eq("id", id));
	}

	@Override
	public void saveOrUpdate(Station station) {
		stationDao.saveOrUpdate(station);
		stationDao.flush();
	}

	@Override
	public Page<Station> getByPage(Map<String, Object> map, int pageNo, int pageSize) {
		Page<Station> page = new Page<>();
		page.pageNo(pageNo).pageSize(pageSize).autoCount(true);
		return stationDao.findPage(page, map);
	}

	@Override
	public Page<Station> getByPage(Map<String, Object> map, int pageNo, int pageSize, String orderBy, String order) {
		Page<Station> page = new Page<>();
		page.pageNo(pageNo).pageSize(pageSize).autoCount(true).orderBy(orderBy).setOrder(order);
		return stationDao.findPage(page, map);
	}

	@Override
	public void executeHql(String hql, Object... values) {
		stationDao.batchExecute(hql, values);
		stationDao.flush();
	}

	@Override
	public Query createQuery(String hql, Object... values) {
		return stationDao.createQuery(hql, values);
	}

	@Override
	public Query createQuery(String hql, Map<String, ?> map) {
		return stationDao.createQuery(hql, map);
	}
}
