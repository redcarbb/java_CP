package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javaclass.BanzEngine;
import javaclass.Toyota;

@Configuration
public class CarConfig {

	@Bean
	public Toyota toyota() {
		return new Toyota(new BanzEngine());
	}

}
