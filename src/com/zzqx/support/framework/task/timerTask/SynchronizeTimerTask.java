package com.zzqx.support.framework.task.timerTask;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zzqx.mvc.commons.CountInfo;
import com.zzqx.mvc.dao.BhSchduMapper;
import com.zzqx.mvc.dao.EmployeeInformationMapper;
import com.zzqx.mvc.entity.*;
import com.zzqx.mvc.service.MessageService;
import com.zzqx.support.framework.mina.androidser.AndroidConstant;
import com.zzqx.support.utils.net.SocketDataSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/11/23 0023.
 */
@Transactional
public class SynchronizeTimerTask {
    @Autowired
    BhSchduMapper bhSchduMapper;
    @Autowired
    EmployeeInformationMapper employeeInformationMapper;
    @Autowired
    private MessageService messageService;
    private String getData = "";
    /**
     * 每分钟获取排班变动
     */
    public void doMessageTask() {
        getData = HttpUtil.get(CountInfo.GET_MESSAGE_SCHEDU);
        JSONObject upDataJson = JSONUtil.parseObj(getData);
        JSONArray schJsonArray = JSONUtil.parseArray(upDataJson.get("update"));
//        List<BhSchdu> schduList = new ArrayList<>();
        if(schJsonArray != null && schJsonArray.size()>0){
            for (int i = 0;i <schJsonArray.size() ; i++){
                Object schObj = schJsonArray.get(i);
                BhSchdu schdu = JSONUtil.toBean(schObj.toString(),BhSchdu.class);
                bhSchduMapper.updateByPrimaryKey(schdu);
                //发送消息到腕表
//                if(schdu.getCreateTime().getDate() == new Date().getDate()){
//                    Message msg = new Message();
//                    msg.setContent("请" + person.getName() + "到" + wPosition);
//                    msg.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//                    msg.setCreator("admin");
//                    msg.setStatu(AndroidConstant.MESSAGE_STATE_UNREAD_KEY);
//                    msg.setTitle("调度信息");
//                    msg.setType(AndroidConstant.MESSAGE_TYPE_CALLMONITOR_KEY);
//                    msg.setWatch_code(person.getWatch_code());
//                    msg.setOrdertime(new Date());
//                    messageService.saveOrUpdate(msg);
//                    SocketDataSender.sendWatchMsg(AndroidConstant.MESSAGE_TYPE_CALLMONITOR_KEY, person.getWatch_code(), person);
//                }
            }
        }
    }

    /**
     * 每天获取排版和人员信息同步
     */
    public void doSynchronizeTask(){
        getData = HttpUtil.get(CountInfo.GET_SYNC_DAYA);
        JSONObject dataJson = JSONUtil.parseObj(getData);
        JSONArray schJsonArray = JSONUtil.parseArray(dataJson.get("ScheduList"));
        JSONArray empJsonArray = JSONUtil.parseArray(dataJson.get("employeeInformationList"));

        List<BhSchdu> schduList = JSONUtil.toList(schJsonArray,BhSchdu.class );
        List<EmployeeInformation> employeeInformationList = JSONUtil.toList(empJsonArray,EmployeeInformation.class );

        if(schduList != null && schduList.size()>0 && employeeInformationList != null && employeeInformationList.size()>0){
            bhSchduMapper.batchInsertBhSchdu(schduList);
//            employeeInformationMapper
        }
  
    }
}
