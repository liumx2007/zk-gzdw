package com.zzqx.mvc.service.impl;

import com.zzqx.mvc.dao.DelTerminalMapper;
import com.zzqx.mvc.entity.DelTerminal;
import com.zzqx.mvc.entity.DelTerminalExample;
import com.zzqx.mvc.service.DelterminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DelTerminalServiceImpl implements DelterminalService {

    @Autowired
    DelTerminalMapper delTerminalMapper;

    @Override
    public int save(DelTerminal delTerminal) {

        return delTerminalMapper.insertSelective(delTerminal);
    }

    @Override
    public List<DelTerminal> selectList(DelTerminal delTerminal) {
        DelTerminalExample delTerminalExample = new DelTerminalExample();
        DelTerminalExample.Criteria criteria = delTerminalExample.createCriteria();
        criteria.andTagEqualTo(0);
        return delTerminalMapper.selectByExample(delTerminalExample);
    }
}
