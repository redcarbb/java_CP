package com.course.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class EcAspect {

	Logger logger = LoggerFactory.getLogger(EcAspect.class);
	
	// execution(scope return-type fully-qualified-class-name .*(parameters))
	@Pointcut("execution(public * com.course.service.ProductService.*(..))")
	public void pointCutMethod() {
	    // 透由@Pointcut定義切點，方法內容維持空方法
	}
	
	@Before("pointCutMethod()")
	public void beforeAdvice(JoinPoint joinPoint) {
		logger.info("@Before: " + joinPoint.getSignature().getName());
	}
	
	@After("pointCutMethod()")
	public void afterAdvice(JoinPoint joinPoint) {
		logger.info("@After: " + joinPoint.getSignature().getName());
	}
	
	@Around("pointCutMethod()")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("@Around 前: " + pjp.getSignature().getName());
		Object obj = pjp.proceed();
		logger.info("@Around 後: " + pjp.getSignature().getName());
		return obj;
	}
}
