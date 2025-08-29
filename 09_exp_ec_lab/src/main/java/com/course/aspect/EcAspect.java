package com.course.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.course.vo.ProductVo;

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
		Long start = System.currentTimeMillis();
		
		Object obj = pjp.proceed();
		if (obj instanceof List<?>) {
			List<?> resultList = (List<?>) obj;
			if (resultList != null && resultList.get(0) instanceof ProductVo) {
				ProductVo vo = (ProductVo)resultList.get(0);
				vo.setName(vo.getName() + "!!!!!");
			}
		}
		
		Long end = System.currentTimeMillis();
		logger.info("@Around 後: 執行時間 "+ (end - start)  + "ms");
		return obj;
	}
	
	@AfterReturning(value = "pointCutMethod()", returning = "re2")
	public void afterReturnAdvice(JoinPoint joinPoint, Object re2) {
		logger.info("@@AfterReturning: " + joinPoint.getSignature().getName());
	}
	
	@AfterThrowing(value = "pointCutMethod()", throwing = "ex")
	public void afterThrowingAdvice(JoinPoint joinPoint, Throwable ex) {
		logger.info("@@@AfterThrowing: " + joinPoint.getSignature().getName());
	}
	
	@Pointcut("within(com.course.controller..*)")
	public void pointCutWithIn() {
	    // 透由@Pointcut定義切點，方法內容維持空方法
	}
	
	@Before("pointCutWithIn()")
	public void beforeWithInAdvice(JoinPoint joinPoint) {
		logger.info("@Before WithIn: " + joinPoint.getSignature().getName());
	}
}
