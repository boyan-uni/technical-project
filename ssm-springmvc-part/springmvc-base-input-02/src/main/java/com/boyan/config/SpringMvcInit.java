package com.boyan.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * SpringMVC 初始化类
 */
public class SpringMvcInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    /**
     * springmvc 需要组件的配置类
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcConfig.class};
    }

    /**
     * Servlet DispatcherServlet 的映射路径
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
