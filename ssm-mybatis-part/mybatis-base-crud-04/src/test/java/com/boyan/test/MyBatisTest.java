package com.boyan.test;

import com.boyan.mapper.UserMapper;
import com.boyan.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class MyBatisTest {

    private SqlSession session;
    // junit会在每一个@Test方法前执行@BeforeEach方法

    @BeforeEach
    public void init() throws IOException {
        session = new SqlSessionFactoryBuilder()
                .build(
                        Resources.getResourceAsStream("mybatis-config.xml"))
                .openSession(true);
    }

    @Test
    public void createTest() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        UserMapper userMapper = session.getMapper(UserMapper.class);
        userMapper.insert(user);
        System.out.println(user);
    }

    @Test
    public void updateTest() {
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.selectById(2);
        user.setUsername("root");
        user.setPassword("111111");
        userMapper.update(user);
        user = userMapper.selectById(1);
        System.out.println(user);
    }

    @Test
    public void deleteTest() {
        UserMapper userMapper = session.getMapper(UserMapper.class);
        userMapper.delete(1);
        User user = userMapper.selectById(1);
        System.out.println("user = " + user);
    }

    @Test
    public void selectByIdTest() {
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.selectById(1);
        System.out.println("user = " + user);
    }

    @Test
    public void selectAllTest() {
        UserMapper userMapper = session.getMapper(UserMapper.class);
        List<User> userList = userMapper.selectAll();
        System.out.println("userList = " + userList);
    }

    // junit会在每一个@Test方法后执行@@AfterEach方法
    @AfterEach
    public void clear() {
        // session.commit();
        session.close();
    }
}
