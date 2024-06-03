package com.boyan.mapper;

import com.boyan.pojo.Employee;

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
    Employee selectEmployee(Integer empId);

}
