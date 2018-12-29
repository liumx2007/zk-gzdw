package com.zzqx.mvc.service;

import com.zzqx.mvc.dto.CmdListDto;
import com.zzqx.mvc.entity.CmdList;
import com.zzqx.mvc.vo.CmdListVo;

import java.util.List;

public interface CmdListService {

    /**
     * 全部数据and指令名字条件查询
     */
    List<CmdList> allList(String directName);

    /**
     * 分页数据List
     */
    List<CmdList> getList(CmdListDto cmdListDto);

    List<CmdList> getListCount(CmdListDto cmdListDto);

    /**
     * 根据主键查询
     */
    CmdListVo getById(String id );
    /**
     * 页面使用的单个数据
     */
//    CmdListVo getCmdVoById(String  id);
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

    /**
     * 主键批量删除
     */
    int deleteByIds(String ids);
}
