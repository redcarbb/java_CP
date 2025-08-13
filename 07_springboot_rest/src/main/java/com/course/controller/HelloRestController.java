package com.course.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController
@RequestMapping(value = "/kitty")
public class HelloRestController {

	@GetMapping("/hello")
//	@ResponseBody
	public String hello() {
		return "hello kitty";
	}
	
	@GetMapping("/hello2")
//	@ResponseBody
	public String hello2() {
		return "hello2";
	}
}
