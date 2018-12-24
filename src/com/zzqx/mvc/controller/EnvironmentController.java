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

import java.util.Date;
import java.util.List;

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

    /**
     * 取一个位置的数据  01
     */
    @OpenAccess
    @RequestMapping("get")
    @ResponseBody
    public R getByArea(){
        List<Environment> environmentList = environmentService.getByArea("01");
//        Environment environment = environmentList.get(0);
        return R.ok().put("data",environmentList);
    }
}
