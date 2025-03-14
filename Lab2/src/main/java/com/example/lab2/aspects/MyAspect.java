package com.example.lab2.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class MyAspect {

    @Before("execution(* com.example.lab2.User.UserController.*(..))")
    public void beforeControllerMethods(JoinPoint joinPoint) {
        System.out.println("Aspect: Before Controller methods (Request will be handled)");
        System.out.println("Method: " + joinPoint.getSignature().getName());
    }
}
