package com.boyan.mapper;

import com.boyan.pojo.Employee02;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 *    t_emp 表对应数据库 SQL 语句映射接口!
 *    接口只规定方法,参数和返回值!
 *    mapper.xml 中编写具体 SQL 语句!
 */
public interface EmployeeMapper {

    /**
     * 根据员工id查询员工数据方法
     * @param empId  员工id
     * @return 员工实体对象
     */
    Employee02 queryById(Integer empId);

    int insertEmployee(Employee02 employee02);  // 如果成功，返回值 == 1

    int updateEmployee(@Param("empId") Integer empId, @Param("empSalary") Double empSalary);

    int updateEmployeeByMap(Map<String, Object> paramMap);
}
