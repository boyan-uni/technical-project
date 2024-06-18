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

    @Resource // == @Autowired + @Qualifier
    private ScheduleMapper scheduleMapper;

    /**
     * 分页查询，具体步骤：分页、查询、分页数据装配
     *
     * @param pageSize int 每页显示条数
     * @param currentPage int 当前页数
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
        log.info("分页查询结果:{}",pageBean);

        // 返回结果
        R r = R.ok(pageBean);
        return r;
    }

    @Override
    public R list() {
        R r = R.ok(scheduleMapper.list());
        return r;
    }

    /**
     * @param schedule
     */
    @Override
    public R insert(Schedule schedule) {
        int response = scheduleMapper.insert(schedule);
        if (response == 1) {
            return R.ok(null);
        } else {
            return R.fail(null);
        }

    }

    /**
     * @param id
     */
    @Override
    public R deleteById(Integer id) {
        int response = scheduleMapper.deleteById(id);
        if (response == 1) {
            return R.ok(null);
        } else {
            return R.fail(null);
        }
    }

    /**
     * @param schedule
     */
    @Override
    public R update(Schedule schedule) {
        int response = scheduleMapper.update(schedule);
        if (response == 1) {
            return R.ok(null);
        } else {
            return R.fail(null);
        }
    }
}
