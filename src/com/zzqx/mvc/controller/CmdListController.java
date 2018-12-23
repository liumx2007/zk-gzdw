package com.zzqx.mvc.controller;

import com.zzqx.mvc.dto.CmdListDto;
import com.zzqx.mvc.entity.CmdList;
import com.zzqx.mvc.javabean.ReturnData;
import com.zzqx.mvc.service.CmdListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cmdList")
public class CmdListController {
    @Autowired
    CmdListService cmdListService;

    @RequestMapping("list")
    @ResponseBody
    public String selectWithCmdList(String page,String rows,CmdListDto cmdListDto){
        //分页数据
        List<CmdList> list = cmdListService.getList(cmdListDto);
        //数据总数
        long total = cmdListService.getListCount(cmdListDto).size();
        ReturnData data = new ReturnData(total,list);
        cn.hutool.json.JSONArray array = new cn.hutool.json.JSONArray();
        array.add(data);
        String s = array.toString();
        String result  = s.substring(1,s.length()-1);
        return  result;
    }
}
