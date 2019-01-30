package com.zzqx.support.framework.task.timerTask;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import com.zzqx.mvc.commons.CountInfo;
import com.zzqx.mvc.dao.TerminalMybatisMapper;
import com.zzqx.mvc.entity.TerminalMybatis;
import com.zzqx.mvc.entity.TerminalMybatisExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class TerminalTimeTask {

    @Autowired
    TerminalMybatisMapper terminalMybatisMapper;

    /**
     * 新增
     */
    public void addDwTerminal(){
        //查询未上传的数据
        TerminalMybatisExample terminalMybatisExample =  new TerminalMybatisExample();
        TerminalMybatisExample.Criteria criteria  =terminalMybatisExample.createCriteria();
        criteria.andUpdateStatusEqualTo(0);
        List<TerminalMybatis> list =   terminalMybatisMapper.selectByExample(terminalMybatisExample);
        //字段匹配上传
        CountInfo countInfo = new CountInfo();
        if (list.size() > 0) {
            list.forEach(terminalMybatis -> {
                try {
                    String s = HttpUtil.get(countInfo.DW_TERMINAL_ADD + "?hallId=" + countInfo.HALL_ID
                            + "&terminalName=" + terminalMybatis.getName()
                            + "&ipAddress=" + terminalMybatis.getIp()
                            + "&macAddress=" + terminalMybatis.getMac()
                            + "&code=" + terminalMybatis.getCodeName()
                            + "&alias=" + terminalMybatis.getSerialNumber()
                    ,2000);
                    JSONObject object = new JSONObject(s);
                    Object flag = object.get("infoCode");
                    if (flag.equals(200)){
                        //更新上传状态
                        TerminalMybatis terminalMybatis1 = new TerminalMybatis();
                        terminalMybatis1.setId(terminalMybatis.getId());
                        terminalMybatis1.setUpdateStatus(1);
                        terminalMybatisMapper.updateByPrimaryKeySelective(terminalMybatis1);
                    }
                }catch (Exception e){
                    System.out.println("-------------------"+ e);
                }
            });
        }
    }
    /**
     * 更新
     */
    public void updateDwTerminal(){
        //查询已更新的数据
        TerminalMybatisExample terminalMybatisExample =  new TerminalMybatisExample();
        TerminalMybatisExample.Criteria criteria  =terminalMybatisExample.createCriteria();
        criteria.andUpdateStatusEqualTo(2);
        List<TerminalMybatis> list =   terminalMybatisMapper.selectByExample(terminalMybatisExample);
        //字段匹配上传
        CountInfo countInfo = new CountInfo();
        if (list.size() > 0) {
            list.forEach(terminalMybatis -> {
                try {
                    String s = HttpUtil.get(countInfo.DW_TERMINAL_UPDATE + "?hallId=" + countInfo.HALL_ID
                            + "&terminalName=" + terminalMybatis.getName()
                            + "&ipAddress=" + terminalMybatis.getIp()
                            + "&macAddress=" + terminalMybatis.getMac()
                            + "&code=" + terminalMybatis.getCodeName()
                            + "&alias=" + terminalMybatis.getSerialNumber()
                    ,2000);
                    JSONObject object = new JSONObject(s);
                    Object flag = object.get("infoCode");
                    if (flag.equals(200)){
                        //更新上传状态
                        TerminalMybatis terminalMybatis1 = new TerminalMybatis();
                        terminalMybatis1.setId(terminalMybatis.getId());
                        terminalMybatis1.setUpdateStatus(1);
                        terminalMybatisMapper.updateByPrimaryKeySelective(terminalMybatis1);
                    }
                }catch (Exception e){
                    System.out.println("-------------------"+ e);
                }
            });
        }
    }
    /**
     * 删除
     */
    public void delDwTerminal(){

    }
}
