package com.boyan.service;

import com.boyan.mapper.UserMapper;
import com.boyan.pojo.UserBoot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserBoot> queryAll() {
        List<UserBoot> users = userMapper.queryAll();
        log.info("查询全部数据:{}",users);
        return users;
    }

    @Transactional
    public void update(){

    }
}
