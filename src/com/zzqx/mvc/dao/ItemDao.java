package com.zzqx.mvc.dao;

import org.springframework.stereotype.Component;

import com.jetsum.core.orm.hibernate.HibernateDao;
import com.zzqx.mvc.entity.Item;

@Component
public class ItemDao extends HibernateDao<Item, String> {
}
