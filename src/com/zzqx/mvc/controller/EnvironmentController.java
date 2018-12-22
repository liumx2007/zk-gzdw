package com.zzqx.mvc.controller;

import com.zzqx.mvc.annotation.OpenAccess;
import com.zzqx.mvc.commons.CountInfo;
import com.zzqx.mvc.entity.Environment;
import com.zzqx.mvc.javabean.R;
import com.zzqx.mvc.service.EnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Controller
@RequestMapping("/environment")
public class EnvironmentController {

    @Autowired
    EnvironmentService environmentService;


    @OpenAccess
    @RequestMapping("save")
    @ResponseBody
    public R saveEvo(Environment environment){
        environment.setHallId(CountInfo.HALL_ID);
        environment.setCreateTime(new Date());
        int flag = environmentService.saveEvo(environment);
        return R.ok();
    }
}
