package com.boyan.service;

import com.boyan.mapper.EmployeeMapper;
import com.boyan.pojo.EmployeeSsm01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * @return
     */
    @Override
    public List<EmployeeSsm01> queryList() {
        return employeeMapper.queryList();
    }
}
