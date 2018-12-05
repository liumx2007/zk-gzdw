package com.zzqx.mvc.dao;

import org.springframework.stereotype.Component;

import com.jetsum.core.orm.hibernate.HibernateDao;
import com.zzqx.mvc.entity.Subject;

@Component
public class SubjectDao extends HibernateDao<Subject, String> {
}
