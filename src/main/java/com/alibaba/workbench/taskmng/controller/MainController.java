package com.alibaba.workbench.taskmng.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String root(ModelMap model, HttpServletRequest request) {
		
		return "index.html";
	}
	
	@RequestMapping("/login")
	public String login(ModelMap model) {
		
		return "login.html";
	}
	
	

}
