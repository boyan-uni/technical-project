package com.boyan.controller;

import com.boyan.pojo.Schedule;
import com.boyan.service.ScheduleService;
import com.boyan.pojo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 控制层只负责两件事：接收参数 ｜ 响应结果
 */

@Controller
@RequestMapping("schedule")
@Slf4j
@ResponseBody
public class ScheduleController {   // 在后端 @Controller层 设置允许非同源访问 restful服务

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/{pageSize}/{currentPage}")
    public R page(@PathVariable Integer pageSize, @PathVariable Integer currentPage)
    {
        // PageBean pageBean = scheduleService.queryPage(pageSize, currentPage);
        R r = scheduleService.queryPage(pageSize, currentPage);
        // sl4fj
        log.info("查询的数据为：{}",r);
        return R.ok(r);
    }

    @GetMapping
    public List<Schedule> listAll()
    {
        List<Schedule> list = scheduleService.list();
        return list;
    }

    @PostMapping
    public R insert(@RequestBody Schedule schedule)
    {
        scheduleService.insert(schedule);
        return R.ok(null);
    }

    @DeleteMapping("{id}")
    public R deleteById(@PathVariable Integer id)
    {
        scheduleService.deleteById(id);
        return R.ok(null);
    }

    @PutMapping
    public R update(@RequestBody Schedule schedule)
    {
        scheduleService.update(schedule);
        return R.ok(null);
    }
}
