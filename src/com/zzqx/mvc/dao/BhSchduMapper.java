package com.zzqx.mvc.dao;

import com.zzqx.mvc.entity.BhSchdu;
import com.zzqx.mvc.entity.BhSchduExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BhSchduMapper {
    long countByExample(BhSchduExample example);

    int deleteByExample(BhSchduExample example);

    int deleteByPrimaryKey(String id);

    int insert(BhSchdu record);

    int insertSelective(BhSchdu record);

    List<BhSchdu> selectByExample(BhSchduExample example);

    BhSchdu selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BhSchdu record, @Param("example") BhSchduExample example);

    int updateByExample(@Param("record") BhSchdu record, @Param("example") BhSchduExample example);

    int updateByPrimaryKeySelective(BhSchdu record);

    int updateByPrimaryKey(BhSchdu record);
}