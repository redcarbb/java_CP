package com.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JspController {

	@GetMapping("/home")
	public String home() {
		
		
		return "index";
	}
	
	@GetMapping("/register")
	public String register() {
		
		
		return "register";
	}
}
