package com.boyan.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class TxAroundAdvice {
    @Around(value = "com.boyan.pointcut.MyPointCut.transactionPointCut()")
    public Object transaction(ProceedingJoinPoint joinPoint) {
        // 保证目标方法被执行即可
        Object[] args = joinPoint.getArgs();
        Object result = null;

        try {
            // 开启事务
            System.out.println("开启事务");
            // 执行目标方法
            result = joinPoint.proceed(args);
            // 提交事务
            System.out.println("提交事务");
        } catch (Throwable throwable) {
            // 回滚事务
            System.out.println("回滚事务");
            throw new RuntimeException(throwable);
        } finally {

        }
        return result;
    }
}
