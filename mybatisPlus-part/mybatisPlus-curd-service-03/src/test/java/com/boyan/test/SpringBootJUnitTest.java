package com.boyan.test;

import com.boyan.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringBootJUnitTest {

    @Autowired
    private UserService userService;

    /**
     * 测试 Service - Mapper - POJO - Database 连通性
     */
    @Test
    public void test() {
        System.out.println(userService.getById(2));
    }
}
