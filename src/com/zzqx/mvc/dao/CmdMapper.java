package com.zzqx.mvc.dao;

import com.zzqx.mvc.entity.Cmd;
import com.zzqx.mvc.entity.CmdExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmdMapper {
    long countByExample(CmdExample example);

    int deleteByExample(CmdExample example);

    int deleteByPrimaryKey(String id);

    int insert(Cmd record);

    int insertSelective(Cmd record);

    List<Cmd> selectByExample(CmdExample example);

    Cmd selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Cmd record, @Param("example") CmdExample example);

    int updateByExample(@Param("record") Cmd record, @Param("example") CmdExample example);

    int updateByPrimaryKeySelective(Cmd record);

    int updateByPrimaryKey(Cmd record);
}