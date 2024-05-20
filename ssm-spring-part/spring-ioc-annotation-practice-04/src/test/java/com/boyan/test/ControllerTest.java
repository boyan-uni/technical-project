package com.boyan.test;

import com.boyan.controller.StudentController;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ControllerTest {
    @Test
    public void testRun() {
        // 1. 创建IOC容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-bean-01.xml");
        // 2. 获取IOC容器中的bean
        StudentController studentController = context.getBean(StudentController.class);
        studentController.findAll();
        // 3. 关闭IOC容器
        context.close();
    }
}
