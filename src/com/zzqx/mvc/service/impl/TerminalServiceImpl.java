package com.zzqx.mvc.service.impl;

import com.jetsum.core.orm.entity.Page;
import com.zzqx.mvc.commons.CountInfo;
import com.zzqx.mvc.dao.GroupDao;
import com.zzqx.mvc.dao.TerminalDao;
import com.zzqx.mvc.dao.TerminalMybatisMapper;
import com.zzqx.mvc.entity.Group;
import com.zzqx.mvc.entity.Terminal;
import com.zzqx.mvc.entity.TerminalMybatis;
import com.zzqx.mvc.entity.TerminalMybatisExample;
import com.zzqx.mvc.service.TerminalService;
import com.zzqx.support.utils.StringHelper;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("terminalService")
@Transactional
public class TerminalServiceImpl implements TerminalService {
	
	@Autowired
	private TerminalDao terminalDao;
	@Autowired
	GroupDao groupDao;

	@Autowired
	TerminalMybatisMapper terminalMybatisMapper;
	
	@Override
	public void delete(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			terminalDao.batchExecute("delete Terminal where id in ("+str+")");
			terminalDao.flush();
		}
	}

	@Override
	public List<Terminal> find(Criterion... criterions) {
		return terminalDao.find(criterions);
	}
	
	@Override
	public List<Terminal> get(String... ids) {
		String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
		if(StringHelper.isNotBlank(str)) {
			return terminalDao.find("from Terminal where id in ("+str+")");
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public List<Terminal> getAll() {
		return terminalDao.getAll("serialNumber", true);
	}

	@Override
	public Terminal getById(String id) {
		return terminalDao.findUnique(Restrictions.eq("id", id));
	}

	@Override
	public void saveOrUpdate(Terminal terminal) {
		terminalDao.saveOrUpdate(terminal);
		terminalDao.flush();
	}

	@Override
	public Page<Terminal> getByPage(Map<String, Object> map, int pageNo, int pageSize) {
		return null;
	}

	@Override
	public Page<Terminal> getByPage(Map<String, Object> map, int pageNo, int pageSize, String orderBy, String order) {
		return null;
	}

	@Override
	public void executeHql(String hql, Object... values) {
		terminalDao.batchExecute(hql, values);
		terminalDao.flush();
	}

	@Override
	public Query createQuery(String hql, Object... values) {
		return terminalDao.createQuery(hql, values);
	}
	
	@Override
	public Query createQuery(String hql, Map<String, ?> map) {
		return terminalDao.createQuery(hql, map);
	}

	@Override
	public List<Terminal> getByGroup(Group group) {
		return terminalDao.find("from Terminal where group = ? order by serialNumber", group);
	}

	@Override
	public List<TerminalMybatis> getList() {
		TerminalMybatisExample terminalMybatisExample = new TerminalMybatisExample();
		CountInfo countInfo =new CountInfo();
		TerminalMybatisExample.Criteria  criteria= terminalMybatisExample.createCriteria();
		criteria.andHallIdEqualTo(countInfo.HALL_ID);
		List<TerminalMybatis> list =terminalMybatisMapper.selectByExample(terminalMybatisExample);
		return list;
	}

	@Override
	public List<TerminalMybatis> getListByStatus() {
		TerminalMybatisExample terminalMybatisExample = new TerminalMybatisExample();
		CountInfo countInfo =new CountInfo();
		TerminalMybatisExample.Criteria  criteria= terminalMybatisExample.createCriteria();
		criteria.andHallIdEqualTo(countInfo.HALL_ID).andStatusEqualTo("true");
		List<TerminalMybatis> list =terminalMybatisMapper.selectByExample(terminalMybatisExample);
		return list;
	}

	@Override
	public int insertBySelect(TerminalMybatis terminalMybatis) {
		List<Group> groups = groupDao.getAll();
		Group group = null;
		if (groups.size() > 0 ){
			group = groups.get(0);
		}else {
			CountInfo countInfo = new CountInfo();
			group.setCodeName(String.valueOf(countInfo.HALL_ID));
			group.setName("体验厅");
			group.setAddTime(new Date());
			groupDao.save(group);
		}
		terminalMybatis.setGroupId(group.getId());
		terminalMybatis.setStatus("false");
		terminalMybatis.setUpdateStatus(1);
		return terminalMybatisMapper.insertSelective(terminalMybatis);
	}

	@Override
	public int updateBySelect(TerminalMybatis terminalMybatis) {
		TerminalMybatis terminalMybatis1 = terminalMybatisMapper.selectByPrimaryKey(terminalMybatis.getId());
		if (terminalMybatis1 != null) {
			terminalMybatis.setUpdateStatus(1);
			terminalMybatisMapper.updateByPrimaryKeySelective(terminalMybatis);
		}
		return 0;
	}

}
