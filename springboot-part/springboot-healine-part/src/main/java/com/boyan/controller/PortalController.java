package com.boyan.controller;

import com.boyan.pojo.Type;
import com.boyan.service.HeadlineService;
import com.boyan.service.TypeService;
import com.boyan.pojo.vo.PortalVo;
import com.boyan.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 按照模块划分
 * 当前是：首页模块
 */
@RestController
@RequestMapping("portal")
@CrossOrigin
public class PortalController {

    @Autowired
    private TypeService typeService;
    @Autowired
    private HeadlineService headlineService;

    /**
     * 查询首页分类
     * url地址：portal/findAllTypes
     * 请求方式：get
     * 请求参数：无
     * 响应数据：
     * 成功
     * {
     *    "code":"200",
     *    "message":"OK"
     *    "data":{
     *             [
     *                 {
     *                     "tid":"1",
     *                     "tname":"新闻"
     *                 },
     *                 ...
     *             ]
     *     }
     * }
     */
    @GetMapping("findAllTypes")
    public BaseResponse findAllTypes()
    {
        // 直接调用业务层IService，查询全部数据，无需到BaseMapper持久层
        List<Type> types = typeService.list();
        return BaseResponse.ok(types);
    }

    // todo. 分页查询首页头条信息
    /**
     * url地址：portal/findNewsPage
     * 请求方式：post
     * 请求参数:
     * ```JSON
     * {
     *     "keyWords":"马斯克", // 搜索标题关键字
     *     "type":0,           // 新闻类型
     *     "pageNum":1,        // 页码数
     *     "pageSize":10     // 页大小
     * }
     * ```
     *
     * 响应数据：
     * 成功
     * {
     *    "code":"200",
     *    "message":"success"
     *    "data":{
     *       "pageInfo":{
     *         "pageData":[
     *           {
     *             "hid":"1",                     // 新闻id
     *             "title":"尚硅谷宣布 ... ...",   // 新闻标题
     *             "type":"1",                    // 新闻所属类别编号
     *             "pageViews":"40",              // 新闻浏览量
     *             "pastHours":"3" ,              // 发布时间已过小时数
     *             "publisher":"1"                // 发布用户ID
     *         },
     *         ... ...
     *         ],
     *       "pageNum":1,    // 页码数
     *       "pageSize":10,  // 页大小
     *       "totalPage":20, // 总页数
     *       "totalSize":200 // 总记录数
     *     }
     *   }
     * }
     */
    @PostMapping("findNewsPage")
    public BaseResponse findNewsPage(@RequestBody PortalVo portalVo)
    {
        // 调用 Headline Service，因为查询的实体本身是 Headline
        return headlineService.findNewsPage(portalVo);
    }

    /**
     * url地址：portal/showHeadlineDetail
     *
     * 请求方式：post
     * 请求参数: hid=1 param形成参数
     *
     * 响应数据：
     * 成功
     * {
     *     "code":"200",
     *     "message":"success",
     *     "data":{
     *         "headline":{
     *             "hid":"1",                     // 新闻id
     *             "title":"马斯克宣布 ... ...",    // 新闻标题
     *             "article":"... ..."            // 新闻正文
     *             "type":"1",                    // 新闻所属类别编号
     *             "typeName":"科技",              // 新闻所属类别
     *             "pageViews":"40",              // 新闻浏览量
     *             "pastHours":"3" ,              // 发布时间已过小时数
     *             "publisher":"1" ,              // 发布用户ID
     *             "author":"张三"                 // 新闻作者
     *         }
     *     }
     * }
     */
    @PostMapping("showHeadlineDetail")
    public BaseResponse showHeadlineDetail(Integer hid)
    {
        // 调用业务层，查询数据
        return headlineService.showHeadlineDetail(hid);
    }

}
