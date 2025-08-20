package com.jennyandaven.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JaController {

	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
}
