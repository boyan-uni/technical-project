package com.boyan.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class SpringIoCInitiator extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 配置 - root 容器配置类
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{DataSourceJavaConfig.class, MapperJavaConfig.class, ServiceJavaConfig.class};
    }

    /**
     * 配置 - web 容器配置类
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebMvcJavaConfig.class};
    }

    /**
     * 配置 - DispatcherServlet 的映射
     * 一般是 "/"
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
