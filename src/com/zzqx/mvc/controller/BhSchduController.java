package com.zzqx.mvc.controller;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.Digester;
import com.zzqx.mvc.annotation.OpenAccess;
import com.zzqx.mvc.dao.BhSchduMapper;
import com.zzqx.mvc.entity.BhSchdu;
import com.zzqx.mvc.javabean.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@OpenAccess
@RequestMapping("/bhschdu")
public class BhSchduController {

        @Autowired
        BhSchduMapper bhSchduMapper;

        @RequestMapping("findone")
        @ResponseBody
        public R selectById(String id){
            BhSchdu bhSchdu = bhSchduMapper.selectByPrimaryKey(id);
            return R.ok().put("bhSchdu",bhSchdu);
        }
}
