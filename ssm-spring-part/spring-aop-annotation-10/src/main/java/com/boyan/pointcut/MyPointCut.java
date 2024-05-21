package com.boyan.pointcut;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// 只要和 Spring 功能有关的都要加入 IoC 容器
@Component
public class MyPointCut {
    @Pointcut(value = "execution(public int *..Calculator.sub(int,int))")
    public void boyanGlobalPointCut(){}

    @Pointcut(value = "execution(public int *..Calculator.add(int,int))")
    public void boyanSecondPointCut(){}

    @Pointcut(value = "execution(* *..*Service.*(..))")
    public void transactionPointCut(){}
}

