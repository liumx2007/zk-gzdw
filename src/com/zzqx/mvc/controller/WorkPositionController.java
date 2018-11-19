package com.zzqx.mvc.controller;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzqx.mvc.entity.WorkPosition;
import com.zzqx.mvc.service.WorkPositionService;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;


@Controller
@RequestMapping(value = "/position")
public class WorkPositionController extends BaseController {

	@Autowired
	private WorkPositionService workPositionService;
	@ResponseBody
	@RequestMapping("getAllPosition")
	public String getAll() {
		Query query = workPositionService.createQuery("from WorkPosition w where 1=? order by w.order_by asc", 1);
		List<WorkPosition> list = query.list();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		return jsonArray.toString();
	}
	

	
}
