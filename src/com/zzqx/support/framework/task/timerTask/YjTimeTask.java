package com.zzqx.support.framework.task.timerTask;

import cn.hutool.http.HttpUtil;
import com.zzqx.mvc.commons.CountInfo;
import com.zzqx.mvc.entity.Yj;
import com.zzqx.mvc.service.YjService;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YjTimeTask {

    @Autowired
    YjService yjService;
    /**
     * 上传意见簿数据
     */
    @Transactional
    public void postYjData() {
        List<Yj> list  = yjService.find(Restrictions.eq("upStatus",0));
        if(list.size() > 0) {
            Yj yj = list.get(0);
            if (yj != null) {
                try {
                    Map<String, Object> map = new HashMap<>();
                    map.put("hallId", CountInfo.HALL_ID);
                    map.put("id", yj.getId());
                    map.put("flag", yj.getFlag());
                    map.put("isShow", yj.getIsShow());
                    map.put("status", yj.getStatus());
                    map.put("type", yj.getType());
                    map.put("hasFk", yj.getHasFk());
                    if (yj.getBh() != null) {
                        map.put("bh", yj.getBh());
                    }
                    if (yj.getContent() != null) {
                        map.put("content", yj.getContent());
                    }
                    if (yj.getFeedback() != null) {
                        map.put("feedback", yj.getFeedback());
                    }
                    if (yj.getName() != null) {
                        map.put("name", yj.getName());
                    }
                    if (yj.getPhone() != null) {
                        map.put("phone", yj.getPhone());
                    }
                    if (yj.getDept() != null){
                        map.put("dept",yj.getDept());
                    }
                    if (yj.getFeedbackName() !=  null){
                        map.put("feedbackName",yj.getFeedbackName());
                    }
                    map.put("addTime", yj.getAddTime().getTime());
                    if (yj.getFlagTime() != null) {
                        map.put("flagTime", yj.getFlagTime().getTime());
                    }
                    if (yj.getFeedbackTime() != null) {
                        map.put("feedbackTime", yj.getFeedbackTime().getTime());
                    }
                    String str = HttpUtil.post(CountInfo.POST_YJ_DATA, map,3000);
                    if (str.contains("成功")) {
                        yj.setUpStatus(1);
                        yjService.saveOrUpdate(yj);
                        System.out.println("意见建议数据：" + yj.getId() + "上传完成");
                    }
                } catch (Exception e) {
                    System.out.println("意见建议数据：" + yj.getId() + "上传失败");
                }
            } else {
                System.out.println("没有未上传的意见建议数据！");
            }
        }
    }
}
