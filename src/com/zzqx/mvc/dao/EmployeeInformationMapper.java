package com.zzqx.mvc.dao;

import com.zzqx.mvc.entity.EmployeeInformation;
import com.zzqx.mvc.entity.EmployeeInformationExample;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployeeInformationMapper {
    long countByExample(EmployeeInformationExample example);

    int deleteByExample(EmployeeInformationExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(EmployeeInformation record);

    int insertSelective(EmployeeInformation record);

    List<EmployeeInformation> selectByExampleWithBLOBs(EmployeeInformationExample example);

    List<EmployeeInformation> selectByExample(EmployeeInformationExample example);

    EmployeeInformation selectByPrimaryKey(BigDecimal id);

    int updateByExampleSelective(@Param("record") EmployeeInformation record, @Param("example") EmployeeInformationExample example);

    int updateByExampleWithBLOBs(@Param("record") EmployeeInformation record, @Param("example") EmployeeInformationExample example);

    int updateByExample(@Param("record") EmployeeInformation record, @Param("example") EmployeeInformationExample example);

    int updateByPrimaryKeySelective(EmployeeInformation record);

    int updateByPrimaryKeyWithBLOBs(EmployeeInformation record);

    int updateByPrimaryKey(EmployeeInformation record);
}