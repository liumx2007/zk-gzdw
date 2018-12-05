package com.zzqx.mvc.service;

import com.zzqx.mvc.entity.TerminalContent;

public interface TerminalContentService extends BaseService<TerminalContent> {
	int getMinSort(String terminalId);
	int getMaxSort(String terminalId);
	TerminalContent getCloseSmall(String id);
	TerminalContent getCloseLarge(String id);
}