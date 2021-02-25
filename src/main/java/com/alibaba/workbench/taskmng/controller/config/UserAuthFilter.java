package com.alibaba.workbench.taskmng.controller.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.workbench.taskmng.controller.utils.CookieUtils;
import com.alibaba.workbench.taskmng.controller.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

@WebFilter(filterName = "UserAuthFilter", urlPatterns = "/*")
@Slf4j
public class UserAuthFilter implements Filter {
 
	private static final HashSet<String> LoginUris = new HashSet<String>( Arrays.asList("/",  "/task/.*" ) );
	
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
 
    }
 
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        
        String path = request.getRequestURI();
        
        log.info("URL地址：" + request.getRequestURL() );
        
        Enumeration<String> eh = request.getHeaderNames();
        StringBuilder sb = new StringBuilder();
        while( eh.hasMoreElements() ) {
        	String headName = eh.nextElement();
        	sb.append( headName + "=" +  request.getHeader( headName) ).append(";");
        }
        log.info("Header信息：" + sb.toString() );
        
        for( String uri : LoginUris ) {
	    	if ( path.matches( uri ) ) {
	    		UserVO user =  CookieUtils.getUserFromCookie(request);
	    		if ( user == null ) {
	    			String loginUrl = "/login";
	    			
	    			response.sendRedirect(loginUrl);
	    			return;
	    		}
	    	}
        }
        
        //打印cookie
		CookieUtils.logCookie(  request );
        
		filterChain.doFilter(servletRequest, response);
        
    }
 
    @Override
    public void destroy() {
 
    }

}