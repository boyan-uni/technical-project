package com.boyan.controller;

import com.boyan.pojo.DataSource1;
import com.boyan.pojo.DataSource2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("data")
public class DataSourceController {

    @Autowired
    private DataSource1 dataSource1;

    @Autowired
    private DataSource2 dataSource2;

    @GetMapping("show1")
    public String getDataSource1() {
        System.out.println("dataSource1 Properties = " + dataSource1);
        return dataSource1.toString();
    }

    @GetMapping("show2")
    public String getDataSource2() {
        System.out.println("dataSource2 Properties = " + dataSource2);
        return dataSource2.toString();
    }

}
