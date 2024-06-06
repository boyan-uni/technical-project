package com.boyan.controller;

import com.boyan.pojo.User;
import org.springframework.web.bind.annotation.*;

/**
 * TODO. Restful 测试，用 Postman 测试通过指定每个请求的：【请求方式】+ url 来调用，会加深理解！！！
 */

@RestController
@RequestMapping("user")
public class UserController {

    /**
     * 分页查询
     * 用户数据分页展示功能（条件：page 页数 默认1，size 每页数量 默认 10）
     * 【Param】
     * url：http://localhost:8080/user | http://localhost:8080/user?page=1&size=10
     */
    @GetMapping
    public Object page(@RequestParam(required = false,defaultValue = "1") int page,
                                @RequestParam(required = false,defaultValue = "10") int size)
    {
        System.out.println("分页查询业务：" + "page = " + page+ "size = " + size);
        return "分页查询业务：" + "page = " + page+ "size = " + size;
    }

    /**
     * 用户添加
     * 保存用户功能
     * 【JSON】通过 Postman 测试
     * url：http://localhost:8080/user 【POST】Json结构体：填写User信息
     */
    @PostMapping
    public Object user(@RequestBody User user)
    {
        System.out.println("保存用户业务：user = " + user);
        return "{'status':'ok'}";
    }

    /**
     * 用户详情
     * 根据用户id查询用户详情功能
     * 【Path】id 动态路径
     * url：http://localhost:8080/user/{id}
     */
    @GetMapping("/{id}")
    public Object user(@PathVariable int id)
    {
        System.out.println("查询用户详情业务：id = " + id);
        return "查询用户详情业务：id = " + id;
    }

    /**
     * 用户更新
     * 根据用户id更新用户数据功能(只传入User，id包含在User里)
     * 【JSON】通过 Postman 测试
     * url：http://localhost:8080/user 【PUT】Json结构体：填写User信息
     * ⚠️ 同一个url 不同的 请求方式，具体访问的 handler 不同！
     */
    @PutMapping
    public Object update(@RequestBody User user)
    {
        System.out.println("更新用户业务：user = " + user);
        return "更新用户业务：user = " + user;
    }

    /**
     * 用户删除
     * 根据用户id删除用户数据功能
     * 【Path】id 动态路径
     * url：http://localhost:8080/user/{id} 【DELETE】
     */
    @DeleteMapping("{id}")
    public Object delete(@PathVariable int id)
    {
        System.out.println("删除用户业务：id = " + id);
        return "删除用户业务：id = " + id;
    }

    /**
     * 条件模糊
     * 多条件模糊查询用户功能（条件：keyword 模糊关键字(String任意字符串），page 页数 默认1，size 每页数量 默认 10）
     * 【Param】
     * url：http://localhost:8080/user/search?keyword=haha&page=2&size=11 【GET】
     */
    @GetMapping("search")
    public Object search(@RequestParam(required = false) String keyword,
                         @RequestParam(required = false,defaultValue = "1") int page,
                         @RequestParam(required = false,defaultValue = "10") int size)
    {
        System.out.println("条件模糊查询业务：keyword = " + keyword + "page = " + page+ "size = " + size);
        return "条件模糊查询业务：keyword = " + keyword + "page = " + page+ "size= " + size;
    }

}
