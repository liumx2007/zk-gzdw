package com.zzqx.mvc.dao;

import com.zzqx.mvc.entity.Picture;
import org.springframework.stereotype.Component;

import com.jetsum.core.orm.hibernate.HibernateDao;

@Component
public class PictureDao extends HibernateDao<Picture, String> {
}
