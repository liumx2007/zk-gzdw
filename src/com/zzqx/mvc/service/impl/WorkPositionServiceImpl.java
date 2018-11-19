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
import com.zzqx.mvc.dao.ArrangeDateDao;
import com.zzqx.mvc.dao.WorkPositionDao;
import com.zzqx.mvc.entity.ArrangeDate;
import com.zzqx.mvc.entity.WorkPosition;
import com.zzqx.mvc.service.ArrangeDateService;
import com.zzqx.mvc.service.WorkPositionService;
import com.zzqx.support.utils.StringHelper;

@Service("workPositionService")
@Transactional
public class WorkPositionServiceImpl implements WorkPositionService {
 
	@Autowired
	private WorkPositionDao workPositionDao;

	@Override
	public void executeHql(String hql, Object... values) {
		// TODO Auto-generated method stub
		workPositionDao.batchExecute(hql, values);
		workPositionDao.flush();
	}

	@Override
	public void saveOrUpdate(WorkPosition t) {
		workPositionDao.saveOrUpdate(t);
		workPositionDao.flush();
	}

	@Override
	public WorkPosition getById(String id) {
		// TODO Auto-generated method stub
		return workPositionDao.get(id);
	}

	@Override
	public void delete(String... ids) {
		String str = Stream.of(ids).map(id -> "'" + id + "'").collect(Collectors.joining(","));
		if (StringHelper.isNotBlank(str)) {
			workPositionDao.batchExecute("delete WorkPosition where id in (" + str + ")");
			workPositionDao.flush();
		}
	}

	@Override
	public List<WorkPosition> find(Criterion... criterions) {
		// TODO Auto-generated method stub
		return workPositionDao.find(criterions);
	}

	@Override
	public List<WorkPosition> getAll() {
		// TODO Auto-generated method stub
		return workPositionDao.getAll();
	}

	@Override
	public List<WorkPosition> get(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			return workPositionDao.find("from WorkPosition where id in ("+str+")");
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public Page<WorkPosition> getByPage(Map<String, Object> map, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<WorkPosition> getByPage(Map<String, Object> map, int pageNo, int pageSize, String orderBy,
			String order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query createQuery(String hql, Object... values) {
		return workPositionDao.createQuery(hql, values);
	}

	@Override
	public Query createQuery(String hql, Map<String, ?> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
