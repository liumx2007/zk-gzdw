package com.zzqx.mvc.dao;

import com.zzqx.mvc.dto.CmdListDto;
import com.zzqx.mvc.entity.CmdList;
import com.zzqx.mvc.entity.CmdListExample;
import java.util.List;

import com.zzqx.mvc.vo.CmdListOneVo;
import org.apache.ibatis.annotations.Param;

public interface CmdListMapper {
    long countByExample(CmdListExample example);

    int deleteByExample(CmdListExample example);

    int deleteByPrimaryKey(String id);

    int insert(CmdList record);

    int insertSelective(CmdList record);

    List<CmdList> selectByExample(CmdListExample example);

    CmdList selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CmdList record, @Param("example") CmdListExample example);

    int updateByExample(@Param("record") CmdList record, @Param("example") CmdListExample example);

    int updateByPrimaryKeySelective(CmdList record);

    int updateByPrimaryKey(CmdList record);

    /**
     * 分页数据list  有选择的添加查询，两个查询配合使用
     * @param cmdListDto
     * @return
     */
    List<CmdList> getList(CmdListDto cmdListDto);
    List<CmdList> getListCount(CmdListDto cmdListDto);

    /**
     * 排除directLsit的所有数据
     */
    List<CmdListOneVo> getListExcludeDirectList();
}