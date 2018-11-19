package com.zzqx.mvc.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzqx.mvc.annotation.OpenAccess;
import com.zzqx.mvc.entity.Queue;
import com.zzqx.mvc.javabean.ReturnMessage;
import com.zzqx.mvc.service.QueueService;
import com.zzqx.support.utils.StringHelper;

import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/queue")
public class QueueController extends BaseController {
	
	@Autowired
	private QueueService queueService;

	@OpenAccess
	@ResponseBody
	@RequestMapping("add")
	public String add(String content) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "录入成功！");
		System.out.println("拦截到短信："+content);
		List<String> ls = new ArrayList<>();
        Pattern pattern = Pattern.compile("(?<=\\【)(.+?)(?=\\】)");
        Matcher matcher = pattern.matcher(content);
        while(matcher.find()) {
            ls.add(matcher.group());
        }
        if(ls.size() == 5) {
	        Queue queue = new Queue();
	        queue.setAddTime(new Date());
	        queue.setConsNo(ls.get(1));
	        queue.setConsName(ls.get(2));
	        queue.setConsTag(Arrays.asList(ls.get(3).split("、")).stream().collect(Collectors.joining(",")));
	        queue.setContent(content);
	        queue.setBusiCode(ls.get(4));
	        queue.setQueueNo(content.substring(content.lastIndexOf("】") + 1, content.lastIndexOf("，")));
	        queueService.saveOrUpdate(queue);
        }
		return message.toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("getQueue")
	public String getQueue(String startDate) {
		Date date = null;
		if(StringHelper.isNotBlank(startDate)) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				date = dateFormat.parse(startDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(date == null) {
			Calendar calendar = Calendar.getInstance();
        	calendar.setTime(new Date());
        	calendar.set(Calendar.HOUR_OF_DAY, 0);
        	calendar.set(Calendar.MINUTE, 0);
        	calendar.set(Calendar.SECOND, 0);
        	date = calendar.getTime();
		}
		List<Queue> list = queueService.createQuery("from Queue where addTime > ? order by addTime desc", date).list();
		return JSONArray.fromObject(list).toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("getAd")
	public String getAd() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MINUTE, -1000);
		Date date = calendar.getTime();
		List<Queue> list = queueService.createQuery("from Queue where addTime > ?", date).list();
		List<String> tags = new ArrayList<>();
		if(list.isEmpty()) {
			return JSONArray.fromObject(tags).toString();
		}
		String[] tagsArray = list.stream().map(Queue::getConsTag).map(consTag->consTag.split(",")).reduce((a,b)->{
			return Stream.concat(Stream.of(a), Stream.of(b)).toArray(String[]::new);
		}).get();
		tags = arraySort(tagsArray);
		return JSONArray.fromObject(tags).toString();
	}
	
	private static List<String> arraySort(String[] arr) {  
        // 定义map，存放数组中的字符及出现次数  
        Map<String, Integer> map = new HashMap<String, Integer>();  
          
        // 遍历数组，将字符及出现次数存放map中  
        for (String str : arr) {  
            Integer count = map.get(str);  
            if (null != count) {  
                map.put(str, count + 1);  
            } else {  
                map.put(str, 1);  
            }  
        }  
          
        // 定义list，存放map中的entry  
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>();  
        list.addAll(map.entrySet());  
          
        // 对list中的entry，按照value值进行降序排列  
        Collections.sort(list, new Comparator<Entry<String, Integer>>(){  
            public int compare(Entry<String, Integer> arg0, Entry<String, Integer> arg1) {  
                return arg1.getValue().compareTo(arg0.getValue());  
            }  
        });  
          
        // 定义StringBuffer，存放返回的字符串 
        List<String> sortList = new ArrayList<>();
        for (Entry<String, Integer> entry : list) {
        	sortList.add(entry.getKey());
        }  
          
        return sortList;
    }
}
