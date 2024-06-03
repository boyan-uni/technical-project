package com.boyan.test;

import com.boyan.mapper.EmployeeMapper;
import com.boyan.pojo.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisTest {

    @Test
    public void testSelectEmployee() throws IOException {

        // 1.创建 SqlSessionFactory 对象
        // ①声明 Mybatis 全局配置文件的路径
        String mybatisConfigFilePath = "mybatis-config.xml";

        // ②以输入流的形式加载 Mybatis 配置文件
        InputStream inputStream = Resources.getResourceAsStream(mybatisConfigFilePath);

        // ③基于读取 Mybatis 配置文件的输入流创建 SqlSessionFactory 对象
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.使用 SqlSessionFactory 对象开启一个会话
        SqlSession session = sessionFactory.openSession();

        // 3.根据 EmployeeMapper 接口的 Class 对象获取 Mapper 接口类型的对象(动态代理技术)
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);

        // 4. 调用代理类方法既可以触发对应的 SQL 语句
        Employee employee = employeeMapper.selectEmployee(1);

        System.out.println("employee = " + employee);

        // 4.关闭 SqlSession
        session.commit(); //提交事务 [DQL不需要,其他需要]
        session.close();  //关闭会话

    }
}
