package com.boyan.test;

import com.boyan.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

//@SpringJUnitConfig(locations = {"classpath:spring-context.xml"})  // 指定配置文件xml
@SpringJUnitConfig(value = {com.boyan.config.JavaConfig.class})     // 指定配置类
public class SpringIoCTest {

    @Autowired
    private A a;

    @Test
    public void test()
    {
        a.show();
    }
}
