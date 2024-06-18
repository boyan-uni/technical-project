package com.boyan.service;

import com.boyan.pojo.Schedule;
import com.boyan.pojo.R;

import java.util.List;

public interface ScheduleService {

    R queryPage(int pageSize, int currentPage);
    List<Schedule> list();

    void insert(Schedule schedule);

    void deleteById(Integer id);

    void update(Schedule schedule);

}
