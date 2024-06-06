package com.boyan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * 项目的配置类，把 controller handlerMapping handlerAdapter 加入 Spring IoC 容器
 */
@EnableWebMvc   // JSON 数据处理，必须使用此注解，因为它会加入 JSON 处理器
@Configuration
@ComponentScan("com.boyan.controller")
public class SpringMvcConfig implements WebMvcConfigurer {

    /*
    @EnableWebMvc 自动配置以下内容，不需要再自定义了！！！
     // handlerMapping
    @Bean
    public RequestMappingHandlerMapping handlerMapping(){
        return new RequestMappingHandlerMapping();
    }

    // handlerAdapter
    @Bean
    public RequestMappingHandlerAdapter handlerAdapter(){
        return new RequestMappingHandlerAdapter();
    }*/

}
