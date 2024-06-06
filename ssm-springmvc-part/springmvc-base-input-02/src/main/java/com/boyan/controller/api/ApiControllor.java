package com.boyan.controller.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api")
public class ApiControllor {
    /**
     * TODO. 如果想要获取请求或者响应对象, 或者会话等, 可以直接在形参列表传入, 并且不分先后顺序!
     * TODO. 注意: 接收原生对象,并不影响参数接收!
     */
    @GetMapping("data")
    @ResponseBody
    public String api(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        String method = request.getMethod();
        System.out.println("method = " + method);
        return "method = " + method;
    }
}
