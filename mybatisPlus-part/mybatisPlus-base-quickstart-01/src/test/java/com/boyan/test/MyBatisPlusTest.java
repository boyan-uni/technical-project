package com.boyan.test;

import com.boyan.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyBatisPlusTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 测试 MyBatis-Plus quickstart 运行
     */
    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        userMapper.selectList(null).forEach(System.out::println);
    }
}
