package com.alibaba.workbench.taskmng.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.workbench.taskmng.controller.utils.CookieUtils;
import com.alibaba.workbench.taskmng.controller.vo.UserVO;

@RestController
@RequestMapping("user")
public class UserController {
	
	@PostMapping("/auth")
	public ModelAndView login(UserVO user, HttpServletRequest request, HttpServletResponse  response) {
		ModelAndView mv = new ModelAndView();

		/* 避免使用
	    request.getSession().setAttribute("user", user);
		UserVO user = (UserVO) request.getSession().getAttribute("user");
		*/
		
		try {
			String  domain =  request.getServerName();
			CookieUtils.setUserFromCookie(response, domain,  user);
			
			mv.addObject(user);
			mv.setViewName("redirect:/");
			
			return mv;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException( e.getMessage(), e );
		}
		
	}

}
