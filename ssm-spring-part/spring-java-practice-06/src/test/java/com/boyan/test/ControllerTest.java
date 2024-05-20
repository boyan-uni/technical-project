package com.boyan.test;

import com.boyan.config.JavaConfig;
import com.boyan.controller.StudentController;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ControllerTest {
    @Test
    public  void testRun(){

        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(JavaConfig.class);

        StudentController studentController = applicationContext.getBean(StudentController.class);

        studentController.findAll();

    }
}
