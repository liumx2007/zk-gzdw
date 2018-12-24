package com.zzqx.mvc.service.impl;

import com.zzqx.mvc.commons.CmdInfo;
import com.zzqx.mvc.dao.CmdMapper;
import com.zzqx.mvc.entity.Cmd;
import com.zzqx.mvc.entity.CmdExample;
import com.zzqx.mvc.service.CmdService;
import com.zzqx.mvc.vo.CmdListVo;
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

    @Override
    public List<Cmd> getListByType(String type) {
        CmdExample cmdExample =new CmdExample();
        CmdExample.Criteria criteria = cmdExample.createCriteria();
        criteria.andTypeEqualTo(type);
        return cmdMapper.selectByExample(cmdExample);
    }

    @Override
    public CmdListVo getAllDataByType() {
        CmdListVo cmdListVo = new CmdListVo();
        //tcp指令集合
        List<Cmd> cmds = getListByType(CmdInfo.TCP);
        cmdListVo.setCmdList(cmds);
        //client指令集合
        List<Cmd> cmds1 = getListByType(CmdInfo.CLIENT);
        cmdListVo.setClientList(cmds1);
        return cmdListVo;
    }
}
