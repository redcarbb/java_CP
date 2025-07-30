package com;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.model.Toyota;

public class SpringMain {

	public static void main(String[] args) {
		 ApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");
		 
		 for (String name : ctx.getBeanDefinitionNames()) {
			 System.out.println(name);
		 }
		 
		 Toyota toyota = (Toyota)ctx.getBean("toyota1");
		 toyota.move();
		 System.out.println(toyota);
		 
		 Toyota toyota2 = ctx.getBean("toyota2", Toyota.class);
		 toyota2.move();
		 System.out.println(toyota2);
		 
		 System.out.println("=====================");
		 Toyota toyota3 = ctx.getBean("toyota3", Toyota.class);
		 toyota3.move();
		 System.out.println(toyota3);
		 
	}

}
