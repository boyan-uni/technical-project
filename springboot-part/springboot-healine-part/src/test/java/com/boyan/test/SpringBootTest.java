package com.boyan.test;

import com.boyan.utils.JwtHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.boot.test.context.SpringBootTest
public class SpringBootTest {

    @Autowired
    private JwtHelper jwtHelper;

    /**
     * 测试 jwt-token 功能
     */
    @Test
    public void test(){
        // 传入用户标识，生成 jwt-token
        String token = jwtHelper.createToken(1L);
        System.out.println("token = " + token);

        // 从传入的 jwt-token，解析出 用户标识
        int userId = jwtHelper.getUserId(token).intValue();
        System.out.println("userId = " + userId);

        // 校验 jwt-token 是否到期：false-未到期｜true-到期
        boolean expiration = jwtHelper.isExpiration(token);
        System.out.println("expiration = " + expiration);
    }

}