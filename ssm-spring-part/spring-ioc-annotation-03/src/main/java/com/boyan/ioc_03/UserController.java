package com.boyan.ioc_03;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller(value = "userController")
public class UserController {

    // @Autowired
    // @Qualifier(value = "userService") // 自动装配 引用类型为 userService 的 bean
    @Resource(name = "userService")
    private UserService userService;

    public void show() {
        userService.show();
    }
}
