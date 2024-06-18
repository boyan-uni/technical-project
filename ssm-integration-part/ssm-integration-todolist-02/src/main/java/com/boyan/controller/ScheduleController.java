package com.boyan.controller;

import com.boyan.pojo.Schedule;
import com.boyan.service.ScheduleService;
import com.boyan.pojo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 控制层只负责两件事：接收参数 ｜ 响应结果
 */
@CrossOrigin
@RestController // == @Controller + @ResponseBody
@RequestMapping("schedule")
@Slf4j
public class ScheduleController {   // 在后端 @Controller层 设置允许非同源访问 restful服务

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/{pageSize}/{currentPage}")
    public R page(@PathVariable(name = "pageSize") Integer pageSize, @PathVariable(name = "currentPage") Integer currentPage)
    {
        R r = scheduleService.queryPage(pageSize, currentPage);
        // sl4fj
        // log.info("响应结果：{}",r);
        return r;
    }

    @GetMapping
    public R listAll()
    {
        R r = scheduleService.list();
        // sl4fj
        // log.info("响应结果：{}",r);
        return r;
    }

    @PostMapping
    public R insert(@Validated @RequestBody Schedule schedule, BindingResult result)
    {
        if (result.hasErrors())
        {
            return R.fail("参数为空，不能保存");
        }
        R r = scheduleService.insert(schedule);
        // sl4fj
        // log.info("响应结果：{}",r);
        return r;
    }

    @DeleteMapping("{id}")
    public R deleteById(@PathVariable Integer id)
    {
        R r = scheduleService.deleteById(id);
        // sl4fj
        // log.info("响应结果：{}",r);
        return r;
    }

    @PutMapping
    public R update(@Validated @RequestBody Schedule schedule, BindingResult result)
    {
        if (result.hasErrors())
        {
            return R.fail("参数为空，不能更新");
        }
        R r = scheduleService.update(schedule);
        // sl4fj
        // log.info("响应结果：{}",r);
        return r;
    }
}
