package com.zzqx.mvc.dao;

import org.springframework.stereotype.Component;

import com.jetsum.core.orm.hibernate.HibernateDao;
import com.zzqx.mvc.entity.Answer;

@Component
public class AnswerDao extends HibernateDao<Answer, String> {
}
