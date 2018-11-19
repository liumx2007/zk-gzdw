package com.zzqx.mvc.validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zzqx.Global;

//import com.zzqx.support.utils.net.Host;

public class CheckAuthorizedInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return "/server/user/login.do".equals(request.getRequestURI()) 
				|| "/server/r/page/main".equals(request.getRequestURI()) 
				|| Global.authorizeStatus != 0;
	}
}

