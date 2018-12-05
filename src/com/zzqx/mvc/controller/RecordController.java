package com.zzqx.mvc.controller;

import com.jetsum.core.orm.entity.Page;
import com.zzqx.mvc.entity.Record;
import com.zzqx.mvc.javabean.ReturnData;
import com.zzqx.mvc.javabean.ReturnMessage;
import com.zzqx.mvc.service.RecordService;
import com.zzqx.support.utils.StringHelper;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/record")
public class RecordController extends BaseController {

    @Autowired
    RecordService recordService;

    /**
     * 数据新增
     */
    @ResponseBody
    @RequestMapping("add")
    public String add(Record record){
        ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "添加成功！");
        recordService.saveOrUpdate(record);
        return message.toString();
    }
    /**
     * 分页数据
     *
    */
    @ResponseBody
    @RequestMapping("/pagelist")
    public String pageList(String page, String rows, HttpServletRequest request){
        int pageNo = StringHelper.isEmpty(page)?1:Integer.parseInt(page);
        int pageSize =  StringHelper.isEmpty(rows)?10:Integer.parseInt(rows);
        Map<String, Object> map = getQueryParameter(request);
        Page<Record> thisPage = recordService.getByPage(map, pageNo, pageSize, "updateTime", "desc");
        List<Record> list = thisPage.getResult();
        ReturnData data = new ReturnData(thisPage.getTotalCount(), list);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        return data.toString(jsonConfig);
    }
}
