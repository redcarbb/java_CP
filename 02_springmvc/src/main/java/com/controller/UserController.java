package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.service.UserService;

// @Component
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public String login(@RequestParam("u1") String username, @RequestParam("p1") String password) {
		// "/WEB-INF/views/login.jsp" 
		
		System.out.println(username);
		System.out.println(password);
		if (userService.checkLogin(username, password)) {
			return "loginSuccess";
		} else {
			return "loginFail";
		}
		
	}
}
