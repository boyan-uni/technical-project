package com.boyan.interceptor;

import com.alibaba.druid.util.StringUtils;
import com.boyan.utils.BaseResponse;
import com.boyan.utils.JwtHelper;
import com.boyan.utils.ResponseCodeEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 登录状态保护的拦截器，在 SpringWebMvcConfig 中配置拦截路径：实现所有“/headline”路径下的拦截
 */
@Component
public class LoginProtectInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token) || jwtHelper.isExpiration(token)){
            BaseResponse baseResponse = BaseResponse.build(null, ResponseCodeEnum.LOGIN_EXPIRED);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(baseResponse);
            response.getWriter().print(json);
            // 拦截
            return false;
        }else{
            // 放行
            return true;
        }
    }
    /**
     * 拦截器
     *  - 根据拦截需求 @Override 相应的方法，添加拦截逻辑
     * preHandle
     * postHandle
     * afterCompletion
     */
}
