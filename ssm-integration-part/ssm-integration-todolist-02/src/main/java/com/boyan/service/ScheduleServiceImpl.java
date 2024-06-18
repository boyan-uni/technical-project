package com.boyan.service;

import com.boyan.mapper.ScheduleMapper;
import com.boyan.pojo.Schedule;
import com.boyan.pojo.PageBean;
import com.boyan.pojo.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ScheduleServiceImpl implements ScheduleService{

    @Resource
    private ScheduleMapper scheduleMapper;

    /**
     * 分页、查询、分页数据装配
     *
     * @param pageSize
     * @param currentPage
     * @return
     */
    @Override
    public R queryPage(int pageSize, int currentPage) {
        //1.设置分页参数
        PageHelper.startPage(currentPage,pageSize); // sql 语句后自动追加 limit
        //2.数据库查询
        List<Schedule> scheduleList = scheduleMapper.list();
        //3.结果获取
        PageInfo<Schedule> pageInfo = new PageInfo<>(scheduleList);
        //4.pageBean封装
        PageBean<Schedule> pageBean = new PageBean<>(currentPage,pageSize,pageInfo.getTotal(),pageInfo.getList());

        R ok = R.ok(pageBean);

        log.info("分页查询结果:{}",pageBean);
        return ok;
    }

    @Override
    public List<Schedule> list() {
        return scheduleMapper.list();
    }

    /**
     * @param schedule
     */
    @Override
    public void insert(Schedule schedule) {
        scheduleMapper.insert(schedule);
    }

    /**
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        scheduleMapper.deleteById(id);
    }

    /**
     * @param schedule
     */
    @Override
    public void update(Schedule schedule) {
        scheduleMapper.update(schedule);
    }
}
