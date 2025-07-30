package com;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.config.CarConfig;
import com.model.Toyota;

public class JavaMain {

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
