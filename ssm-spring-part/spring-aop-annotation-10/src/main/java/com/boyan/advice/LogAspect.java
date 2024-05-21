package com.boyan.advice;

import com.boyan.pointcut.MyPointCut;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// @Aspect表示这个类是一个切面类
@Aspect
// @Component注解保证这个切面类能够放入IOC容器
@Component
@Order(10)
public class LogAspect {

    /**
     * TODO: 切点表达式
     *  固定语法 execution(1 2 3.4.5(6))
     *  1. 访问修饰符 public / private
     *  2. 返回值类型 int / void / String
     *     如果不考虑访问修饰符和返回值！这两位整合成一起写 *
     *     如果不考虑，两个必须一起不考虑！不能出现 * String
     *  3. 包的位置
     *      具体包：com.boyan.service.impl
     *      单层模糊：com.boyan.service.*
     *      多层模糊：com..impl
     *              .. 代表任意层的模糊，但细节：.. 不能开头
     *              找所有 impl包：com..impl / *..impl✅
     *  4. 类的名称
     *       具体类：CalculatorPureImpl
     *       模糊：*
     *       部分模糊：*Impl
     *  5. 方法名 语法和类名一致
     *  6. (6) 形参数列表
     *         没有参数 ()
     *         有具体参数 (String)
     *         模糊参数 (..) 有没有参数都可以，有多个也可以！
     *         部分模糊 (String..) String 后面有没有无所谓
     *                 (..int)    最后一个参数是 int
     *                 (String..int)
     */

    /**
     * TODO：切点表达式的提取和复用
     *  同一类中提取并引用、不同类中引用、创建一个专门存储切点的类（推荐）
     */
    @Pointcut(value = "execution(public int com.boyan.CalculatorPureImpl.add(int,int))")
    public void pointCut() {
    }

    // @Before注解：声明当前方法是前置通知方法
    // value属性：指定切入点表达式，由切入点表达式控制当前通知方法要作用在哪一个目标方法上
    // @Before(value = "execution(public int com.boyan.CalculatorPureImpl.add(int,int))")
    // @Before("pointCut()")
    @Before(value = "com.boyan.pointcut.MyPointCut.boyanSecondPointCut()")
    public void printLogBeforeCore() {
        System.out.println("[AOP前置通知] 方法开始了");
    }

    // @AfterReturning(value = "execution(public int com.boyan.CalculatorPureImpl.add(int,int))")
    // @AfterReturning(value = "pointCut()")
    @AfterReturning(value = "com.boyan.pointcut.MyPointCut.boyanSecondPointCut()")
    public void printLogAfterSuccess() {
        System.out.println("[AOP返回通知] 方法成功返回了");
    }

    // @AfterThrowing(value = "execution(public int com.boyan.CalculatorPureImpl.add(int,int))")
    // @AfterThrowing(value = "pointCut()")
    @AfterThrowing(value = "com.boyan.pointcut.MyPointCut.boyanSecondPointCut()")
    public void printLogAfterException() {
        System.out.println("[AOP异常通知] 方法抛异常了");
    }

    // @After(value = "execution(public int com.boyan.CalculatorPureImpl.add(int,int))")
    // @After(value = "pointCut()")
    @After(value = "com.boyan.pointcut.MyPointCut.boyanSecondPointCut()")
    public void printLogFinallyEnd() {
        System.out.println("[AOP后置通知] 方法最终结束了");
    }

}
