package com.zzqx.support.framework.task.timerTask;

import com.zzqx.mvc.dao.InteractionLogMapper;
import com.zzqx.mvc.entity.InteractionLog;
import com.zzqx.mvc.entity.InteractionLogExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class InteractionLogTimeTask {

    @Autowired
    InteractionLogMapper interactionLogMapper;
    /**
     * 定时上传数据
     */
    public void save(){
        InteractionLogExample interactionLogExample =new InteractionLogExample();
        InteractionLogExample.Criteria criteria = interactionLogExample.createCriteria();
        criteria.andStatusEqualTo("0");
        List<InteractionLog> logList = interactionLogMapper.selectByExample(interactionLogExample);
        for (int i = 0; i<logList.size(); i++){

        }
//        Map<String,Object> map = new HashMap<>();
//        map.put("data",logList);
//        HttpUtil.post(CountInfo.POST_INTERACTIONLOG_DATA, map,3000);
    }
}
