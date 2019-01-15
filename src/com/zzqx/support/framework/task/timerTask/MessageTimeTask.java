package com.zzqx.support.framework.task.timerTask;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import com.zzqx.mvc.commons.CountInfo;
import com.zzqx.mvc.dao.MessageDao;
import com.zzqx.mvc.entity.Message;
import com.zzqx.mvc.service.MessageService;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageTimeTask {

    @Autowired
    MessageDao messageDao;
    @Autowired
    MessageService messageService;
    /**
     * 上传消息数据
     */
    @Transactional
    public void postMessageData() {
//        Query query = messageService.createQuery("from Message m where m.upStatus = ? order by m.create_time desc ",0);
//        List<Message> list = query.list();
        List<Message> list  = messageService.find(Restrictions.eq("upStatus",0));
        if(list.size() > 0) {
            Message message = list.get(0);
            Date createTime = DateUtil.parse(message.getCreate_time());
            Date editTime = DateUtil.parse(message.getEdit_time());
            if (message != null) {
                try {
                    Map<String, Object> map = new HashMap<>();
                    map.put("hallId", CountInfo.HALL_ID);
                    map.put("id", message.getId());
                    map.put("title", message.getTitle());
                    map.put("content", message.getContent());
                    map.put("type", message.getType());
                    map.put("watchCode", message.getWatch_code());
                    map.put("statu", message.getStatu());
                    map.put("createTime", createTime.getTime());
                    if (editTime != null) {
                        map.put("editTime", editTime.getTime());
                    }
                    map.put("creator", message.getCreator());
                    if (message.getOrdertime() != null) {
                        map.put("ordertime", message.getOrdertime().getTime());
                    }
                    String str = HttpUtil.post(CountInfo.POST_MESSAGE_DATA, map,3000);
                    if (str.contains("成功")) {
                        message.setUpStatus(1);
                        messageDao.saveOrUpdate(message);
                        System.out.println("消息数据：" + message.getId() + "上传完成");
                    }
                } catch (Exception e) {
                    System.out.println("消息数据：" + message.getId() + "上传失败");
                }
            } else {
                System.out.println("没有未上传的信息数据！");
            }
        }
    }
}
