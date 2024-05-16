package com.boyan.ioc_02;

public class UserService {

    private UserDao userDao;
    private String name;
    private int age;

    // 1. 基于构造函数的依赖注入（单个构造参数）- DI 配置
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    // 2. 基于构造函数的依赖注入（多个构造参数）- DI 配置
    public UserService(UserDao userDao, String name, int age) {
        this.userDao = userDao;
        this.name = name;
        this.age = age;
    }
}
