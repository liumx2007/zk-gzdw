package com.zzqx.mvc.dao;

import org.springframework.stereotype.Component;

import com.jetsum.core.orm.hibernate.HibernateDao;
import com.zzqx.mvc.entity.Order;

@Component
public class OrderDao extends HibernateDao<Order, String> {
}
