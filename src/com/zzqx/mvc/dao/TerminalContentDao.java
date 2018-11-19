package com.zzqx.mvc.dao;

import org.springframework.stereotype.Component;

import com.jetsum.core.orm.hibernate.HibernateDao;
import com.zzqx.mvc.entity.TerminalContent;

@Component
public class TerminalContentDao extends HibernateDao<TerminalContent, String> {
}
