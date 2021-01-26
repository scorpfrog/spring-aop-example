package com.example.spring.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import java.util.logging.Logger;

@Aspect
@Order(2)
public class LoggingAspect2 {

    private Logger logger = Logger.getLogger(LoggingAspect1.class.getName());

    @Pointcut("execution(* com.example.spring.aop.domain.*.*set*(..))")
    public void allSetters() {

    }

    @Around("allSetters()")
    public Object log (ProceedingJoinPoint thisJoinPoint) throws Throwable {
        String methodName = thisJoinPoint.getSignature().getName();
        Object[] methodArgs = thisJoinPoint.getArgs();
        logger.severe("Call method " + methodName + " with arg " + methodArgs[0]);
        Object result = thisJoinPoint.proceed();
        logger.severe("Method " + methodName + " returns " + result);
        return result;
    }
}
