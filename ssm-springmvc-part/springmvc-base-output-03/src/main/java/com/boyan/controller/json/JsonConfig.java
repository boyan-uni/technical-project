package com.boyan.controller.json;

import com.boyan.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO. Handler：返回 JSON 类型的数据
 */
//@Controller
@RequestMapping("json")
//@ResponseBody
@RestController
public class JsonConfig {

    // 实体类对象 User
    @RequestMapping("data-user")
    public User dataUser(){
        User user = new User();
        user.setName("Anna");
        user.setAge(18);
        return user;
    }   // user -> handlerAdapter -> json -> @ResponseBody -> json直接返回【前后端分离模式】

    // 集合 List<Object>
    @RequestMapping("data-userlist")
    public List<User> dataUserList(){

        User user = new User();
        user.setName("Anna");
        user.setAge(18);

        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        return users;
    }

}
