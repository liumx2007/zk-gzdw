package com.zzqx.mvc.service.impl;

import com.zzqx.mvc.commons.CountInfo;
import com.zzqx.mvc.dao.HardwareMapper;
import com.zzqx.mvc.dao.TerminalMybatisMapper;
import com.zzqx.mvc.entity.Hardware;
import com.zzqx.mvc.entity.TerminalMybatis;
import com.zzqx.mvc.entity.TerminalMybatisExample;
import com.zzqx.mvc.service.HardwareService;
import com.zzqx.support.utils.machine.hardware.HardwareHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HardwareServiceImpl implements HardwareService {
    @Autowired
    HardwareMapper hardwareMapper;
    @Autowired
    TerminalMybatisMapper terminalMybatisMapper;

    @Override
    public int batchInsert(List<Hardware> list) {
        //查询已经开启的设备
        TerminalMybatisExample terminalMybatisExample = new TerminalMybatisExample();
        CountInfo countInfo =new CountInfo();
        TerminalMybatisExample.Criteria  criteria= terminalMybatisExample.createCriteria();
        criteria.andHallIdEqualTo(countInfo.HALL_ID).andStatusEqualTo("true");
        List<TerminalMybatis> terminalMybatisList =terminalMybatisMapper.selectByExample(terminalMybatisExample);
        terminalMybatisList.forEach(terminalMybatis -> {
            List<com.zzqx.support.utils.machine.hardware.Hardware> hardwareList = HardwareHandler.getHardwareList(terminalMybatis.getMac());
            hardwareList.forEach(hardware -> {

            });
        });
        return 0;
    }
}
