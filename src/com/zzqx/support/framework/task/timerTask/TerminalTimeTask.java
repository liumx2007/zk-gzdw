package com.zzqx.support.framework.task.timerTask;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import com.zzqx.mvc.commons.CountInfo;
import com.zzqx.mvc.dao.TerminalMybatisMapper;
import com.zzqx.mvc.entity.TerminalMybatis;
import com.zzqx.mvc.entity.TerminalMybatisExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<TerminalMybatis> list =  terminalMybatisMapper.selectByExample(terminalMybatisExample);
        //字段匹配上传
        CountInfo countInfo = new CountInfo();
        if (list.size() > 0) {
            list.forEach(terminalMybatis -> {
//            TerminalMybatis terminalMybatis = list.get(0);
                Map<String,Object> map = new HashMap<>();
                map.put("hallId",countInfo.HALL_ID);
                map.put("terminalName",terminalMybatis.getName());
                map.put("ipAddress",terminalMybatis.getIp());
                map.put("macAddress",terminalMybatis.getMac());
                map.put("code",terminalMybatis.getCodeName());
                map.put("alias",terminalMybatis.getSerialNumber());
                try {
                    String s = HttpUtil.post(countInfo.DW_TERMINAL_ADD , map,2000);
                    if (!"".equals(s)) {
                        JSONObject object = new JSONObject(s);
                        Object flag = object.get("infoCode");
                        if (flag.equals(200)) {
                            //更新上传状态
                            TerminalMybatis terminalMybatis1 = new TerminalMybatis();
                            terminalMybatis1.setId(terminalMybatis.getId());
                            terminalMybatis1.setUpdateStatus(1);
                            terminalMybatisMapper.updateByPrimaryKeySelective(terminalMybatis1);
                        }
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
//            TerminalMybatis terminalMybatis = list.get(0);
                Map<String,Object> map = new HashMap<>();
                map.put("hallId",countInfo.HALL_ID);
                map.put("terminalName",terminalMybatis.getName());
                map.put("ipAddress",terminalMybatis.getIp());
                map.put("macAddress",terminalMybatis.getMac());
                map.put("code",terminalMybatis.getCodeName());
                map.put("alias",terminalMybatis.getSerialNumber());
                try {
                    String s = HttpUtil.post(countInfo.DW_TERMINAL_UPDATE ,map,2000);
                    if (!"".equals(s)) {
                        JSONObject object = new JSONObject(s);
                        Object flag = object.get("infoCode");
                        if (flag.equals(200)) {
                            //更新上传状态
                            TerminalMybatis terminalMybatis1 = new TerminalMybatis();
                            terminalMybatis1.setId(terminalMybatis.getId());
                            terminalMybatis1.setUpdateStatus(1);
                            terminalMybatisMapper.updateByPrimaryKeySelective(terminalMybatis1);
                        }
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
