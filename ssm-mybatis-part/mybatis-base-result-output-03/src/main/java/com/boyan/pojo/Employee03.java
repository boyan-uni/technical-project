package com.boyan.pojo;

public class Employee03 {

    private Integer empId;  // auto increment Id 自增长

    private String empName;

    private Double empSalary;

    /**
     * TODO. 手动添加 包含无参构造函数!!! MyBatis 需要默认构造函数来实例化对象
     */
    public Employee03() {}

    public Employee03(String empName, Double empSalary) {
        this.empName = empName;
        this.empSalary = empSalary;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Double getEmpSalary() {
        return empSalary;
    }

    public void setEmpSalary(Double empSalary) {
        this.empSalary = empSalary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empSalary=" + empSalary +
                '}';
    }
}
