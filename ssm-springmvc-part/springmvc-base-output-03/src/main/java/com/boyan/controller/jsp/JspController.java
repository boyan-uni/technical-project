package com.boyan.controller.jsp;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 快速返回一个 JSP 页面
 */
@Controller
@RequestMapping("jsp")
// @ResponseBody
public class JspController {

    /**
     * 跳转到提交文件页面  /index
     *
     * TODO. 快速查找视图
     * 1. 方法的返回值是 String 字符串类型
     * 2. 不能添加 @ResponseBody：因为添加后代表直接返回字符串给浏览器，不找 view ，也不走 view 视图解析器
     * 3. 返回值，对应 JSP 页面的名称即可
     *
     * <property name="prefix" value="/WEB-INF/views/"/>
     *  + 逻辑视图名 +
     * <property name="suffix" value=".jsp"/>
     */
    @GetMapping("index")
    public String jumpJsp(HttpServletRequest request){
        request.setAttribute("msg","request data!!");
        return "index";
    }

    /**
     * TODO. 转发到   /index 路径
     *
     * 1. 方法的返回值是 String 字符串类型
     * 2. 不能添加 @ResponseBody，对应 JSP 页面的名称即可
     * 3. 返回字符串前面：forword:/转发到的目标地址
     *
     * ⚠️ 转发：只能转发到项目下的资源跳转
     * @return
     */
    @RequestMapping("/forward")
    public String forward() {
        return "forward:/jsp/index";
    }

    /**
     * TODO. 重定向到 项目内界面：/index 路径
     * 1. 方法的返回值是 String 字符串类型
     * 2. 不能添加 @ResponseBody，对应 JSP 页面的名称即可
     * 3. 返回字符串前面：redirect:/重定向到的目标地址
     *
     * ⚠️ 重定向：项目内外的地址都可以！
     * @return
     */
    @RequestMapping("/redirect")
    public String redirect() {
        return "redirect:/jsp/index";
    }

    /**
     * TODO. 重定向到 项目外页面：eg. 百度网页路径
     * http://localhost:8080/jsp/redirect-baidu
     * @return
     */
    @RequestMapping("/redirect-baidu")
    public String redirectBaidu() {
        return "redirect:http://www.baidu.com";
    }

    /**
     * 路径细节⚠️注意：【使用 springmvc 路径语法时】转发和重定向到【项目下资源】 路径都是相同，都不需要添加项目根路径！填写项目下路径即可！
     */
}
