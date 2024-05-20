package com.boyan.service.impl;


import com.boyan.dao.StudentDao;
import com.boyan.pojo.Student;
import com.boyan.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;
    /**
     * 查询全部学员业务
     *
     * @return
     */
    @Override
    public List<Student> findAll() {
        List<Student> studentList =  studentDao.queryAll();

        return studentList;
    }
}
