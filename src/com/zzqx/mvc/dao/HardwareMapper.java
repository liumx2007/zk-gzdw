package com.zzqx.mvc.dao;

import com.zzqx.mvc.entity.Hardware;
import com.zzqx.mvc.entity.HardwareExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HardwareMapper {
    long countByExample(HardwareExample example);

    int deleteByExample(HardwareExample example);

    int deleteByPrimaryKey(String id);

    int insert(Hardware record);

    int insertSelective(Hardware record);

    /**
     * 批量插入
     * @param list
     * @return
     */
    int batchInsert(List<Hardware> list);

    List<Hardware> selectByExample(HardwareExample example);

    Hardware selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Hardware record, @Param("example") HardwareExample example);

    int updateByExample(@Param("record") Hardware record, @Param("example") HardwareExample example);

    int updateByPrimaryKeySelective(Hardware record);

    int updateByPrimaryKey(Hardware record);
}