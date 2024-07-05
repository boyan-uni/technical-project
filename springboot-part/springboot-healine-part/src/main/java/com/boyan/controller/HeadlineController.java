package com.boyan.controller;

import com.boyan.service.HeadlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("headline")
@CrossOrigin
public class HeadlineController {

    /**
     * Headline - 服务
     * - 分页浏览
     * - 根据标题关键字查询新闻
     * - 查看新闻详情
     * - 新闻的修改
     * - 新闻的删除
     */

    @Autowired
    private HeadlineService headlineService;

}
