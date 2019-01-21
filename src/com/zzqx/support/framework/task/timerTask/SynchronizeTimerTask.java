package com.zzqx.support.framework.task.timerTask;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zzqx.mvc.commons.CountInfo;
import com.zzqx.mvc.dao.BhSchduMapper;
import com.zzqx.mvc.dao.EmployeeInformationMapper;
import com.zzqx.mvc.dao.EmployeeJobsMapper;
import com.zzqx.mvc.entity.*;
import com.zzqx.mvc.service.MessageService;
import com.zzqx.support.framework.mina.androidser.AndroidConstant;
import com.zzqx.support.utils.net.SocketDataSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
    EmployeeJobsMapper employeeJobsMapper;

    @Autowired
    private MessageService messageService;
    private String getData = "";
    /**
     * 每分钟获取排班变动
     */
    public void doMessageTask() {
        CountInfo countInfo = new CountInfo();
        getData = HttpUtil.get(countInfo.GET_MESSAGE_SCHEDU);
        //todo getData 非空判断
        JSONObject upDataJson = JSONUtil.parseObj(getData);
        JSONArray schJsonArray = JSONUtil.parseArray(upDataJson.get("update"));
//        List<BhSchdu> schduList = JSONUtil.toList(schJsonArray,BhSchdu.class );
        if(schJsonArray != null && schJsonArray.size()>0){
            for (int i = 0;i <schJsonArray.size() ; i++){
                Object schObj = schJsonArray.get(i);
                BhSchdu schdu = JSONUtil.toBean(schObj.toString(),BhSchdu.class);
                bhSchduMapper.updateByPrimaryKey(schdu);
                //发送消息到腕表
                if(schdu.getCreateTime().getDate() == new Date().getDate()){
                    Message msg = new Message();
                    BigDecimal empId = BigDecimal.valueOf(schdu.getEmployeeId());
                    EmployeeJobs employeeJobs = employeeJobsMapper.selectById(schdu.getJobsId());
                    EmployeeInformation employeeInformation = employeeInformationMapper.selectByPrimaryKey(empId);
                    msg.setContent("请" + employeeInformation.getName() + "到" + employeeJobs.getJobsName());
                    msg.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    msg.setCreator("admin");
                    msg.setStatu(AndroidConstant.MESSAGE_STATE_UNREAD_KEY);
                    msg.setTitle("调度信息");
                    msg.setType(AndroidConstant.MESSAGE_TYPE_CALLMONITOR_KEY);
                    msg.setWatch_code(employeeInformation.getWatchCode());
                    msg.setOrdertime(new Date());
                    messageService.saveOrUpdate(msg);
                    Personnel person = new Personnel();
                    person.setName(employeeInformation.getName());
                    person.setWatch_code(employeeInformation.getWatchCode());
                    SocketDataSender.sendWatchMsg(AndroidConstant.MESSAGE_TYPE_CALLMONITOR_KEY, employeeInformation.getWatchCode(), person);
                    //更新本地排班数据
                    bhSchduMapper.updateByPrimaryKey(schdu);
                }
            }
        }
    }

    /**
     * 每天获取排版和人员信息同步
     */
    @Transactional
    public void doSynchronizeTask(){
//        String getData = "{\n" +
//                "\t\"ScheduList\": [],\n" +
//                "\t\"employeeInformationList\": [{\n" +
//                "\t\t\"averageProcessTime\": \"24min\",\n" +
//                "\t\t\"bindState\": \"1\",\n" +
//                "\t\t\"businessToday\": \"200\",\n" +
//                "\t\t\"createBy\": \"1\",\n" +
//                "\t\t\"createTime\": \"2018-07-17\",\n" +
//                "\t\t\"delFlag\": \"0\",\n" +
//                "\t\t\"employeeAccount\": \"a005\",\n" +
//                "\t\t\"employeeAge\": \"27\",\n" +
//                "\t\t\"employeeId\": \"SF0008\",\n" +
//                "\t\t\"employeeSex\": \"1\",\n" +
//                "\t\t\"employeeState\": \"1\",\n" +
//                "\t\t\"hallId\": 1,\n" +
//                "\t\t\"id\": 2,\n" +
//                "\t\t\"jobs\": \"5,2\",\n" +
//                "\t\t\"loginName\": \"3333\",\n" +
//                "\t\t\"myWork\": \"岗位名称\",\n" +
//                "\t\t\"name\": \"张颖彤\",\n" +
//                "\t\t\"password\": \"333\",\n" +
//                "\t\t\"phone\": \"1325556565\",\n" +
//                "\t\t\"updateBy\": \"1\",\n" +
//                "\t\t\"updateTime\": \"2018-08-21\",\n" +
//                "\t\t\"workState\": \"1\"\n" +
//                "\t}]\n" +
//                "}";
        CountInfo countInfo = new CountInfo();
        getData = HttpUtil.get(countInfo.GET_SYNC_DAYA);
        JSONObject dataJson = JSONUtil.parseObj(getData);
        Object bhSchduObject = dataJson.get("ScheduList");
        Object employeeInformationObject = dataJson.get("employeeInformationList");
        JSONArray schJsonArray = JSONUtil.parseArray(bhSchduObject);
        JSONArray empJsonArray = JSONUtil.parseArray(employeeInformationObject);

        List<BhSchdu> schduList = JSONUtil.toList(schJsonArray,BhSchdu.class );
        List<EmployeeInformation> employeeInformationList = JSONUtil.toList(empJsonArray,EmployeeInformation.class );

        if(schduList !=null && schduList.size()>0){
            bhSchduMapper.deleteByExample(null);
            bhSchduMapper.batchInsertBhSchdu(schduList);
        }
        if(employeeInformationList != null && employeeInformationList.size()>0){
            employeeInformationMapper.delOrBaoAn();
            employeeInformationMapper.batchInsert(employeeInformationList);
        }

    }
}
