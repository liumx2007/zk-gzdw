package com.zzqx.support.framework.task.timerTask;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zzqx.mvc.commons.CountInfo;
import com.zzqx.mvc.dao.BhSchduMapper;
import com.zzqx.mvc.dao.EmployeeInformationMapper;
import com.zzqx.mvc.entity.BhSchdu;
import com.zzqx.mvc.entity.EmployeeInformation;
import com.zzqx.mvc.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
        //todo getData 非空判断
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
//        BhSchdu bhSchdu = new BhSchdu();
//        EmployeeInformation employeeInformation = new EmployeeInformation();
//        getData = HttpUtil.get(CountInfo.GET_SYNC_DAYA);
        String getData = "{\n" +
                "\t\"ScheduList\": [],\n" +
                "\t\"employeeInformationList\": [{\n" +
                "\t\t\"averageProcessTime\": \"24min\",\n" +
                "\t\t\"bindState\": \"1\",\n" +
                "\t\t\"businessToday\": \"200\",\n" +
                "\t\t\"createBy\": \"1\",\n" +
                "\t\t\"createTime\": \"2018-07-17\",\n" +
                "\t\t\"delFlag\": \"0\",\n" +
                "\t\t\"employeeAccount\": \"a005\",\n" +
                "\t\t\"employeeAge\": \"27\",\n" +
                "\t\t\"employeeId\": \"SF0008\",\n" +
                "\t\t\"employeeSex\": \"1\",\n" +
                "\t\t\"employeeState\": \"1\",\n" +
                "\t\t\"hallId\": 1,\n" +
                "\t\t\"id\": 2,\n" +
                "\t\t\"jobs\": \"5,2\",\n" +
                "\t\t\"loginName\": \"3333\",\n" +
                "\t\t\"myWork\": \"岗位名称\",\n" +
                "\t\t\"name\": \"张颖彤\",\n" +
                "\t\t\"password\": \"333\",\n" +
                "\t\t\"phone\": \"1325556565\",\n" +
                "\t\t\"updateBy\": \"1\",\n" +
                "\t\t\"updateTime\": \"2018-08-21\",\n" +
                "\t\t\"workState\": \"1\"\n" +
                "\t}]\n" +
                "}";
        JSONObject dataJson = JSONUtil.parseObj(getData);
        Object bhSchduObject = dataJson.get("ScheduList");
        Object employeeInformationObject = dataJson.get("employeeInformationList");
        JSONArray schJsonArray = JSONUtil.parseArray(dataJson.get("ScheduList"));
        JSONArray empJsonArray = JSONUtil.parseArray(dataJson.get("employeeInformationList"));

        List<BhSchdu> schduList = JSONUtil.toList(schJsonArray,BhSchdu.class );
        List<EmployeeInformation> employeeInformationList = JSONUtil.toList(empJsonArray,EmployeeInformation.class );

        if(schduList !=null && schduList.size()>0){
            bhSchduMapper.batchInsertBhSchdu(schduList);
        }
        if(employeeInformationList != null && employeeInformationList.size()>0){
            employeeInformationMapper.batchInsert(employeeInformationList);
        }
  
    }
}
