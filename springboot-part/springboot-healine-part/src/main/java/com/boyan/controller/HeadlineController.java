package com.boyan.controller;

import com.boyan.pojo.Headline;
import com.boyan.pojo.vo.HeadlineInsertVo;
import com.boyan.service.HeadlineService;
import com.boyan.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("headline")
@CrossOrigin
public class HeadlineController {

    @Autowired
    private HeadlineService headlineService;

    // todo. 头条发布实现

    /**
     * url地址：headline/publish
     * 请求方式：post
     * 请求头: token
     * 请求参数: JSON   // 两个都有
     * {
     *     "title":"尚硅谷宣布 ... ...",   // 文章标题
     *     "article":"... ...",          // 文章内容
     *     "type":"1"                    // 文章类别
     * }
     * 响应数据：
     * 未登录
     * {
     *     "code":"504",
     *     "message":"loginExpired",
     *     "data":{}
     * }
     * 成功
     * {
     *     "code":"200",
     *     "message":"success",
     *     "data":{}
     * }
     * 实现步骤:
     *  1. token 获取 userId [无需校验,拦截器会校验]
     *  2. 封装 headline 数据
     *  3. 插入数据即可
     */
    @PostMapping("publish")
    public Result publish(@RequestHeader String token, @RequestBody HeadlineInsertVo headlineInsertVo) {
        // 在执行这个方法前会调用拦截器通过 token 校验当前 user 的登录情况的，所以到这里了一定是已经通过校验了，是已登录的状态
        Result result = headlineService.publish(token, headlineInsertVo);
        return result;
    }

    // todo. headline 修改页面 头条信息回显
    /**
     * url地址：headline/findHeadlineByHid
     * 请求方式：post
     * 请求参数: hid=1 param形成参数
     * 请求头: token【拦截器会自己获取，不需要在 @Controller 层接收】
     * 响应数据：
     * 成功
     * {
     *     "code":"200",
     *     "message":"success",
     *     "data":{
     *         "headline":{
     *             "hid":"1",
     *             "title":"马斯克宣布",
     *             "article":"... ... ",
     *             "type":"2"
     *         }
     *     }
     * }
     */
    @PostMapping("findHeadlineByHid")
    public Result findHeadlineByHid(Integer hid) {
        return headlineService.findHeadlineByHid(hid);
    }

    // todo. headline 实现修改行为
    /**
     * url地址：headline/update
     * 请求方式：post
     * 请求头: token【拦截器会自己获取，不需要在 @Controller 层接收】
     * 请求参数: JSON
     * {
     *     "hid":"1",
     *     "title":"尚硅谷宣布 ... ...",
     *     "article":"... ...",
     *     "type":"2"
     * }
     * 响应数据：
     * 成功
     * {
     *     "code":"200",
     *     "message":"success",
     *     "data":{}
     * }
     */
    @PostMapping("update")
    public Result updateHeadline(@RequestBody Headline headline) {
        Result result = headlineService.updateHeadline(headline);
        return result;
    }

    // todo. headline 实现删除行为【逻辑删除 is_deleted 字段由0-1，整个过程由 MP 框架实现】
    /**
     * url地址：headline/removeByHid
     * 请求方式：post
     * 请求头: token【拦截器会自己获取，不需要在 @Controller 层接收】
     * 请求参数: hid=1 param形成参数
     * 响应数据：
     * 成功
     * {
     *     "code":"200",
     *     "message":"success",
     *     "data":{}
     * }
     */
    @PostMapping("removeByHid")
    public Result removeById(Integer hid) {
        boolean flag = headlineService.removeById(hid);
        System.out.println(flag);
        return Result.ok(null);
    }


}
