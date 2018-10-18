package com.interceptor;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.entity.Role;
import com.entity.Teacher;

public class PowerInterceptor implements HandlerInterceptor {

	private List<String> jiaoshiUrls;
	private List<String> jiaowuUrls;

	public List<String> getJiaoshiUrls() {
		return jiaoshiUrls;
	}

	public void setJiaoshiUrls(List<String> jiaoshiUrls) {
		this.jiaoshiUrls = jiaoshiUrls;
	}

	public List<String> getJiaowuUrls() {
		return jiaowuUrls;
	}

	public void setJiaowuUrls(List<String> jiaowuUrls) {
		this.jiaowuUrls = jiaowuUrls;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		String requestUri = arg0.getRequestURI();
		
		HttpSession session = arg0.getSession();
		
		Teacher t=(Teacher)session.getAttribute("teacher");
		
		if(t==null){
			return true;
		}
		String choice=(String)session.getAttribute("choice");
		if(choice==null||choice.equals("")){
			arg1.sendRedirect(arg0.getContextPath() + "/teacher/toLogin");
	        return false;
		}
		if(choice.equals("ΩÃ ¶")){
			for (String url : jiaoshiUrls) {	
				if (requestUri.contains(url)) {
		            return true;
		        }
			}
		}else{
			for (String url : jiaowuUrls) {
		        if (requestUri.contains(url)) {
		            return true;
		        }
		    }
		}
				
        arg1.sendRedirect(arg0.getContextPath() + "/error/error404.jsp");
        return false;
	}

}
