package com.boyan.controller;

import com.boyan.mapper.UserMapper;
import com.boyan.pojo.UserBoot;
import com.boyan.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserService userService;


    @GetMapping("test1")
    public String testMvc()
    {
        UserBoot userBoot = new UserBoot();
        userBoot.setUserName("杨过");
        userBoot.setPassword("123456");
        return userBoot.toString();
    }

    @GetMapping("test2")
    public UserBoot testDruid(){
        String sql = "select * from user where id = ? ; ";
        UserBoot userBoot = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(UserBoot.class), 2);
        log.info("查询的user数据为:{}", userBoot.toString());
        return userBoot;
    }

    @GetMapping("test3")
    public List<UserBoot> testMyBatisMapper()
    {
        List<UserBoot> userList = userService.queryAll();
        log.info("查询的user数据为:{}",userList);
        return userList;
    }



}
