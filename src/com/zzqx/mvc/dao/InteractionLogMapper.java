package com.zzqx.mvc.dao;

import com.zzqx.mvc.dto.InteractionLogDto;
import com.zzqx.mvc.entity.InteractionLog;
import com.zzqx.mvc.entity.InteractionLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InteractionLogMapper {
    long countByExample(InteractionLogExample example);

    int deleteByExample(InteractionLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(InteractionLog record);

    int insertSelective(InteractionLog record);

    List<InteractionLog> selectByExample(InteractionLogExample example);

    //有选择的条件查询
    List<InteractionLog> selectByEntity(InteractionLog interactionLog);

    //连表有选择查询
    List<InteractionLog> selectWithInteraction(InteractionLogDto interactionLogDto);

    //连表分页
    List<InteractionLog> selectWithInteractionTest(InteractionLogDto interactionLogDto);

    InteractionLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") InteractionLog record, @Param("example") InteractionLogExample example);

    int updateByExample(@Param("record") InteractionLog record, @Param("example") InteractionLogExample example);

    int updateByPrimaryKeySelective(InteractionLog record);

    int updateByPrimaryKey(InteractionLog record);

    InteractionLog getUpdateOne();
}