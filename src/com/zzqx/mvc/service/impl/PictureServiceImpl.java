package com.zzqx.mvc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.zzqx.mvc.entity.Picture;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jetsum.core.orm.entity.Page;
import com.zzqx.mvc.dao.PictureDao;
import com.zzqx.mvc.service.PictureService;
import com.zzqx.support.utils.StringHelper;

@Service("pictureService")
@Transactional
public class PictureServiceImpl implements PictureService {
	
	@Autowired
	private PictureDao pictureDao;
	
	@Override
	public void delete(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			pictureDao.batchExecute("delete Picture where id in ("+str+")");
			pictureDao.flush();
		}
	}

	@Override
	public List<Picture> find(Criterion... criterions) {
		return pictureDao.find(criterions);
	}
	
	@Override
	public List<Picture> get(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			return pictureDao.find("from Picture where id in ("+str+")");
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public List<Picture> getAll() {
		return pictureDao.getAll();
	}

	@Override
	public Picture getById(String id) {
		return pictureDao.findUnique(Restrictions.eq("id", id));
	}

	@Override
	public void saveOrUpdate(Picture ScanPicture) {
		pictureDao.saveOrUpdate(ScanPicture);
		pictureDao.flush();
	}

	@Override
	public Page<Picture> getByPage(Map<String, Object> map, int pageNo, int pageSize) {
		Page<Picture> page = new Page<>();
		page.pageNo(pageNo).pageSize(pageSize).autoCount(true);
		return pictureDao.findPage(page, map);
	}

	@Override
	public Page<Picture> getByPage(Map<String, Object> map, int pageNo, int pageSize, String orderBy, String order) {
		Page<Picture> page = new Page<>();
		page.pageNo(pageNo).pageSize(pageSize).autoCount(true).orderBy(orderBy).setOrder(order);
		return pictureDao.findPage(page, map);
	}

	@Override
	public void executeHql(String hql, Object... values) {
		pictureDao.batchExecute(hql, values);
		pictureDao.flush();
	}

	@Override
	public Query createQuery(String hql, Object... values) {
		return pictureDao.createQuery(hql, values);
	}

	@Override
	public Query createQuery(String hql, Map<String, ?> map) {
		return pictureDao.createQuery(hql, map);
	}
}
