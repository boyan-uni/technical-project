package com.boyan.controller.requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 路径设置注解：
 *
 * 1. 精确地址【一个｜多个】 /user/login  {"address1","address2"}
 * 2. 支持模糊： * 任意一层字符串｜** 任意层任意字符串
 *            /user/*   ----> /user/a 可以，/user/任意字符串都可以访问
 *            /user/**  ----> /user  /user/a /user/a/b 都可以，/user/任意层任意字符串都可以访问，只要是以 /user 开头就行
 * 3. 在类｜方法上 添加 @RequestMapping 的区别
 *            类上：提取通用的访问地址
 *            方法上：具体的 handler 地址
 *            访问：类地址 / 方法地址 即可
 *
 * 4. 请求方式指定
 *    请求方式处理路径：客户端 -- http(get|post|put|delete) -- dispatcherServlet -- 具体 handler (XxxController 中自定义具体方法)
 *    默认情况：@RequestMapping("login") 只要地址正确，任何类型的请求方法都可以访问
 *    指定请求方式：@RequestMapping(value = "login", method = RequestMethod.POST)
 *    如果发送的请求 不符合请求方式：会出现 405 异常！
 *
 * 5. 注解进阶（只能用在方法上，类上是定义提取的通用 address，不管具体的方式指定）
 *    get：@GetMapping == @RequestMapping(value = "login", method = RequestMethod.GET)
 *    post：@PostMapping == @RequestMapping(value = "login", method = RequestMethod.POST)
 *    put：@PutMapping == @RequestMapping(value = "login", method = RequestMethod.PUT)
 *    delete：@DeleteMapping == @RequestMapping(value = "login", method = RequestMethod.DELETE)
 */

@Controller
@RequestMapping( "user")
public class UserController {
    @RequestMapping(value = "login",method = RequestMethod.POST) // method 设置只能用在方法上
    public void login() {
        System.out.println("login");
    }

    @RequestMapping("register")
    public void register() {
        System.out.println("register");
    }

}
