package com.webmvc.Employee.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class Applogger {

	//Logger log = LoggerFactory.getLogger(Applogger.class);
	@Before("execution(* com.webmvc.Employee..*(..))")
	public void beforeMessage(JoinPoint joinPoint) {
		log.info("Before Application : start method is going to execute "+joinPoint.getClass().getName());
	}
	
	@After("execution(* com.webmvc.Employee..*(..))")
	public void afterMessage(JoinPoint joinPoint) {
		log.info("After Application : execute method is going to terminate "+joinPoint.getClass().getName());
	}
}
