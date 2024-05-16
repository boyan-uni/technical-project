package com.boyan.dao;

import com.boyan.pojo.Student;

import java.util.List;

//接口
public interface StudentDao {

    /**
     * 查询全部学生数据
     * @return
     */
    List<Student> queryAll();
}