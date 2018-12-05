package com.zzqx.mvc.dao;

import com.jetsum.core.orm.hibernate.HibernateDao;
import com.zzqx.mvc.entity.Record;
import org.springframework.stereotype.Component;

@Component
public class RecordDao extends HibernateDao<Record,String> {

}
