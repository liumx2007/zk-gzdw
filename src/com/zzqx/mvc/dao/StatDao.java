package com.zzqx.mvc.dao;

import org.springframework.stereotype.Component;

import com.jetsum.core.orm.hibernate.HibernateDao;
import com.zzqx.mvc.entity.Stat;

@Component
public class StatDao extends HibernateDao<Stat, String> {
}