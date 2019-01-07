package com.zzqx.mvc.service.impl;


import com.zzqx.mvc.dao.CmdListMapper;
import com.zzqx.mvc.dao.CmdMapper;
import com.zzqx.mvc.dto.CmdListDto;

import com.zzqx.mvc.entity.*;
import com.zzqx.mvc.service.*;
import com.zzqx.mvc.vo.CmdListOneVo;
import com.zzqx.mvc.vo.CmdListVo;


import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.lang.reflect.InvocationTargetException;


import java.util.List;


@Service
@Transactional
public class CmdListServiceImpl implements CmdListService {
    @Autowired
    CmdListMapper cmdListMapper;
    @Autowired
    CmdMapper cmdMapper;
    @Autowired
    CmdService cmdService;



    @Override
    public List<CmdList> allList(String directName) {
        CmdListExample cmdListExample = new CmdListExample();
        if (  !"".equals(directName)) {
            CmdListExample.Criteria criteria = cmdListExample.createCriteria();
            criteria.andDirectListNameEqualTo(directName);
        }
        return cmdListMapper.selectByExample(cmdListExample);
    }

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
        CmdListVo cmdListVo = cmdService.getAllDataByType();
        try {
            BeanUtils.copyProperties(cmdListVo,cmdList);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //检测是否有toTCPServer字符
//        String s = cmdList.getDirectList();
//        boolean flag = StringUtils.contains(s,"toTCPServer=");
//        if (flag){
//            String data = s.substring(22);
//            System.out.print(data);
//            String ip = data.split(",")[0];
//            String port = data.split(",")[1];
//            cmdListVo.setIp(ip);
//            cmdListVo.setPort(port);
//        }
        return cmdListVo;
    }

    @Override
    public List<CmdListOneVo> getListExcludeDirectList() {
        return cmdListMapper.getListExcludeDirectList();
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
        cmdList = cmdAdd(cmdList);
        return cmdListMapper.insertSelective(cmdList);
    }

    @Override
    public int updateSelect(CmdList cmdList) {
        cmdList = cmdAdd(cmdList);
        return cmdListMapper.updateByPrimaryKeySelective(cmdList);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return cmdListMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteByIds(String ids) {

        return 0;
    }

    /**
     * 指令拼接
     */
    public CmdList cmdAdd(CmdList cmdList){
        //tcp指令拼接
//        Stream.of(cmdList.getTcpSource()).forEach(cmd->{
//
//        });
        String s = "";
        String[] strings = cmdList.getTcpSource().split(",");
        for (String item:strings){
            s += cmdList.getTcpIp()+","+cmdList.getTcpPort()+","+ item+";";
        }
        String source = s.substring(0,s.length()-1);
//        source = "toTCPServer="+source;
        cmdList.setDirectList(source);
        //todo 其他指令的拼接
        return cmdList;
    }
}
