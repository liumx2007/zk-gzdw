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
import com.zzqx.mvc.dao.TerminalContentDao;
import com.zzqx.mvc.entity.TerminalContent;
import com.zzqx.mvc.service.TerminalContentService;
import com.zzqx.support.utils.StringHelper;

@Service("terminalContentService")
@Transactional
public class TerminalContentServiceImpl implements TerminalContentService {
	
	@Autowired
	private TerminalContentDao terminalContentDao;
	
	@Override
	public void delete(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			terminalContentDao.batchExecute("delete TerminalContent where id in ("+str+")");
			terminalContentDao.flush();
		}
	}

	@Override
	public List<TerminalContent> find(Criterion... criterions) {
		return terminalContentDao.find(criterions);
	}
	
	@Override
	public List<TerminalContent> get(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			return terminalContentDao.find("from TerminalContent where id in ("+str+")");
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public List<TerminalContent> getAll() {
		return terminalContentDao.getAll("number", true);
	}

	@Override
	public TerminalContent getById(String id) {
		return terminalContentDao.findUnique(Restrictions.eq("id", id));
	}

	@Override
	public void saveOrUpdate(TerminalContent terminalContent) {
		terminalContentDao.saveOrUpdate(terminalContent);
		terminalContentDao.flush();
	}

	@Override
	public Page<TerminalContent> getByPage(Map<String, Object> map, int pageNo, int pageSize) {
		return null;
	}

	@Override
	public Page<TerminalContent> getByPage(Map<String, Object> map, int pageNo, int pageSize, String orderBy, String order) {
		return null;
	}

	@Override
	public void executeHql(String hql, Object... values) {
		terminalContentDao.batchExecute(hql, values);
		terminalContentDao.flush();
	}

	@Override
	public Query createQuery(String hql, Object... values) {
		return terminalContentDao.createQuery(hql, values);
	}
	
	@Override
	public Query createQuery(String hql, Map<String, ?> map) {
		return terminalContentDao.createQuery(hql, map);
	}
	
	@Override
	public int getMinSort(String terminalId) {
		String hql = "select min(sort) from TerminalContent where terminal=(from Terminal where id = '"+terminalId+"')";
		Object obj = terminalContentDao.createQuery(hql).uniqueResult();
		if(obj==null)
			return 0;
		else {
			return Integer.parseInt(obj.toString());
		}
	}

	@Override
	public int getMaxSort(String terminalId) {
		String hql = "select max(sort) from TerminalContent where terminal=(from Terminal where id = '"+terminalId+"')";
		Object obj = terminalContentDao.createQuery(hql).uniqueResult();
		if(obj==null)
			return 0;
		else {
			return Integer.parseInt(obj.toString());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public TerminalContent getCloseSmall(String id) {
		TerminalContent terminalContent = terminalContentDao.get(id);
		if(terminalContent == null) {
			return null;
		}
		List<TerminalContent> list = terminalContentDao.createQuery(
				"from TerminalContent where sort<? and terminal = ? order by sort desc", 
				terminalContent.getSort(), terminalContent.getTerminal()).list();
		if(list.size()>0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public TerminalContent getCloseLarge(String id) {
		TerminalContent terminalContent = terminalContentDao.get(id);
		if(terminalContent == null) {
			return null;
		}
		List<TerminalContent> list = terminalContentDao.createQuery(
				"from TerminalContent where sort>? and terminal = ? order by sort asc", 
				terminalContent.getSort(), terminalContent.getTerminal()).list();
		if(list.size()>0) {
			return list.get(0);
		} else {
			return null;
		}
	}
}
