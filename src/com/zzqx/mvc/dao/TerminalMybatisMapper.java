package com.zzqx.mvc.dao;

import com.zzqx.mvc.entity.TerminalMybatis;
import com.zzqx.mvc.entity.TerminalMybatisExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TerminalMybatisMapper {
    long countByExample(TerminalMybatisExample example);

    int deleteByExample(TerminalMybatisExample example);

    int deleteByPrimaryKey(String id);

    int insert(TerminalMybatis record);

    int insertSelective(TerminalMybatis record);

    List<TerminalMybatis> selectByExample(TerminalMybatisExample example);

    TerminalMybatis selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TerminalMybatis record, @Param("example") TerminalMybatisExample example);

    int updateByExample(@Param("record") TerminalMybatis record, @Param("example") TerminalMybatisExample example);

    int updateByPrimaryKeySelective(TerminalMybatis record);

    int updateByPrimaryKey(TerminalMybatis record);
}