package com.zzqx.mvc.service.impl;

import com.zzqx.mvc.dao.EmployeeInformationMapper;
import com.zzqx.mvc.entity.EmployeeInformation;
import com.zzqx.mvc.entity.EmployeeInformationExample;
import com.zzqx.mvc.service.EmployeeInformationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmployeeInformationServiceImpl implements EmployeeInformationService {

    @Autowired
    EmployeeInformationMapper employeeInformationMapper;

    @Override
    public int updateByWatchCode(EmployeeInformation employeeInformation) {
//        String status = employeeInformation.getWorkState();
        EmployeeInformationExample employeeInformationExample  = new EmployeeInformationExample();
        EmployeeInformationExample.Criteria criteria = employeeInformationExample.createCriteria();
        criteria.andWatchCodeEqualTo(employeeInformation.getWatchCode());
        EmployeeInformation em = new EmployeeInformation();
        em.setWorkState(employeeInformation.getWorkState());
        return employeeInformationMapper.updateByExampleSelective(em,employeeInformationExample);
    }

    @Override
    public List<EmployeeInformation> selectByWatchCode(EmployeeInformation employeeInformation) {
        EmployeeInformationExample employeeInformationExample  = new EmployeeInformationExample();
        EmployeeInformationExample.Criteria criteria = employeeInformationExample.createCriteria();
        criteria.andWatchCodeEqualTo(employeeInformation.getWatchCode());
        return employeeInformationMapper.selectByExample(employeeInformationExample);
    }

    @Override
    public List<EmployeeInformation> selectNoboding(EmployeeInformation employeeInformation) {

        return null;
    }
}
