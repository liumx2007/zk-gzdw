package com.zzqx.mvc.dao;

import org.springframework.stereotype.Component;

import com.jetsum.core.orm.hibernate.HibernateDao;
import com.zzqx.mvc.entity.Message;

@Component
public class MessageDao extends HibernateDao<Message, String> {
}