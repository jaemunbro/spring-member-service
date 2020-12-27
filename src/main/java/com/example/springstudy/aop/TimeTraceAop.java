package com.example.springstudy.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


// Component를 쓰거나 SpringConfig에 추가할 수 잇는데, Aop는 일반적이지 않으므로 Config에 추가하기를 권장
@Aspect
@Component
public class TimeTraceAop {
    //적용될 package 하위로 지정 ( 클래스 명 등 지정 가능)
    @Around("execution(* com.example.springstudy..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("start: " + joinPoint.toString());

        try {
            Object result = joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");

        }
    }
}

