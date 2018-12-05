package com.zzqx.mvc.dao;

import org.springframework.stereotype.Component;

import com.jetsum.core.orm.hibernate.HibernateDao;
import com.zzqx.mvc.entity.Queue;

@Component
public class QueueDao extends HibernateDao<Queue, String> {
}
