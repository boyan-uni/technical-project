package com.boyan.controller;

import com.boyan.pojo.EmployeeSsm01;
import com.boyan.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("find")
    public List<EmployeeSsm01> queryList()
    {
        List<EmployeeSsm01> employees = employeeService.queryList();
        log.info("员工数据:{}",employees);
        return employees;
    }
}
