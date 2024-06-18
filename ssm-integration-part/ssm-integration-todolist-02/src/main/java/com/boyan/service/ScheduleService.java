package com.boyan.service;

import com.boyan.pojo.Schedule;
import com.boyan.pojo.R;

import java.util.List;

public interface ScheduleService {

    // 分页查询
    R queryPage(int pageSize, int currentPage);
    // 不分页获取 list
    R list();

    R insert(Schedule schedule);

    R deleteById(Integer id);

    R update(Schedule schedule);

}
