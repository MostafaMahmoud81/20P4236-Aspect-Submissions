package com.example.demo.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

    @Before("execution(* com.example.demo.controllers.Controller.*(..))")
    public void beforeControllerMethods() {
        System.out.println("Aspect: Before Controller methods (Request will be handled)");
    }
}
