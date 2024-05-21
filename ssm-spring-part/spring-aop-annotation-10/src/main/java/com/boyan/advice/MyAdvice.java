package com.boyan.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;

/**
 * description: 定义四个增强方法，获取目标方法的信息，返回值，异常对象
 *
 * 1. 定义方法 - 增强代码
 * 2. 使用注解指定对应的位置
 * 3. 配置切点表达式选中方法
 * 4. 切面和IoC 的配置
 * 5. 开启 aspectj 注解的支持
 *
 * TODO：增强方法中获取目标方法信息
 *  1. 全部增强方法中，获取目标方法的信息（方法名，参数，访问）
 *     - (JoinPoint jointPoint) jointPoint 包含目标方法的信息 import org.aspectj.lang.JoinPoint;
 *  2. 返回的结果 - @AfterReturning
 *     1 - (JoinPoint jointPoint, Object result) result接收返回的结果
 *     2 - @AfterReturning(value = "execution(* com..impl.*.*(..))",returning = "result") 注解中指定形参名称
 *  3. 异常的信息 - @AfterThrowing
 *    1 - (JoinPoint jointPoint, Throwable e) e接收异常对象
 *    2 - @AfterThrowing(value = "execution(* com..impl.*.*(..))",throwing = "e") 注解中指定形参名称
 */
@Component
@Aspect
public class MyAdvice {
    @Before("execution(public int com.boyan.Calculator.add(int,int))")
    public void before(JoinPoint jointPoint) {
        // 1. 获取方法所属类的信息（固定方法，记就行）
        String simpleName = jointPoint.getTarget().getClass().getSimpleName();
        // 2. 获取方法名称
        int modifiers = jointPoint.getSignature().getModifiers();
        Modifier.toString(modifiers);
        String name = jointPoint.getSignature().getName();
        // 3. 获取参数列表
        Object[] args = jointPoint.getArgs();
    }
    @AfterReturning(value = "execution(public int com.boyan.Calculator.add(int,int))",returning = "result")
    public void afterReturning(JoinPoint jointPoint, Object result) {  // 获取返回结果


    }

    @After("execution(public int com.boyan.Calculator.add(int,int))")
    public void after(JoinPoint jointPoint) {

    }
    @AfterThrowing(value = "execution(public int com.boyan.Calculator.add(int,int))",throwing = "e")
    public void afterThrowing(JoinPoint jointPoint, Throwable e) {  // 获取异常信息

    }
}
