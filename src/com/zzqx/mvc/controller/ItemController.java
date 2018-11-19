package com.zzqx.mvc.controller;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzqx.mvc.annotation.OpenAccess;
import com.zzqx.mvc.entity.Item;
import com.zzqx.mvc.javabean.ReturnMessage;
import com.zzqx.mvc.service.ItemService;
import com.zzqx.support.utils.StringHelper;
import com.zzqx.support.utils.net.SocketDataSender;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/item")
public class ItemController extends BaseController {

	@Autowired
	private ItemService itemService;
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("get")
	public String get(String item) {
		List<Item> list = itemService.find(Restrictions.eq("item", item));
		Item it = new Item();
		if(list.size() > 0) {
			it = list.get(0);
		}
		return JSONObject.fromObject(it).toString();
	}
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("edit")
	public String edit(String item, String value, String noticeIpOrCodeName, String noticeData) {
		ReturnMessage message = new ReturnMessage();
		message.setType(ReturnMessage.MESSAGE_SUCCESS);
		List<Item> list = itemService.find(Restrictions.eq("item", item));
		Item it = new Item();
		if(list.size() > 0) {
			it = list.get(0);
		}
		it.setItem(item);
		it.setValue(value);
		itemService.saveOrUpdate(it);
		if(StringHelper.isNotBlank(noticeIpOrCodeName)) {
			for(String ipOrCodeName : noticeIpOrCodeName.split(",")) {
				SocketDataSender sender = new SocketDataSender();
				sender.sendToTerminal(ipOrCodeName, noticeData);
			}
		}
		message.setMessage("修改成功！");
		return message.toString();
	}
}
