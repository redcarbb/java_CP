package com.course;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
//		for (String name : ctx.getBeanDefinitionNames()) {
//			System.out.println(name);
//		}
		
		
		Logger logger = LoggerFactory.getLogger(Application.class);
		logger.error("ERROR 出事了!!!!");
		logger.warn("WARN");
		logger.info("INFO");
		logger.debug("DEBUG");
		logger.trace("TRACE");
	}

}
