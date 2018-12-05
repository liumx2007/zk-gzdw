package com.zzqx.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NavigatorController extends BaseController {
	
	@RequestMapping(value="/page/{page}", method=RequestMethod.GET)
	public String direct(@PathVariable("page") String page) {
		if("main".equals(page)) {
			
		}
		return page;
	}

	@RequestMapping(value="/page/{kind}/{page}", method=RequestMethod.GET)
	public String direct(@PathVariable("kind") String kind, @PathVariable("page") String page) {
		return kind+"/"+page;
	}
}
