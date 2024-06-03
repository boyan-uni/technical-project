package com.boyan.test;

import com.boyan.mapper.EmployeeMapper;
import com.boyan.pojo.Employee02;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *      JUnit5 测试
 */
public class MybatisTest {

    private SqlSession session;

    /**
     * 在每个 @Test 方法之前执行 - 开启 SqlSession
     */
    @BeforeEach
    public void init() throws IOException {
        session = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml")).openSession(); // 浓缩成一条语句
    }

    /**
     * 在每个 @Test 方法之后执行 - 关闭 SqlSession
     */
    @AfterEach
    public void clear() {
        session.commit(); //提交事务 [DQL不需要,其他需要]
        session.close();  //关闭会话
    }

    /**
     * 单个 简单类型 参数
     * @throws IOException 在输入输出时可能出现的异常情况！
     */
    @Test
    public void test_01() throws IOException {
        // 1 获取 employeeMapper 对象
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        // 2 调用并执行 employeeMapper 中对应的方法的 sql 语句，如果有返回值，得到结果
        Employee02 employee02 = employeeMapper.queryById(1);
        // 输出
        System.out.println("employee = " + employee02);
    }

    /**
     * 单个 实体类类型 参数
     * @throws IOException 在输入输出时可能出现的异常情况！
     */
    @Test
    public void test_02() throws IOException {
        // 1 获取 employeeMapper 对象
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);

        // 2 调用并执行 employeeMapper 中对应的方法的 sql 语句，如果有返回值，得到结果
        int result = employeeMapper.insertEmployee(new Employee02("二狗子", 200.69));

        // 输出
        if (result == 1) {
            System.out.println("insert 执行成功，result = " + result);
        } else {
            System.out.println("insert 执行失败，result = " + result);
        }
    }

    /**
     * 零散的 简单类型 参数
     * @throws IOException 在输入输出时可能出现的异常情况！
     */
    @Test
    public void test_03() throws IOException {
        // 1 获取 employeeMapper 对象
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);

        // 2 调用并执行 employeeMapper 中对应的方法的 sql 语句，如果有返回值，得到结果
        int result = employeeMapper.updateEmployee(1,1234.00);

        // 输出
        if (result == 1) {
            System.out.println("update 执行成功，result = " + result);
        } else {
            System.out.println("update 执行失败，result = " + result);
        }
    }

    /**
     * Map类型 参数 - HashMap<String key,Object value> 通过往哈希表中存值传参
     * @throws IOException 在输入输出时可能出现的异常情况！
     */
    @Test
    public void test_04() throws IOException {
        // 1 获取 employeeMapper 对象
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);

        // 2 调用并执行 employeeMapper 中对应的方法的 sql 语句，如果有返回值，得到结果
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("empSalaryKey",999.99);
        paramMap.put("empIdKey",1);
        int result = employeeMapper.updateEmployeeByMap(paramMap);

        // 输出
        if (result == 1) {
            System.out.println("update 执行成功，result = " + result);
        } else {
            System.out.println("update 执行失败，result = " + result);
        }
    }
}
