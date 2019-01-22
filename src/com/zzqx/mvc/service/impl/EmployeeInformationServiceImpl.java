package com.zzqx.mvc.service.impl;

import com.zzqx.mvc.commons.CountInfo;
import com.zzqx.mvc.dao.EmployeeInformationMapper;
import com.zzqx.mvc.dto.EmployeeInformationDto;
import com.zzqx.mvc.entity.EmployeeInformation;
import com.zzqx.mvc.entity.EmployeeInformationExample;
import com.zzqx.mvc.service.EmployeeInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class EmployeeInformationServiceImpl implements EmployeeInformationService {

    @Autowired
    EmployeeInformationMapper employeeInformationMapper;

    @Override
    public int updateByWatchCode(EmployeeInformation employeeInformation) {
//        String status = employeeInformation.getWorkState();
        EmployeeInformationExample employeeInformationExample  = new EmployeeInformationExample();
        EmployeeInformationExample.Criteria criteria = employeeInformationExample.createCriteria();
        criteria.andWatchCodeEqualTo(employeeInformation.getWatchCode());
        return employeeInformationMapper.updateByExampleSelective(employeeInformation,employeeInformationExample);
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
        EmployeeInformationExample employeeInformationExample  = new EmployeeInformationExample();
        EmployeeInformationExample.Criteria criteria = employeeInformationExample.createCriteria();
        criteria.andHallIdEqualTo(employeeInformation.getHallId()).andBindStateEqualTo(employeeInformation.getBindState());
        return employeeInformationMapper.selectByExample(employeeInformationExample);
    }

    @Override
    public int updateById(EmployeeInformation employeeInformation) {
        EmployeeInformationExample employeeInformationExample  = new EmployeeInformationExample();
        EmployeeInformationExample.Criteria criteria = employeeInformationExample.createCriteria();
        BigDecimal bd = new BigDecimal(employeeInformation.getId());
        CountInfo countInfo = new CountInfo();
        criteria.andHallIdEqualTo(countInfo.HALL_ID).andIdEqualTo(bd);
//        EmployeeInformation em = new EmployeeInformation();
//        em.setBindState((short)0);
//        em.setWatchCode(employeeInformation.getWatchCode());
        return employeeInformationMapper.updateByExampleSelective(employeeInformation,employeeInformationExample);
    }

    @Override
    public List<EmployeeInformationDto> selectAll(EmployeeInformation employeeInformation) {
        EmployeeInformationExample employeeInformationExample  = new EmployeeInformationExample();
        EmployeeInformationExample.Criteria criteria = employeeInformationExample.createCriteria();
        criteria.andBindStateEqualTo(employeeInformation.getBindState());
        return employeeInformationMapper.selectAll(employeeInformationExample);
    }

    @Override
    public EmployeeInformation getById(String id) {
        return employeeInformationMapper.selectByPrimaryKey(BigDecimal.valueOf(Long.valueOf(id)));
    }

    @Override
    public EmployeeInformation selectByMyWork(String myWork) {
        EmployeeInformationExample employeeInformationExample  = new EmployeeInformationExample();
        EmployeeInformationExample.Criteria criteria = employeeInformationExample.createCriteria();
        criteria.andMyWorkEqualTo(myWork);
        List<EmployeeInformation> employeeInformationList = employeeInformationMapper.selectByExample(employeeInformationExample);
        EmployeeInformation employeeInformation = null;
        if (employeeInformationList.size() > 0){
            employeeInformation = employeeInformationList.get(0);
        }
        return employeeInformation;
    }
}
