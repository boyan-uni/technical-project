package com.boyan.ioc_04;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JavaBean {

    @Value("boyan")
    private String name;

    @Value("${boyan.password:root}")
    private String password;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
