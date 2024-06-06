package com.boyan;

import com.boyan.config.SpringMvcConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * SpringMVC 提供的接口,是替代 web.xml 配置文件的方案,更方便实现完全注解方式 ssm 处理!
 *
 * TODO: Springmvc 框架会自动检查当前类的实现类,会自动加载 getRootConfigClasses / getServletConfigClasses 提供的配置类
 * TODO: getServletMappings 返回的地址 设置 DispatherServlet 对应处理的地址
 */

public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 指定service / mapper层的配置类
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    /**
     * 指定我们项目对应的 springmvc 的配置类
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { SpringMvcConfig.class };
    }

    /**
     * 设置 dispatcherServlet 的处理路径，即配置 springmvc 内部自带的 servlet 的访问地址！
     * 一般情况下为 / 代表处理所有请求!
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}