package com.zzqx.mvc.service;

import com.zzqx.mvc.entity.Group;
import com.zzqx.mvc.entity.Terminal;
import com.zzqx.mvc.entity.TerminalMybatis;

import java.util.List;

public interface TerminalService extends BaseService<Terminal> {

	List<Terminal> getByGroup(Group group);

	/**
	 * 获取设备列表
	 * @return
	 */
	List<TerminalMybatis> getList();

	/**
	 * 获取开机状态的设备列表
	 * @return
	 */
	List<TerminalMybatis> getListByStatus();

	/**
	 * 设备新增
	 */
	int insertBySelect(TerminalMybatis terminalMybatis);
	/**
	 * 设备更新
	 */
	int updateBySelect(TerminalMybatis terminalMybatis);
}