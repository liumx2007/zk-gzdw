package com.zzqx.mvc.service;

import com.zzqx.mvc.entity.Group;

public interface GroupService extends BaseService<Group> {
	
	Group getByCodeName(String codeName);
	
}