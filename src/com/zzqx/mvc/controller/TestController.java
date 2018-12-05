package com.zzqx.mvc.controller;

import com.zzqx.mvc.annotation.OpenAccess;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/test")
public class TestController extends BaseController {

	@OpenAccess
	@ResponseBody
	@RequestMapping("get")
	public String get() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "{\"categories\":[\"一月\",\"二月\",\"三月\",\"四月\",\"五月\"],\"values\":[111,222,333,444,555]}";
	}
}