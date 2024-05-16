package com.boyan.ioc_03;

public class HappyComponent {

    //默认包含无参数构造函数（可以不写省略掉）
    public HappyComponent() {}

    public void doWork() {
        System.out.println("HappyComponent.doWork");
    }
}
