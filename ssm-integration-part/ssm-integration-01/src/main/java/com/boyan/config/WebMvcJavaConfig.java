package com.boyan.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 控制层的配置类 - controller springmvc
 * 需要配置哪些：
 * - @Configuration
 * - @EnableWebMvc
 * - @ComponentScan【/controller、/exceptionHandler】
 * - 导入 implements WebMvcConfigurer
 *
 * - 其中⬆️@EnableWebMvc 自动给配置：handlerMapping handlerAdapter Json转换器...
 * - 配置默认的servlet处理静态资源: springmvc需要配置，springboot不需要配置会自己包含；
 * - 配置视图解析器
 * - 配置拦截器
 * - ... 还有好多，但其实现在我们是暂时用不到的
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.boyan.controller") // exceptionHandler 如果有全局异常处理器，也需要扫描，属于 springmvc
public class WebMvcJavaConfig implements WebMvcConfigurer {

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
     * 配置开启静态资源处理 <mvc:default-servlet-handler/>
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

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
     * 配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截器
        // registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/","/index");
    }
}
