package com.boyan.test;

import com.boyan.Calculator;
import com.boyan.CalculatorLogImpl;
import com.boyan.ProxyFactory;
import org.junit.jupiter.api.Test;


public class TestProxy {
    @Test
    public void testDynamicProxy(){
        ProxyFactory factory = new ProxyFactory(new CalculatorLogImpl());
        Calculator proxy = (Calculator) factory.getProxy();
        proxy.div(1,1);
    }
}
