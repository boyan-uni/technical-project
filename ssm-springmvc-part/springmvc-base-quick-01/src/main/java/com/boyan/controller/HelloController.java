package com.boyan.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    //handlers

    /**
     * handler就是controller内部的具体方法
     * @RequestMapping("/springmvc/hello") 就是用来向handlerMapping中注册的方法注解!
     * @ResponseBody 代表向浏览器直接返回数据!
     */
    @RequestMapping("/")
    @ResponseBody
    public String hello(){
        System.out.println("HelloController.hello");
        return "hello springmvc!!";
    }

    @PostConstruct
    public void init() {
        System.out.println("HelloController initialized");
    }


}

