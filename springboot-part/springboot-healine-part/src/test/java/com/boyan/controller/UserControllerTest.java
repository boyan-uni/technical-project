package com.boyan.controller;

import com.boyan.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserControllerTest {

    @Autowired
    UserController userController;

    /**
     * 测试 快速验证 用户名是否可用的 Restful 服务
     */
    @Test
    void checkUsername() {
        System.out.println(userController.checkUsername("boyan"));  // 这个用户名可用
    }

    /**
     * 测试 用户注册的 Restful 服务
     */
    @Test
    void register() {
        User user = new User("boyan", "123456", "boyan");
        System.out.println(userController.register(user));
    }


}