package com.boyan.service;

import com.boyan.pojo.EmployeeSsm01;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmployeeService {
    // 查询所有员工
    List<EmployeeSsm01> queryList();
}
