package com.zzqx.mvc.dao;

import com.zzqx.mvc.entity.DelTerminal;
import com.zzqx.mvc.entity.DelTerminalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DelTerminalMapper {
    long countByExample(DelTerminalExample example);

    int deleteByExample(DelTerminalExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DelTerminal record);

    int insertSelective(DelTerminal record);

    List<DelTerminal> selectByExample(DelTerminalExample example);

    DelTerminal selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DelTerminal record, @Param("example") DelTerminalExample example);

    int updateByExample(@Param("record") DelTerminal record, @Param("example") DelTerminalExample example);

    int updateByPrimaryKeySelective(DelTerminal record);

    int updateByPrimaryKey(DelTerminal record);
}