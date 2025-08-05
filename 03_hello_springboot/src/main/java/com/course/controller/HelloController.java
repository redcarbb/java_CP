package com.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.service.HelloService;

@RestController

public class HelloController {

	@Autowired
	private HelloService helloService;

	@GetMapping("/home")
	public String home() {
		return "HOME";
	}
}
