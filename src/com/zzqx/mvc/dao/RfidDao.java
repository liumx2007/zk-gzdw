package com.zzqx.mvc.dao;

import org.springframework.stereotype.Component;

import com.jetsum.core.orm.hibernate.HibernateDao;
import com.zzqx.mvc.entity.Rfid;

@Component
public class RfidDao extends HibernateDao<Rfid, String> {
}
