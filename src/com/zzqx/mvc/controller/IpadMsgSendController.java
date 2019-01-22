package com.zzqx.mvc.controller;

import com.zzqx.mvc.annotation.OpenAccess;
import com.zzqx.mvc.commons.CountInfo;
import com.zzqx.mvc.dao.EmployeeInformationMapper;
import com.zzqx.mvc.entity.EmployeeInformation;
import com.zzqx.mvc.javabean.R;
import com.zzqx.mvc.service.EmployeeInformationService;
import com.zzqx.mvc.service.MessageService;
import com.zzqx.mvc.service.PersonnelService;
import com.zzqx.mvc.vo.MsgVo;
import com.zzqx.support.framework.mina.androidser.AndroidConstant;
import com.zzqx.support.framework.mina.androidser.AndroidMinaSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@OpenAccess
@Controller
@RequestMapping("msgSend")
public class IpadMsgSendController {

    @Autowired
    PersonnelService personnelService;
    @Autowired
    MessageService messageService;
    @Autowired
    EmployeeInformationService employeeInformationService;
    @Autowired
    EmployeeInformationMapper employeeInformationMapper;

    @RequestMapping("personMsg")
    @ResponseBody
    public R personMsg(MsgVo msgVo){
        //查询班长的watchCode,保安的watchCode
        EmployeeInformation employeeInformation = null;
        AndroidMinaSession androidMinaSession = new AndroidMinaSession();
        //班长、值长
        if (msgVo.getMsgType().equals(AndroidConstant.MESSAGE_TYPE_CALLMONITOR_KEY) || msgVo.getMsgType().equals(AndroidConstant.MESSAGE_TYPE_SHIFT_KEY)){
            employeeInformation = employeeInformationService.selectByMyWork(CountInfo.BANZHANG);
            if (employeeInformation != null) {
                androidMinaSession.setWatchCode(employeeInformation.getWatchCode());
                personnelService.logicMsgCall(androidMinaSession, msgVo.getMsgType(), employeeInformation.getWatchCode(), messageService);
            }
        }
        //保安
        if (msgVo.getMsgType().equals(AndroidConstant.MESSAGE_TYPE_CALLSECURITY_KEY)){
//            employeeInformation1 = employeeInformationService.selectByMyWork(CountInfo.BAOAN);
            employeeInformation = employeeInformationService.selectByMyWork(CountInfo.BANZHANG);
            if (employeeInformation != null) {
                androidMinaSession.setWatchCode(employeeInformation.getWatchCode());
                personnelService.logicMsgCall(androidMinaSession, msgVo.getMsgType(), employeeInformation.getWatchCode(), messageService);
            }
        }
        if (msgVo.getMsgType().equals(AndroidConstant.MESSAGE_TYPE_FIREALARM_KEY)){
            employeeInformation = employeeInformationService.selectByMyWork(CountInfo.BANZHANG);
            if (employeeInformation != null) {
                androidMinaSession.setWatchCode(employeeInformation.getWatchCode());
                List<EmployeeInformation> employeeInformationList = employeeInformationMapper.selectWatchCodeNotNull();
                messageService.logicMsgUrgent(androidMinaSession,msgVo.getMsgType(),employeeInformation.getWatchCode(),personnelService,employeeInformationList);
            }
        }
        return R.ok();
    }
}
