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
import com.zzqx.mvc.dao.AnswerDao;
import com.zzqx.mvc.entity.Answer;
import com.zzqx.mvc.service.AnswerService;
import com.zzqx.support.utils.StringHelper;

@Service("answerService")
@Transactional
public class AnswerServiceImpl implements AnswerService {
	
	@Autowired
	private AnswerDao answerDao;
	
	@Override
	public void delete(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			answerDao.batchExecute("delete Answer where id in ("+str+")");
			answerDao.flush();
		}
	}

	@Override
	public List<Answer> find(Criterion... criterions) {
		return answerDao.find(criterions);
	}
	
	@Override
	public List<Answer> get(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			return answerDao.find("from Answer where id in ("+str+")");
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public List<Answer> getAll() {
		return answerDao.getAll();
	}

	@Override
	public Answer getById(String id) {
		return answerDao.findUnique(Restrictions.eq("id", id));
	}
	
	@Override
	public void saveOrUpdate(Answer Answer) {
		answerDao.saveOrUpdate(Answer);
		answerDao.flush();
	}

	@Override
	public Page<Answer> getByPage(Map<String, Object> map, int pageNo, int pageSize) {
		Page<Answer> page = new Page<>();
		page.pageNo(pageNo).pageSize(pageSize).autoCount(true);
		return answerDao.findPage(page, map);
	}

	@Override
	public Page<Answer> getByPage(Map<String, Object> map, int pageNo, int pageSize, String orderBy, String order) {
		Page<Answer> page = new Page<>();
		page.pageNo(pageNo).pageSize(pageSize).autoCount(true).orderBy(orderBy).setOrder(order);
		return answerDao.findPage(page, map);
	}

	@Override
	public void executeHql(String hql, Object... values) {
		answerDao.batchExecute(hql, values);
		answerDao.flush();
	}

	@Override
	public Query createQuery(String hql, Object... values) {
		return answerDao.createQuery(hql, values);
	}

	@Override
	public Query createQuery(String hql, Map<String, ?> map) {
		return answerDao.createQuery(hql, map);
	}
	
	@Override
	public int getMinSort(String subjectId) {
		String hql = "select min(sort) from Answer where subject=(from Subject where id = '"+subjectId+"')";
		Object obj = answerDao.createQuery(hql).uniqueResult();
		if(obj==null)
			return 0;
		else {
			return Integer.parseInt(obj.toString());
		}
	}

	@Override
	public int getMaxSort(String subjectId) {
		String hql = "select max(sort) from Answer where subject=(from Subject where id = '"+subjectId+"')";
		Object obj = answerDao.createQuery(hql).uniqueResult();
		if(obj==null)
			return 0;
		else {
			return Integer.parseInt(obj.toString());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Answer getCloseSmall(String id) {
		Answer answer = answerDao.get(id);
		if(answer == null) {
			return null;
		}
		List<Answer> list = answerDao.createQuery(
				"from Answer where sort<? and subject = ? order by sort desc", 
				answer.getSort(), answer.getSubject()).list();
		if(list.size()>0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Answer getCloseLarge(String id) {
		Answer answer = answerDao.get(id);
		if(answer == null) {
			return null;
		}
		List<Answer> list = answerDao.createQuery(
				"from Answer where sort>? and subject = ? order by sort asc", 
				answer.getSort(), answer.getSubject()).list();
		if(list.size()>0) {
			return list.get(0);
		} else {
			return null;
		}
	}
}
