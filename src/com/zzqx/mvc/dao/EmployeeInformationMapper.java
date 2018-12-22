package com.zzqx.mvc.dao;

import com.zzqx.mvc.dto.EmployeeInformationDto;
import com.zzqx.mvc.entity.EmployeeInformation;
import com.zzqx.mvc.entity.EmployeeInformationExample;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeInformationMapper {
    long countByExample(EmployeeInformationExample example);

    int deleteByExample(EmployeeInformationExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(EmployeeInformation record);

    /**
     * 批量更新或插入
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<EmployeeInformation> list);

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

    List<EmployeeInformationDto> selectAll(EmployeeInformationExample employeeInformationExample);


}