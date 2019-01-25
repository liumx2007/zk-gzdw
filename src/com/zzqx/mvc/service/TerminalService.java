package com.zzqx.mvc.service;

import com.zzqx.mvc.entity.Group;
import com.zzqx.mvc.entity.Terminal;
import com.zzqx.mvc.entity.TerminalMybatis;

import java.util.List;

public interface TerminalService extends BaseService<Terminal> {

	List<Terminal> getByGroup(Group group);

	List<TerminalMybatis> getList();

	/**
	 * 设备新增
	 */
	int insertBySelect(TerminalMybatis terminalMybatis);
	/**
	 * 设备更新
	 */
	int updateBySelect(TerminalMybatis terminalMybatis);
}