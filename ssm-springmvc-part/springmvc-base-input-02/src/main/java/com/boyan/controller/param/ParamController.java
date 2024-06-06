package com.boyan.controller.param;

import com.boyan.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 讲解：如何接收前端 Param 类型的参数
 */
@Controller
@RequestMapping("param")
public class ParamController {

    /**
     * 直接接收参数   /param/data1?name=root&age=18
     *
     * 要求在形参列表填写对应参数的名称即可：请求参数名 = 形参参数名！
     * 1. 名称相同
     * 2. 可以不传递，不会报错
     */
    @RequestMapping("data1")
    @ResponseBody
    public String data1(String name, int age)
    {
        String result = "name = " + name + " age = " + age;
        System.out.println(result);
        return result;
    }

    /**
     * 注解指定参数   /param/data2?account=root&page=1    (其中 account 必须传递，page 可以不传递)
     *
     * @RequestParam(value = "name", required = false, defaultValue = "root") -> 只能在形参列表，作用：1）指定请求参数名 2）是否必须传递 3）非必需传递，并设置默认值
     * - 注解指定名称可以和参数名称不一样，以 @RequestParam(value="") 指定名称为准。
     */
    @RequestMapping("data2")
    @ResponseBody
    public String data2(@RequestParam("account") String username,
                        @RequestParam(required = false, defaultValue = "1") int page){
        String result = "username = " + username + " page = " + page;
        System.out.println(result);
        return result;
    }


    /**
     * 特殊值
     *
     * 1. 一变量名，多值：key=1&key=2    /param/data3?hbs=玩&hbs=学习
     *    - 直接用同名的集合接收值，加 @RequestParam 注解即可（可指定其他名称）
     *
     * 2. 使用实体类型对象接值            /param/data4?name=二狗子&age=18
     *    - 直接用实体类型对象接收值
     *    1. 属性名 == 参数名！！！
     *    2. 没有办法使用@RequestParam注解
     *    3. 默认值：可以直接在User类里写死，传入的就不会生效
     */
    @RequestMapping("data3")
    @ResponseBody
    public String data3(@RequestParam List<String> hbs){
        System.out.println(hbs);
        return "ok";
    }
    @RequestMapping("data4")
    @ResponseBody
    public String data4(User user){
        System.out.println(user);
        return user.toString();
    }
}
