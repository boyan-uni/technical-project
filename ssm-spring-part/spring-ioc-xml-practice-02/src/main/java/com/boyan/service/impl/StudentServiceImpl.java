package com.boyan.service.impl;
import com.boyan.dao.StudentDao;
import com.boyan.pojo.Student;
import com.boyan.service.StudentService;

import java.util.List;

//实现类
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    /**
     * 查询全部学员业务
     * @return
     */
    @Override
    public List<Student> findAll() {

        List<Student> studentList =  studentDao.queryAll();

        return studentList;
    }
}
