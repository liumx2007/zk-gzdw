package com.zzqx.mvc.controller;

import com.zzqx.mvc.annotation.OpenAccess;
import com.zzqx.mvc.dto.InteractionLogDto;
import com.zzqx.mvc.entity.InteractionLog;
import com.zzqx.mvc.javabean.R;
import com.zzqx.mvc.service.InteractionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@OpenAccess
@Controller
@RequestMapping("/interactionlog")
public class InteractionLogController {

    @Autowired
    InteractionLogService interactionLogService;

    @RequestMapping("list1")
    @ResponseBody
    public R selectByEntity(InteractionLog interactionLog){
        List<InteractionLog> logList = interactionLogService.selectByEntity(interactionLog);
        return R.ok().put("interactionLogList" , logList);
    }

    @RequestMapping("list")
    @ResponseBody
    public R selectWithInteraction(InteractionLogDto interactionLogDto){
        List<InteractionLog> logList = interactionLogService.selectWithInteraction(interactionLogDto);
        return R.ok().put("interactionLogList" , logList);
    }

    @RequestMapping("save")
    @ResponseBody
    public R insertSelective(InteractionLog interactionLog){
        Integer i = interactionLogService.insertSelective(interactionLog);
        return R.ok();
    }
}
