package com.zzqx.mvc.service;

import com.zzqx.mvc.dto.FolderContentDto;
import com.zzqx.mvc.entity.Content;
import com.zzqx.mvc.vo.ContentStatusVo;

import java.util.List;

public interface ContentService extends BaseService<Content> {
	int getMinSort(String terminalId);
	int getMaxSort(String terminalId);
	Content getCloseSmall(String id);
	Content getCloseLarge(String id);
	List<FolderContentDto> selectByCheckStatus();
	void changeStatus(ContentStatusVo contentStatusVo);
}