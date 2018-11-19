package com.zzqx.mvc.service.impl;

import com.jetsum.core.orm.entity.Page;
import com.zzqx.mvc.dao.RecordDao;
import com.zzqx.mvc.entity.Answer;
import com.zzqx.mvc.entity.Record;
import com.zzqx.mvc.service.RecordService;
import com.zzqx.support.utils.StringHelper;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("recordService")
@Transactional
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordDao recordDao;


    /**
     * 全数据查询
     */
    @Override
    public List<Record> getAll(){
        return recordDao.getAll();
    }

    /**
     * 主键查询单数据
     * @param id
     * @return
     */
    @Override
    public Record getById(String id) {
        return recordDao.get(id);
    }

    /**
     * 条件查询
     * @param criterions
     * @return
     */
    @Override
    public List<Record> find(Criterion... criterions) {
        return recordDao.find(criterions);
    }
    @Override
    public Page<Record> getByPage(Map<String, Object> map, int pageNo, int pageSize) {
        Page<Record> page = new Page<>();
        page.pageNo(pageNo).pageSize(pageSize).autoCount(true);
        return recordDao.findPage(page, map);
    }

    @Override
    public Page<Record> getByPage(Map<String, Object> map, int pageNo, int pageSize, String orderBy, String order) {
        Page<Record> page = new Page<>();
        page.pageNo(pageNo).pageSize(pageSize).autoCount(true).orderBy(orderBy).setOrder(order);
        return recordDao.findPage(page, map);
    }

    /**
     * 主键删除
     * @param ids
     */
    @Override
    public void delete(String... ids) {
        String str = Stream.of(ids).map(id->"'"+id+"'").collect(Collectors.joining(","));
        if(StringHelper.isNotBlank(str)) {
            recordDao.batchExecute("delete Record where id in ("+str+")");
            recordDao.flush();
        }
    }

    @Override
    public void saveOrUpdate(Record record) {
        recordDao.saveOrUpdate(record);
        recordDao.flush();
    }

    @Override
    public void executeHql(String hql, Object... values) {

    }

    @Override
    public List<Record> get(String... ids) {
        return null;
    }


    @Override
    public Query createQuery(String hql, Object... values) {
        return null;
    }

    @Override
    public Query createQuery(String hql, Map<String, ?> map) {
        return null;
    }
}
