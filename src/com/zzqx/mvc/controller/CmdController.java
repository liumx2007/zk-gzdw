package com.zzqx.mvc.controller;

import com.zzqx.mvc.entity.Cmd;
import com.zzqx.mvc.javabean.R;
import com.zzqx.mvc.service.CmdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cmd")
public class CmdController {
    @Autowired
    CmdService cmdService;

    @RequestMapping("list")
    @ResponseBody
    public R getList(Cmd cmd){
        List<Cmd> list = cmdService.getList(cmd);
        return R.ok().put("data",list);
    }
}
