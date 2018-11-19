package com.zzqx.mvc.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jetsum.core.web.AbstractController;
import com.zzqx.support.utils.net.Host;

public class BaseController extends AbstractController {

	protected String getBasePath(HttpServletRequest request) {
		if(request == null) {
			return "";
		}
		String path = request.getContextPath();
		String serverName = "localhost".equals(request.getServerName())||"127.0.0.1".equals(request.getServerName())?Host.getExtranetIPv4Address():request.getServerName();
		String temp = request.getScheme()+"://"+serverName+":"+request.getServerPort()+path+"/";
		return temp;
	}

	@Override
	protected Map<String, String> getQueryParameterType() {
		return null;
	}
	
}
