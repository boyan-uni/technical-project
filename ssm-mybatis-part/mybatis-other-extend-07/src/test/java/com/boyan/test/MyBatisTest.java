package com.boyan.test;

import com.boyan.mapper.EmployeeMapper;
import com.boyan.pojo.Employee07;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {

    private SqlSession sqlSession;
    @BeforeEach
    public void before() throws IOException{
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSession = sqlSessionFactory.openSession(true);
    }
    @AfterEach
    public void clean(){
        sqlSession.close();
    }

    @Test
    public void test_01(){
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);

        // 调用之前先设置分页（当前是第几页，每页显示多少个）
        PageHelper.startPage(1,2);
        // TODO. 注意：startPage 和 pageInfo 之间只能插 一个 查询语句, 否则分页失效 !!! 如果想再查，再写一遍这部分！
        List<Employee07> list = employeeMapper.queryList();
        // 将查询到的数据结果封装到一个 PageInfo 的分页实体类中
        PageInfo<Employee07> pageInfo = new PageInfo<>(list);

        // 接下来就可以从 pageInfo 中获取关于分页的数据了
        // 1）当前页的数据
        List<Employee07> list1 = pageInfo.getList();
        System.out.println("list1 = " + list1);
        // 2）获取总页数
        int pages = pageInfo.getPages();
        System.out.println("pages = " + pages);
        // 3）总条数
        long total = pageInfo.getTotal();
        int pageNum = pageInfo.getPageNum();
        int pageSize = pageInfo.getPageSize();
        System.out.println("total = " + total);
        System.out.println("pageNum = " + pageNum);
        System.out.println("pageSize = " + pageSize);
    }
}
