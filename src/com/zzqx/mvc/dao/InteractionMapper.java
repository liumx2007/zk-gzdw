package com.zzqx.mvc.dao;

import com.zzqx.mvc.entity.Interaction;
import com.zzqx.mvc.entity.InteractionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InteractionMapper {
    long countByExample(InteractionExample example);

    int deleteByExample(InteractionExample example);

    int deleteByPrimaryKey(String id);

    int insert(Interaction record);

    int insertSelective(Interaction record);

    List<Interaction> selectByExample(InteractionExample example);

    Interaction selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Interaction record, @Param("example") InteractionExample example);

    int updateByExample(@Param("record") Interaction record, @Param("example") InteractionExample example);

    int updateByPrimaryKeySelective(Interaction record);

    int updateByPrimaryKey(Interaction record);
}