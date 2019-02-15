package com.zzqx.support.framework.task.timerTask;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.zzqx.mvc.commons.CountInfo;
import com.zzqx.mvc.dao.HardwareMapper;
import com.zzqx.mvc.dao.TerminalMybatisMapper;
import com.zzqx.mvc.dto.HardwareDto;
import com.zzqx.mvc.entity.HardwareExample;
import com.zzqx.mvc.entity.TerminalMybatis;
import com.zzqx.mvc.entity.TerminalMybatisExample;
import com.zzqx.support.utils.machine.hardware.HardwareHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Transactional
public class TerminalTimeTask {

    @Autowired
    TerminalMybatisMapper terminalMybatisMapper;
    @Autowired
    HardwareMapper hardwareMapper;

    /**
     * 新增设备到监控
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
//                Map<String,Object> map = new HashMap<>();
//                map.put("hallId",countInfo.HALL_ID);
//                map.put("terminalName",terminalMybatis.getName());
//                map.put("ipAddress",terminalMybatis.getIp());
//                map.put("macAddress",terminalMybatis.getMac());
//                map.put("code",terminalMybatis.getCodeName());
//                map.put("alias",terminalMybatis.getSerialNumber());
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id",terminalMybatis.getId());
                jsonObject.put("hallId",terminalMybatis.getHallId());
                jsonObject.put("terminalName",terminalMybatis.getName());
                jsonObject.put("ipAddress",terminalMybatis.getIp());
                jsonObject.put("macAddress",terminalMybatis.getMac());
                jsonObject.put("code",terminalMybatis.getCodeName());
                jsonObject.put("alias",terminalMybatis.getSerialNumber());
                try {
//                    String s = HttpUtil.post(countInfo.DW_TERMINAL_ADD , map,2000);
                    String s = HttpUtil.createPost(countInfo.DW_TERMINAL_ADD).body(jsonObject.toString(),"application/json")
                            .timeout(2000).execute().body();
                    if (!"".equals(s)) {
                        JSONObject object = new JSONObject(s);
                        Object flag = object.get("infoCode");
                        if (flag.equals(200)) {
                            //更新上传状态
                            TerminalMybatis terminalMybatis1 = new TerminalMybatis();
                            terminalMybatis1.setId(terminalMybatis.getId());
                            terminalMybatis1.setUpdateStatus(1);
                            terminalMybatis1.setHallId(terminalMybatis.getHallId());
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
     * 更新设备到监控
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
//                Map<String,Object> map = new HashMap<>();
//                map.put("hallId",countInfo.HALL_ID);
//                map.put("terminalName",terminalMybatis.getName());
//                map.put("ipAddress",terminalMybatis.getIp());
//                map.put("macAddress",terminalMybatis.getMac());
//                map.put("code",terminalMybatis.getCodeName());
//                map.put("alias",terminalMybatis.getSerialNumber());
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id",terminalMybatis.getId());
                jsonObject.put("hallId",terminalMybatis.getHallId());
                jsonObject.put("terminalName",terminalMybatis.getName());
                jsonObject.put("ipAddress",terminalMybatis.getIp());
                jsonObject.put("macAddress",terminalMybatis.getMac());
                jsonObject.put("code",terminalMybatis.getCodeName());
                jsonObject.put("alias",terminalMybatis.getSerialNumber());
                try {
                    String s = HttpUtil.createPost(countInfo.DW_TERMINAL_UPDATE).body(jsonObject.toString(),"application/json")
                            .timeout(2000).execute().body();
                    if (!"".equals(s)) {
                        JSONObject object = new JSONObject(s);
                        Object flag = object.get("infoCode");
                        if (flag.equals(200)) {
                            //更新上传状态
                            TerminalMybatis terminalMybatis1 = new TerminalMybatis();
                            terminalMybatis1.setId(terminalMybatis.getId());
                            terminalMybatis1.setUpdateStatus(1);
                            terminalMybatis1.setHallId(terminalMybatis.getHallId());
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
    /**
     * 设备信息保存本地
     */
    public void doSaveHardware(){
        //获取开机状态的设备
        TerminalMybatisExample terminalMybatisExample = new TerminalMybatisExample();
        CountInfo countInfo =new CountInfo();
        TerminalMybatisExample.Criteria  criteria= terminalMybatisExample.createCriteria();
        criteria.andHallIdEqualTo(countInfo.HALL_ID).andStatusEqualTo("true");
        List<TerminalMybatis> list =terminalMybatisMapper.selectByExample(terminalMybatisExample);
//        Hardware hardware = null ;
        list.forEach(terminalMybatis -> {
            List<com.zzqx.support.utils.machine.hardware.Hardware> list_1 = HardwareHandler.getHardwareList(terminalMybatis.getMac());
            if(list_1.size() > 0){
                List<com.zzqx.mvc.entity.Hardware> hardwareList = new CopyOnWriteArrayList<>();
                list_1.forEach(hardware_1 -> {
                    com.zzqx.mvc.entity.Hardware hardware = new com.zzqx.mvc.entity.Hardware();
                    BeanUtils.copyProperties(hardware_1,hardware);
                    hardware.setMac(terminalMybatis.getMac());
                    hardware.setCreateTime(new Date());
                    hardware.setHallId(countInfo.HALL_ID);
                    hardwareList.add(hardware);
                });
                //保存数据到本地数据库
                hardwareMapper.batchInsert(hardwareList);
            }
        });
    }
    /**
     * 设备信息同步到监控
     */
    public void terminalSave2Control(){
        CountInfo countInfo = new CountInfo();
        //获取为上传的数据
        HardwareExample hardwareExample = new HardwareExample();
        HardwareExample.Criteria criteria = hardwareExample.createCriteria();
        criteria.andUpdateStatusEqualTo(0);
        List<com.zzqx.mvc.entity.Hardware> hardwareList = hardwareMapper.selectByExample(hardwareExample);
        List<HardwareDto> hardwareDtos = new CopyOnWriteArrayList<>();
        hardwareList.forEach(hardware -> {
            HardwareDto hardwareDto = new HardwareDto();
            try {
                org.apache.commons.beanutils.BeanUtils.copyProperties(hardwareDto,hardware);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            hardwareDto.setCreateTime(hardware.getCreateTime().getTime());
            hardwareDtos.add(hardwareDto);
        });
        //转hardwareDtos2Json
        JSONArray jsonArray = new JSONArray(hardwareDtos);
        System.out.println("------------------"+jsonArray);
        String json = jsonArray.toString();
        String s =  HttpUtil.post(countInfo.DW_TERMINAL_SAVE2CENTROL_TEST,json);
    }
}
