package com.zzqx.support.framework.task.timerTask;

import cn.hutool.http.HttpUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2018/11/23 0023.
 */
@Transactional
public class SynchronizeTimerTask {
    private String url = "";
    /**
     * 每分钟获取排班变动
     */
    public void doMessageTask() {
//        url = HttpUtil.get(httpCore + "/api/employeeInformation/updateByWatchCode?workState=4&watchCode=" + watchCode);
    }

    /**
     * 每天获取排版和人员信息同步
     */
    public void doSynchronizeTask(){

    }
}
