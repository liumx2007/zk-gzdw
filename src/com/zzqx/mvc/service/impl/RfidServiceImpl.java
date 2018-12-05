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
import com.zzqx.mvc.dao.RfidDao;
import com.zzqx.mvc.entity.Rfid;
import com.zzqx.mvc.service.RfidService;
import com.zzqx.support.utils.StringHelper;

@Service("rfidService")
@Transactional
public class RfidServiceImpl implements RfidService {
	
	@Autowired
	private RfidDao rfidDao;
	
	@Override
	public void delete(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			rfidDao.batchExecute("delete Rfid where id in ("+str+")");
			rfidDao.flush();
		}
	}

	@Override
	public List<Rfid> find(Criterion... criterions) {
		return rfidDao.find(criterions);
	}
	
	@Override
	public List<Rfid> get(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			return rfidDao.find("from Rfid where id in ("+str+")");
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public List<Rfid> getAll() {
		return rfidDao.find("from Rfid order by date desc,apm asc");
	}

	@Override
	public Rfid getById(String id) {
		return rfidDao.findUnique(Restrictions.eq("id", id));
	}
	
	@Override
	public void saveOrUpdate(Rfid Rfid) {
		rfidDao.saveOrUpdate(Rfid);
		rfidDao.flush();
	}

	@Override
	public Page<Rfid> getByPage(Map<String, Object> map, int pageNo, int pageSize) {
		Page<Rfid> page = new Page<>();
		page.pageNo(pageNo).pageSize(pageSize).autoCount(true);
		return rfidDao.findPage(page, map);
	}

	@Override
	public Page<Rfid> getByPage(Map<String, Object> map, int pageNo, int pageSize, String orderBy, String order) {
		Page<Rfid> page = new Page<>();
		page.pageNo(pageNo).pageSize(pageSize).autoCount(true).orderBy(orderBy).setOrder(order);
		return rfidDao.findPage(page, map);
	}

	@Override
	public void executeHql(String hql, Object... values) {
		rfidDao.batchExecute(hql, values);
		rfidDao.flush();
	}

	@Override
	public Query createQuery(String hql, Object... values) {
		return rfidDao.createQuery(hql, values);
	}

	@Override
	public Query createQuery(String hql, Map<String, ?> map) {
		return rfidDao.createQuery(hql, map);
	}
}
