package com.boyan.service;

import com.boyan.pojo.Student;

import java.util.List;

//接口
public interface StudentService {

    /**
     * 查询全部学生数据
     * @return
     */
    List<Student> findAll();
}

