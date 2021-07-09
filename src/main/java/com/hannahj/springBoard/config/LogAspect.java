package com.hannahj.springBoard.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    private static final Logger logger =  org.slf4j.LoggerFactory.getLogger(LogAspect.class);
    
    @Before("execution(* com.hannahj.springBoard.repository.*.*Aop(..))")
    public void onBeforeHandler(JoinPoint joinPoint) {
        logger.info("======================= onBeforeThing");
        System.out.println("======================= onBeforeThing");
    }
    @After("execution(* com.hannahj.springBoard.repository.*.*Aop(..))")
    public void onAfterHandler(JoinPoint joinPoint) {
        logger.info("======================= onAfterThing");
        System.out.println("======================= onAfterThing");
    }
    @Around("execution(* com.hannahj.springBoard.repository.*.*Aop(..))")
    public void onAroundHandler(JoinPoint joinPoint) {
        logger.info("======================= onAroundThing");
        System.out.println("======================= onAroundThing");
    }
    
    @AfterReturning(pointcut = "execution(* com.hannahj.springBoard.repository.*.*Aop(..))", returning = "str")
    public void onAfterReturningHandler(JoinPoint joinPoint, Object str) {
        logger.info("@AfterReturning: " + str);
        System.out.println("@AfterReturning: " + str);
        logger.info("======================= onAfterReturningHandler");
        System.out.println("======================= onAfterReturningHandler");
    }
    @Pointcut("execution(* com.hannahj.springBoard.repository.*.*Aop(..))")
    public void onPointcut(JoinPoint joinPoint) {
        logger.info("======================= onPointcut");
        System.out.println("======================= onPointcut");
    }
    
}
