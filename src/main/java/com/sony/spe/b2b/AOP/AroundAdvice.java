package com.sony.spe.b2b.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AroundAdvice {
    @Around("execution(* com.sony.spe.b2b.service.*.*(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("Entering method:" + proceedingJoinPoint.getSignature().getName());
        Object result = proceedingJoinPoint.proceed();
        System.out.println("Exiting method:" + proceedingJoinPoint.getSignature().getName());
        return result;
    }
}
