package com.boyan.ioc_04;

public class JavaBean {

    /**
     * 必须是 public，必须是 void 返回值，必须是无参数！
     * 命名随意！
     * 初始化方法 -> 在函数中编写初始化业务逻辑即可
     */
    public void init()
    {
        // 初始化逻辑
        System.out.println("JavaBean.init");
    }

    /**
     * 必须是 public，必须是 void 返回值，必须是无参数！
     * 命名随意！
     * 销毁方法 -> 在函数中编写销毁业务逻辑即可
     */
    public void destroy()
    {
        // 释放资源逻辑
        System.out.println("JavaBean.destroy");
    }
}
