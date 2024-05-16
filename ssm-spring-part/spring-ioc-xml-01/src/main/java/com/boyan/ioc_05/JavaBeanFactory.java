package com.boyan.ioc_05;

import org.springframework.beans.factory.FactoryBean;

public class JavaBeanFactory implements FactoryBean {

    // 通过配置文件 给 javaBean 的 name 属性赋值 的中间桥接
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 重写 FactoryBean 中的方法（一共 3 个）
    @Override
    public Object getObject() throws Exception {
        // getObject(): 使用自己的方式实例化对象就可以啦
        JavaBean javaBean = new JavaBean();
        javaBean.setName(name);
        return javaBean;
    }

    @Override
    public Class<?> getObjectType() {
        // getObjectType: 返回对象的类型
        return JavaBean.class;
    }
        // isSingleton(): 是否单例：默认单例，不用重写。如果是多例，需要重写。
}
