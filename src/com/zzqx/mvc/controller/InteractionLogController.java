package com.zzqx.mvc.controller;

import com.zzqx.mvc.annotation.OpenAccess;
import com.zzqx.mvc.dto.InteractionLogDto;
import com.zzqx.mvc.entity.InteractionLog;
import com.zzqx.mvc.javabean.R;
import com.zzqx.mvc.service.InteractionLogService;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
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
//        JsonConfig jsonConfig = new JsonConfig();
//        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
//        return JSONArray.fromObject(logList, jsonConfig).toString();
        return R.ok().put("interactionLogList" , logList);
    }

    @RequestMapping("list")
    @ResponseBody
    public String selectWithInteraction(InteractionLogDto interactionLogDto){
        List<InteractionLog> list = interactionLogService.selectWithInteraction(interactionLogDto);
        cn.hutool.json.JSONArray array = new cn.hutool.json.JSONArray();
        array.add(list);
        String s = array.toString();
        String re  = s.substring(1,s.length()-1);
        return  re;
    }

    @RequestMapping("save")
    @ResponseBody
    public R insertSelective(InteractionLog interactionLog){
        Integer i = interactionLogService.insertSelective(interactionLog);
        return R.ok();
    }
}
