package com.boyan.test;

import com.boyan.mapper.EmployeeMapper;
import com.boyan.pojo.Employee03;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        // session = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml")).openSession(); // 浓缩成一条语句
        session = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml")).openSession(true);
    }

    /**
     * 在每个 @Test 方法之后执行 - 关闭 SqlSession
     */
    @AfterEach
    public void clear() {
        // session.commit(); //提交事务 [DQL不需要,其他需要，DML语句必须要]
        session.close();  //关闭会话
    }

    /**
     * 1 - DML - int / long
     */
    @Test
    public void test_01(){
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        // 增删改
        int result = employeeMapper.deleteById(100);
        Assertions.assertEquals(0,0);
        System.out.println("如预期，删除失败，返回值0：test_01 result = " + result);
    }

    /**
     * 2 - 查询 - 单个简单类型 返回值
     */
    @Test
    public void test_02(){
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        int count = employeeMapper.selectEmpCount();
        System.out.println("表中数据项个数返回值：test_02 count = " + count);
    }

    /**
     * 3 - 查询 - 单个实体类对象 返回值
     */
    @Test
    public void test_03(){
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Employee03 employee = employeeMapper.selectEmpById(1);
        System.out.println(employee);
    }

    /**
     * 4 - 查询 - Map<String, Object> 类型 返回值
     * 方法：返回工资最高的员工的姓名和他的工资
     */
    @Test
    public void test_04(){
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        // Map<Key,Value>
        Map<String, Object> resultMap = employeeMapper.selectEmpNameAndMaxSalary();
        // Set<Key>
        Set<Map.Entry<String, Object>> entrySet = resultMap.entrySet();
        // for-each 取值
        for (Map.Entry<String, Object> entry : entrySet) {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key + ", " + value);
        }
    }

    /**
     * 5 - 查询 - List<Object> 类型 返回值
     */
    @Test
    public void test_05(){
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        // List<Object>
        List<Employee03> employeeList = employeeMapper.selectAll();
        // for-each 取值
        for (Employee03 employee:employeeList) {
            System.out.println(employee);
        }
    }

    /**
     * 场景：主键回显（获取插入数据的主键）
     * 1 - 自增长类型主键 mysql auto_increment
     * 函数：员工插入，返回id
     */
    @Test
    public void test_06(){
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Employee03 employee = new Employee03();
        employee.setEmpName("john");
        employee.setEmpSalary(666.66);
        employeeMapper.insertEmployee(employee);
        System.out.println(employee.getEmpId());
    }



}