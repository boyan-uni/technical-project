package com.boyan.mapper;

import com.boyan.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    /**
     * 为了测试 sql动态语句 的一些方法 - query
     */

    List<Employee> query(@Param("name") String name, @Param("salary") Double salary);
    void updateEmployeeDynamic(Employee employee);
    List<Employee> selectEmployeeByConditionByTrim(Employee employee);

    /**
     * 批量操作：查询query 删除delete 插入insert 更新update
     */
    List<Employee> queryBatch(@Param("ids") List<Integer> ids);
    int deleteBatch(@Param("ids")List<Integer> ids);
    int insertBatch(@Param("employeeList")List<Employee> employeeList);
    int updateBatch(@Param("employeeList")List<Employee> employeeList);

}
