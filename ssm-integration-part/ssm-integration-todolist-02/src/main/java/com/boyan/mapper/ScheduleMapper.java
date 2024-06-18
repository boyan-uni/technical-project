package com.boyan.mapper;

import com.boyan.pojo.Schedule;

import java.util.List;

/**
* @author boyan
* &#064; description  针对表【schedule】的数据库操作 Mapper
* &#064; createDate  2024-06-17 00:23:53
* &#064; Entity  com.boyan.pojo.Schedule
 */

public interface ScheduleMapper {

    List<Schedule> list();

    int insert(Schedule schedule);

    int deleteById(Integer id);

    int update(Schedule schedule);

}
