package com.zzqx.mvc.dao;

import com.zzqx.mvc.entity.EmployeeJobs;
import com.zzqx.mvc.entity.EmployeeJobsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployeeJobsMapper {
    long countByExample(EmployeeJobsExample example);

    int deleteByExample(EmployeeJobsExample example);

    int insert(EmployeeJobs record);

    int insertSelective(EmployeeJobs record);

    List<EmployeeJobs> selectByExample(EmployeeJobsExample example);

    int updateByExampleSelective(@Param("record") EmployeeJobs record, @Param("example") EmployeeJobsExample example);

    int updateByExample(@Param("record") EmployeeJobs record, @Param("example") EmployeeJobsExample example);
}