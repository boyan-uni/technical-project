package com.boyan;

/**
 * 在每个方法中,输出传入的参数和计算后的返回结果!
 */
public class CalculatorLogImpl implements Calculator {

    @Override
    public int add(int i, int j) {

        System.out.println("参数是：" + i + "," + j);
        int result = i + j;
        System.out.println("方法内部 result = " + result);

        return result;
    }

    @Override
    public int sub(int i, int j) {

        System.out.println("参数是：" + i + "," + j);

        int result = i - j;

        System.out.println("方法内部 result = " + result);
        return result;
    }

    @Override
    public int mul(int i, int j) {

        System.out.println("参数是：" + i + "," + j);

        int result = i * j;

        System.out.println("方法内部 result = " + result);

        return result;
    }

    @Override
    public int div(int i, int j) {

        System.out.println("参数是：" + i + "," + j);

        int result = i / j;

        System.out.println("方法内部 result = " + result);

        return result;
    }
}
