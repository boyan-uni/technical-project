package com.boyan.pojo;

import lombok.Data;

/**
 * @TableName t_emp
 */
@Data
public class EmployeeSsm01 {
    private Integer empId;
    private String empName;
    private Double empSalary;
}