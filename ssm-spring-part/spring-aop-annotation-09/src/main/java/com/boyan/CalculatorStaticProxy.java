package com.boyan;

/**
 * 静态代理：代码写死，不灵活
 */
public class CalculatorStaticProxy implements Calculator {

    // 将被代理的目标对象声明为成员变量
    private Calculator target;

    public CalculatorStaticProxy(Calculator target) {
        this.target = target;
    }

    @Override
    public int add(int i, int j) {

        // 附加功能由代理类中的代理方法来实现
        System.out.println("参数是：" + i + "," + j);

        // 通过目标对象来实现核心业务逻辑
        int addResult = target.add(i, j);

        System.out.println("方法内部 result = " + addResult);

        return addResult;
    }

    /**
     * @param i
     * @param j
     * @return
     */
    @Override
    public int sub(int i, int j) {
        // 附加功能由代理类中的代理方法来实现
        System.out.println("参数是：" + i + "," + j);

        // 通过目标对象来实现核心业务逻辑
        int subResult = target.sub(i, j);

        System.out.println("方法内部 result = " + subResult);

        return subResult;
    }

    /**
     * @param i
     * @param j
     * @return
     */
    @Override
    public int mul(int i, int j) {
        // 附加功能由代理类中的代理方法来实现
        System.out.println("参数是：" + i + "," + j);

        // 通过目标对象来实现核心业务逻辑
        int mulResult = target.mul(i, j);

        System.out.println("方法内部 result = " + mulResult);

        return mulResult;
    }

    /**
     * @param i
     * @param j
     * @return
     */
    @Override
    public int div(int i, int j) {
        // 附加功能由代理类中的代理方法来实现
        System.out.println("参数是：" + i + "," + j);

        // 通过目标对象来实现核心业务逻辑
        int divResult = target.div(i, j);

        System.out.println("方法内部 result = " + divResult);

        return divResult;
    }
}
