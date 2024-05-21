package com.boyan;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CalculatorCglibImpl {


    public int add(int i, int j) {

        int result = i + j;

        return result;
    }


    public int sub(int i, int j) {

        int result = i - j;

        return result;
    }


    public int mul(int i, int j) {

        int result = i * j;

        return result;
    }


    public int div(int i, int j) {

        int result = i / j;

        return result;
    }
}

