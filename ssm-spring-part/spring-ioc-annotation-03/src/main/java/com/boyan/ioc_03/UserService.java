package com.boyan.ioc_03;

import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserService {
    public void show() {
        System.out.println("ioc_04_show()");
    }
}
