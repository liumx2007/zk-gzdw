package com.zzqx.mvc.service.impl;

import com.zzqx.mvc.dao.CmdListMapper;
import com.zzqx.mvc.dao.CmdMapper;
import com.zzqx.mvc.dto.CmdListDto;
import com.zzqx.mvc.entity.Cmd;
import com.zzqx.mvc.entity.CmdExample;
import com.zzqx.mvc.entity.CmdList;
import com.zzqx.mvc.service.CmdListService;
import com.zzqx.mvc.vo.CmdListVo;
import com.zzqx.support.utils.StringHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@Transactional
public class CmdListServiceImpl implements CmdListService {
    @Autowired
    CmdListMapper cmdListMapper;
    @Autowired
    CmdMapper cmdMapper;

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
    public CmdListVo getById(String id) {
        CmdList cmdList = cmdListMapper.selectByPrimaryKey(id);
        List<String> list2 = new ArrayList<>();
        String[] strings = cmdList.getDirectList().split(",");
        for (String item: strings){
            list2.add(item);
        }
        CmdListVo cmdListVo = new CmdListVo();
        try {
            BeanUtils.copyProperties(cmdListVo,cmdList);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        CmdExample cmdExample =new CmdExample();
        List<Cmd> cmds = cmdMapper.selectByExample(cmdExample);
        cmdListVo.setCmdList(cmds);
        //没有设置的指令集合
        List<String>  list1 = new ArrayList<>();
        cmds.forEach(data-> {
            list1.add(data.getDirect());
        });
        list1.removeAll(list2);
        System.out.print(list1);
//        cmdListVo.setDirectTest(list1);
        return cmdListVo;
    }

//    @Override
//    public CmdListVo getCmdVoById(String id) {
//       CmdList cmdList =  cmdListMapper.selectByPrimaryKey(id);
//       String[] stings = cmdList.getDirectList().split(",");
//       for (String  item:stings){
//
//       }
//        return null;
//    }

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
