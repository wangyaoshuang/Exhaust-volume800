package com.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.entity.Teacher;

public class SessionFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String[] notFilter=new String[]{"generatecheckcode","login"};
		String uri=request.getRequestURI();
		System.out.println(uri);
		boolean doFilter=true;
		for(String s:notFilter){
			if(uri.indexOf(s)!=-1){
				doFilter=false;
				break;
			}
			if(uri.equals("/Exhaust-volume/")){
				doFilter=false;
				break;
			}
		}
		if(false){
			Teacher teacher = (Teacher)request.getSession().getAttribute("Teacher");
			if(null==teacher){
				// 如果session中不存在登录者实体，则弹出框提示重新登录
				// 设置request和response的字符集，防止乱码
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				String loginPage = "/Exhaust-volume/login.jsp";
				StringBuilder builder = new StringBuilder();
				builder.append("<script type=\"text/javascript\">");
				builder.append("alert('网页过期，请重新登录！');");
				builder.append("window.top.location.href='");
				builder.append(loginPage);
				builder.append("';");
				builder.append("</script>");
				out.print(builder.toString());
			}else{
				//如果session中存在登陆者实体，则继续
				filterChain.doFilter(request, response);
			}
		}else{
			//如果不执行过滤，则继续
			filterChain.doFilter(request, response);
		}
	}
}
