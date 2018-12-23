package com.zzqx.mvc.service;

import com.zzqx.mvc.dto.CmdListDto;
import com.zzqx.mvc.entity.CmdList;

import java.util.List;

public interface CmdListService {

    /**
     * 分页数据List
     */
    List<CmdList> getList(CmdListDto cmdListDto);

    List<CmdList> getListCount(CmdListDto cmdListDto);

    /**
     * 有选择的插入数据
     */
    int insertSelect(CmdList cmdList);

    /**
     * 有选择的更新数据
     */
     int updateSelect(CmdList cmdList);

    /**
     * 根据主键删除
     */
    int deleteByPrimaryKey(String id);
}
