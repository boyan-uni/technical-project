package com.boyan.test;

import com.boyan.Calculator;
import com.boyan.CalculatorCglibImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

//@SpringJUnitConfig(locations = "classpath:spring-aop.xml")
@SpringJUnitConfig(classes = {com.boyan.config.MyConfig.class})
public class AopTest {

    @Autowired
    private Calculator calculator;

    @Test
    public void testCalculator(){
        calculator.add(1,1);
    }

    @Autowired
    private CalculatorCglibImpl calculatorCglib;

    @Test
    public void testCalculatorCglib(){
        calculatorCglib.add(1,1);
    }

}
