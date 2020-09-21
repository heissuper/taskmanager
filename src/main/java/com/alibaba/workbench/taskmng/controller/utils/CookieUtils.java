package com.alibaba.workbench.taskmng.controller.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.workbench.taskmng.controller.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ningzhong.wyl
 *
 */
@Slf4j
public class CookieUtils {
	private static final String   USERINFO_COOKIE_NAME="DEMO_USERINFO";
	
	
	static public  void   logCookie( HttpServletRequest request ) throws UnsupportedEncodingException {
		Cookie[]  cookies = request.getCookies();
		if( cookies==null ) {
			return;
		}
		
		for( int i=0;i< cookies.length;i++) {
			log.info( cookies[i].getName() + "=" + cookies[i].getValue() );
		}
	}
	
	/**从cookie 中获取登录用户
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	static public  UserVO   getUserFromCookie( HttpServletRequest request ) throws UnsupportedEncodingException {
		Cookie cookieUserInfo = CookieUtils.getCookie( USERINFO_COOKIE_NAME, request);
		UserVO user = null;
		if( cookieUserInfo!=null && StringUtils.hasText(cookieUserInfo.getValue() ) ) {
			byte[] decoded = Base64.getDecoder().decode( cookieUserInfo.getValue() );
			user = JSON.parseObject( new String(decoded, "UTF-8"), UserVO.class );
		}
		return user;
	}
	
	
	/** 设置登录用户信息
	 * @param response
	 * @param user
	 * @throws UnsupportedEncodingException
	 */
	static public  void   setUserFromCookie( HttpServletResponse  response , String  domain,  UserVO user ) throws UnsupportedEncodingException {
		String strUser = JSON.toJSONString( user );
		String cookieValue = Base64.getEncoder().encodeToString( strUser.getBytes("UTF-8") );
		
		//种cookie
		Cookie cookie = new Cookie( USERINFO_COOKIE_NAME, cookieValue );
		cookie.setDomain( domain );
		cookie.setMaxAge( 1800 );
		cookie.setSecure( false );
		cookie.setPath("/");
		cookie.setSecure( false );
				
		response.addCookie(cookie);
	}
	
	static  public   Cookie getCookie(String key, HttpServletRequest request) {
        if (request == null || StringUtils.isEmpty (key) ) {
            return null;
        }
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        Cookie value = null;
        for (Cookie c : cookies) {
            if (key.equals(c.getName())) {
                value = c;
                break;
            }
        }
        return value;
    }
	
}
