package com.boyan.mapper;

import com.boyan.pojo.EmployeeSsm01;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
* @author boyan
* @description 针对表【t_emp】的数据库操作Mapper
* @createDate 2024-06-12 20:53:18
* @Entity com.boyan.pojo.TEmp
*/
@Mapper
public interface EmployeeMapper {
    // 查询所有员工
    List<EmployeeSsm01> queryList();
}
