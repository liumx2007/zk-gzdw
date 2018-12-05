package com.zzqx.mvc.service;

import java.util.List;

import com.zzqx.mvc.entity.Group;
import com.zzqx.mvc.entity.Terminal;

public interface TerminalService extends BaseService<Terminal> {

	List<Terminal> getByGroup(Group group);
	
}