package com.zzqx.mvc.dao;

import org.springframework.stereotype.Component;

import com.jetsum.core.orm.hibernate.HibernateDao;
import com.zzqx.mvc.entity.Personnel;

@Component
public class PersonnelDao extends HibernateDao<Personnel, String> {
}