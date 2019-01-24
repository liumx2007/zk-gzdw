package com.zzqx.mvc.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.zzqx.mvc.commons.CountInfo;
import com.zzqx.mvc.dao.CmdListMapper;
import com.zzqx.mvc.dao.CmdMapper;
import com.zzqx.mvc.dto.CmdListDto;
import com.zzqx.mvc.entity.CmdList;
import com.zzqx.mvc.entity.CmdListExample;
import com.zzqx.mvc.service.CmdListService;
import com.zzqx.mvc.service.CmdService;
import com.zzqx.mvc.vo.CmdListOneVo;
import com.zzqx.mvc.vo.CmdListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
        CmdListExample.Criteria criteria = cmdListExample.createCriteria();
        if (  !"".equals(directName)) {
            criteria.andDirectListNameEqualTo(directName);
        }
        //只查本营业厅的指令数据
        CountInfo countInfo = new CountInfo();
        criteria.andHallIdEqualTo(countInfo.HALL_ID);
        return cmdListMapper.selectByExample(cmdListExample);
    }

    @Override
    public List<CmdList> getList(CmdListDto cmdListDto) {
        int pageNo = cmdListDto.getPage() == 0?1:cmdListDto.getPage();
        int pageSize =  cmdListDto.getRows() == 0?10:cmdListDto.getRows();
        int  limit0 = (pageNo-1)*pageSize;
        cmdListDto.setLimit0(limit0);
        cmdListDto.setLimit1(pageSize);
        //只查询本营业厅数据
        CountInfo countInfo = new CountInfo();
        cmdListDto.setHallId(countInfo.HALL_ID);
        return cmdListMapper.getList(cmdListDto);
    }

    @Override
    public List<CmdList> getListCount(CmdListDto cmdListDto) {
        CountInfo countInfo = new CountInfo();
        cmdListDto.setHallId(countInfo.HALL_ID);
        return cmdListMapper.getListCount(cmdListDto);
    }

    @Override
    public CmdListVo getById(String id) {
        CmdList cmdList = cmdListMapper.selectByPrimaryKey(id);
        CmdListVo cmdListVo = cmdService.getAllDataByType();
//        try {
//            BeanUtils.copyProperties(cmdListVo,cmdList);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
        BeanUtil.copyProperties(cmdList,cmdListVo);
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
    public CmdList getByIdOne(String id) {
        CmdList cmdList = cmdListMapper.selectByPrimaryKey(id);
        return cmdList;
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
        CountInfo countInfo = new CountInfo();
        cmdList.setHallId(countInfo.HALL_ID);
        cmdList.setCreateTime(new Date());
        cmdList.setUpdateTime(new Date());
        cmdList = cmdAdd(cmdList);
        return cmdListMapper.insertSelective(cmdList);
    }

    @Override
    public int updateSelect(CmdList cmdList) {
        CountInfo countInfo = new CountInfo();
        cmdList.setHallId(countInfo.HALL_ID);
        cmdList.setUpdateTime(new Date());
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
