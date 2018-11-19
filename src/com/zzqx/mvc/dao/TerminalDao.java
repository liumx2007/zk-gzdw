package com.zzqx.mvc.dao;

import org.springframework.stereotype.Component;

import com.jetsum.core.orm.hibernate.HibernateDao;
import com.zzqx.mvc.entity.Terminal;

@Component
public class TerminalDao extends HibernateDao<Terminal, String> {
}
