package com.zzqx.mvc.service.impl;

import com.zzqx.mvc.dao.CmdMapper;
import com.zzqx.mvc.entity.Cmd;
import com.zzqx.mvc.entity.CmdExample;
import com.zzqx.mvc.service.CmdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CmdServiceImpl implements CmdService {

    @Autowired
    CmdMapper cmdMapper;

    @Override
    public List<Cmd> getList(Cmd cmd) {
        CmdExample cmdExample = new CmdExample();
        List<Cmd> list = cmdMapper.selectByExample(cmdExample);
        return list;
    }
}
