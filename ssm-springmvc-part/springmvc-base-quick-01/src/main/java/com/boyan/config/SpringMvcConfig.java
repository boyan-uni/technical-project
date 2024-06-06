package com.boyan.config;

//TODO: SpringMVC对应组件的配置类 [声明SpringMVC需要的组件信息]
//TODO: 导入handlerMapping和handlerAdapter的三种方式

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * 1. 自动导入handlerMapping和handlerAdapter [推荐]
 * 2. 可以不添加,springmvc会检查是否配置handlerMapping和handlerAdapter,没有配置默认加载
 * 3. 使用@Bean方式配置handlerMapper和handlerAdapter
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.boyan.controller")
public class SpringMvcConfig implements WebMvcConfigurer {
    //WebMvcConfigurer springMvc 进行组件配置的规范, 配置组件, 提供各种方法! 前期可以实现
    @Bean
    public HandlerMapping handlerMapping(){
        return new RequestMappingHandlerMapping();
    }

    @Bean
    public HandlerAdapter handlerAdapter(){
        return new RequestMappingHandlerAdapter();
    }

}
