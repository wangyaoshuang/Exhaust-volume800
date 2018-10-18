package com.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component 
public class UserSecurityInterceptor implements HandlerInterceptor {

private List<String> excludedUrls;
	
	
	
	public List<String> getExcludedUrls() {
		return excludedUrls;
	}

	public void setExcludedUrls(List<String> excludedUrls) {
		this.excludedUrls = excludedUrls;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		
		String requestUri = arg0.getRequestURI();
		
        for (String url : excludedUrls) {
            if (requestUri.contains(url)) {
                return true;
            }
        }
        
        HttpSession session = arg0.getSession();
        if (session.getAttribute("teacher") == null) {
            arg1.sendRedirect(arg0.getContextPath() + "/teacher/toLogin");
            return false;
        }else
        	return true;
	}

}
