package com.boyan.advice;

import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class TxAdvice {

    @Before(value = "com.boyan.pointcut.MyPointCut.boyanSecondPointCut()")
    public void begin() {
        System.out.println("事务开启");
    }
    @AfterReturning(value = "com.boyan.pointcut.MyPointCut.boyanSecondPointCut()")
    public void commit() {
        System.out.println("事务提交");
    }
    @AfterThrowing(value = "com.boyan.pointcut.MyPointCut.boyanSecondPointCut()")
    public void rollback() {
        System.out.println("事务回滚");
    }

}
