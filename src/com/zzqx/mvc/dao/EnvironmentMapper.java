package com.zzqx.mvc.dao;

import com.zzqx.mvc.entity.Environment;
import com.zzqx.mvc.entity.EnvironmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EnvironmentMapper {
    long countByExample(EnvironmentExample example);

    int deleteByExample(EnvironmentExample example);

    int deleteByPrimaryKey(String id);

    int insert(Environment record);

    int insertSelective(Environment record);

    List<Environment> selectByExample(EnvironmentExample example);

    Environment selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Environment record, @Param("example") EnvironmentExample example);

    int updateByExample(@Param("record") Environment record, @Param("example") EnvironmentExample example);

    int updateByPrimaryKeySelective(Environment record);

    int updateByPrimaryKey(Environment record);
}