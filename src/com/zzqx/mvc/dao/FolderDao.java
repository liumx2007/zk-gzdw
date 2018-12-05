package com.zzqx.mvc.dao;

import org.springframework.stereotype.Component;

import com.jetsum.core.orm.hibernate.HibernateDao;
import com.zzqx.mvc.entity.Folder;

@Component
public class FolderDao extends HibernateDao<Folder, String> {
}
