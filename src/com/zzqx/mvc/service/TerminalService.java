package com.zzqx.mvc.service;

import com.zzqx.mvc.entity.Group;
import com.zzqx.mvc.entity.Terminal;
import com.zzqx.mvc.entity.TerminalMybatis;
import com.zzqx.mvc.vo.TerminalVo;

import java.util.List;

public interface TerminalService extends BaseService<Terminal> {

	List<Terminal> getByGroup(Group group);

	List<TerminalMybatis> getList();
	
}