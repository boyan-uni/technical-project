package com.boyan.ioc_02;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


// @Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)   单例，默认值 - 作用域 只有单例管理 destroy
// @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)   多例，二选一 - 作用域 多例组件太多不管理 destroy
@Component
public class JavaBean {

    // 周期方法：对应 XML 配置的 init-method / destroy-method
    @PostConstruct
    public void init() {
        // 初始化逻辑
        System.out.println("JavaBean init");
    }
    @PreDestroy
    public void destroy() {
        // 销毁逻辑，eg.释放资源
        System.out.println("JavaBean destroy");
    }

    public boolean getName() {
        return false;
    }
}
