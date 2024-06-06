package com.boyan.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 项目的配置类，把 controller handlerMapping handlerAdapter 加入 Spring IoC 容器
 */
@EnableWebMvc   // JSON 数据处理，必须使用此注解，因为它会加入 JSON 处理器
@Configuration
@ComponentScan({"com.boyan.error","com.boyan.controller"})
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

    /**
     * 配置jsp对应的视图解析器
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //快速配置 jsp 模板语言对应的路径
        registry.jsp("/WEB-INF/views/",".jsp");
        // handler -> jsp
    }

    /**
     * 开启静态资源处理 <mvc:default-servlet-handler/>
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
