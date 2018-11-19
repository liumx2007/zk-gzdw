package com.zzqx.mvc.controller;

import com.zzqx.mvc.annotation.OpenAccess;
import com.zzqx.mvc.dao.InteractionMapper;
import com.zzqx.mvc.entity.Interaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/interaction")
public class InteractionController {
    @Autowired
    InteractionMapper interactionMapper;

    @OpenAccess
    @RequestMapping("one")
    @ResponseBody
    public String list(String id){
//        ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "修改成功！");
        Interaction interaction = interactionMapper.selectByPrimaryKey(id);
        return null;
    }
}
