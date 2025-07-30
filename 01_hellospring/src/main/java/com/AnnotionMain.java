package com;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.component.Kia;
import com.config.AnnotationConfig;
import com.model.SingletonBean;

public class AnnotionMain {

	public static void main(String[] args) {

//		 AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AnnotationConfig.class);
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AnnotationConfig.class);) {
			for (String name : ctx.getBeanDefinitionNames()) {
				System.out.println(name);
			}

			Kia kia = ctx.getBean(Kia.class);
			System.out.println("KIA: " + kia.getEngine());

			Kia kia2 = ctx.getBean(Kia.class);
			System.out.println(kia2);

		}

		SingletonBean sBean = SingletonBean.getInstance();
		System.out.println(sBean);

		SingletonBean sBean2 = SingletonBean.getInstance();
		System.out.println(sBean2);
	}

}
