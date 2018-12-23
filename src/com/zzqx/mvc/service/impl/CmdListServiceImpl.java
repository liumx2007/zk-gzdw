package com.zzqx.mvc.service.impl;

import com.zzqx.mvc.dao.CmdListMapper;
import com.zzqx.mvc.dto.CmdListDto;
import com.zzqx.mvc.entity.CmdList;
import com.zzqx.mvc.service.CmdListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CmdListServiceImpl implements CmdListService {
    @Autowired
    CmdListMapper cmdListMapper;
    @Override
    public List<CmdList> getList(CmdListDto cmdListDto) {
        int pageNo = cmdListDto.getLimit0() == 0?1:cmdListDto.getLimit0();
        int pageSize =  cmdListDto.getLimit1() == 0?10:cmdListDto.getLimit1();
        int  limit0 = (pageNo-1)*pageSize;
        cmdListDto.setLimit0(limit0);
        cmdListDto.setLimit1(pageSize);
        return cmdListMapper.getList(cmdListDto);
    }

    @Override
    public List<CmdList> getListCount(CmdListDto cmdListDto) {
        return cmdListMapper.getListCount(cmdListDto);
    }

    @Override
    public int insertSelect(CmdList cmdList) {
        return cmdListMapper.insertSelective(cmdList);
    }

    @Override
    public int updateSelect(CmdList cmdList) {
        return cmdListMapper.updateByPrimaryKeySelective(cmdList);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return cmdListMapper.deleteByPrimaryKey(id);
    }
}
