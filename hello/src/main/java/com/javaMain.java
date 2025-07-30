package com;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.config.CarConfig;
import javaclass.Toyota;

public class javaMain {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(CarConfig.class);

		for (String name : ctx.getBeanDefinitionNames()) {
			System.out.println(name);
		}
		
		 Toyota toyota = (Toyota)ctx.getBean("toyota");
		 toyota.move();
		 System.out.println(toyota);
	}

}
