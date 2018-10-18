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
				// ���session�в����ڵ�¼��ʵ�壬�򵯳�����ʾ���µ�¼
				// ����request��response���ַ�������ֹ����
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				String loginPage = "/Exhaust-volume/login.jsp";
				StringBuilder builder = new StringBuilder();
				builder.append("<script type=\"text/javascript\">");
				builder.append("alert('��ҳ���ڣ������µ�¼��');");
				builder.append("window.top.location.href='");
				builder.append(loginPage);
				builder.append("';");
				builder.append("</script>");
				out.print(builder.toString());
			}else{
				//���session�д��ڵ�½��ʵ�壬�����
				filterChain.doFilter(request, response);
			}
		}else{
			//�����ִ�й��ˣ������
			filterChain.doFilter(request, response);
		}
	}
}
