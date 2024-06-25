package com.boyan.test;

import com.boyan.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringJUnitTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 测试功能点1 - 查询所有
     */
    @Test
    public void test01(){
        System.out.println(("----- selectAll method test ------"));
        userMapper.selectList(null).forEach(System.out::println);
    }

    /**
     * 测试功能点2 - 插入
     */
    @Test
    public void test02(){
        System.out.println(("----- insert method test ------"));
        int result = userMapper.insert(new com.boyan.pojo.User(null, "test01", "123456"));
        System.out.println(result);
    }

    /**
     * 测试功能点3 - 删除
     */
    @Test
    public void test03(){
        System.out.println(("----- delete method test ------"));
        int result = userMapper.deleteById(2128412673L);
        System.out.println(result);
    }

}
