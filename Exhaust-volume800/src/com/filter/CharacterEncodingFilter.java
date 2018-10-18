package com.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class CharacterEncodingFilter extends OncePerRequestFilter {

	private String encoding; 
	
	private boolean forceEncoding = false;
	
	public void setEncoding(String encoding) {  
        this.encoding = encoding;  
    }
	
	public void setForceEncoding(boolean forceEncoding) {  
        this.forceEncoding = forceEncoding;  
    }
	
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (this.encoding != null && (this.forceEncoding || request.getCharacterEncoding() == null)) {  
            request.setCharacterEncoding(this.encoding);  
            if (this.forceEncoding) {  
                response.setCharacterEncoding(this.encoding);  
            }  
        }
        filterChain.doFilter(request, response);
		
	}

}
