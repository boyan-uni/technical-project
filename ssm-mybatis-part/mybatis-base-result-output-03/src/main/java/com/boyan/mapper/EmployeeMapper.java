package com.boyan.mapper;

import com.boyan.pojo.Employee03;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

/**
 *    t_emp 表对应数据库 SQL 语句映射接口!
 *    接口只规定方法,参数和返回值!
 *    mapper.xml 中编写具体 SQL 语句!
 */
public interface EmployeeMapper {

    /**
     * 1 - DML output - int / long 直接接收返回值，无需指定 resultType 返回值类型；
     */
    int deleteById(Integer id);

    /**
     * 2 - 查询 - 单个简单类型 返回值
     */
    int selectEmpCount();               // 根据员工的 id 查询员工的姓名
    String queryNameById(Integer id);   // 根据员工的 id 查询员工的工资

    /**
     * 3 - 查询 - 单个实体类对象 返回值
     */
    Employee03 selectEmpById(Integer empId);

    /**
     * 4 - 查询 - Map<String, Object> 类型 返回值
     * 方法：返回工资最高的员工的姓名和他的工资
     */
    @MapKey("empName")
    Map<String,Object> selectEmpNameAndMaxSalary();

    /**
     * 5 - 查询 - List<Object> 类型 返回值
     */
    List<Employee03> selectAll();

    /**
     - 场景：主键回显（获取插入数据的主键）
     - 1 - 自增长类型主键 mysql auto_increment
     - 函数：实现员工插入，并返回新插入的员工实体的id
     */
    int insertEmployee(Employee03 employee);
}
