package com.boyan.dao.impl;

import com.boyan.dao.StudentDao;
import com.boyan.pojo.Student;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

//实现类
public class StudentDaoImpl implements StudentDao {

    // JdbcTemplate
    private JdbcTemplate jdbcTemplate;
    // 注入 JdbcTemplate 对象
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 直接数据库操作：查询全部学生数据
     * @return
     */
    @Override
    public List<Student> queryAll() {

        String sql = "select id , name , age , gender , class as classes from students ;";

        /*
          query可以返回集合!
          BeanPropertyRowMapper就是封装好RowMapper的实现,要求属性名和列名相同即可
         */
        List<Student> studentList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Student.class));

        return studentList;
    }
}
