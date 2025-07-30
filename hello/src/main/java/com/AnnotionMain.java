package com;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.component.Kia;
import com.config.AnnotationConfig;
import javaclass.SingletonBean;

public class AnnotionMain {

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AnnotationConfig.class);) {
			for (String name : ctx.getBeanDefinitionNames()) {
				System.out.println(name);

			}
			Kia kia = ctx.getBean(Kia.class);
			System.out.println(kia);

			Kia kia2 = ctx.getBean(Kia.class);
			System.out.println(kia2);

		}

		SingletonBean sBean = SingletonBean.getInstance();
		System.out.println(sBean);

		
		SingletonBean sBean2 = SingletonBean.getInstance();
		System.out.println(sBean2);

	}

}
