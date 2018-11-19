package com.zzqx.mvc.validation;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zzqx.mvc.annotation.OpenAccess;
import com.zzqx.mvc.entity.User;

public class CheckLoggedInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			System.out.println(((HandlerMethod) handler).getBean().getClass().getName()+" ---> "+((HandlerMethod) handler).getMethod().getName());
			Class<?> clazz = ((HandlerMethod) handler).getBean().getClass();
			HttpSession session = request.getSession(true);
			User user = (User) session.getAttribute("user");
			if(clazz.getAnnotation(OpenAccess.class) != null || 
					((HandlerMethod) handler).getMethod().getAnnotation(OpenAccess.class) != null
					|| user != null) {
				return true;
			} else {
				String path = request.getContextPath();
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
				PrintWriter out;
				response.setContentType("text/html;charset=utf-8");
				out = response.getWriter();
				out.println("<script type='text/javascript' pageEncoding='utf-8' >");
				out.println("window.top.location.href='"+basePath+"login.jsp';");
				out.println("</script>");
				out.close();
				return false;
			}
		} else {
			return true;
		}
	}
}
