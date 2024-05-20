package com.boyan;

import com.boyan.config.JavaConfiguration;
import com.boyan.ioc_01.StudentController;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringIoCTest {
    @Test
    public void test() {
        // step1. 创建 IOC 容器 Java 配置类方法创建
        //      * 方式1：
        //      *   1. 创建 IOC 容器：new AnnotationConfigApplicationContext();
        //      *   2. 添加配置类：addConfigClass(JavaConfiguration.class);
        //      *   3. 刷新容器：refresh(); 一定要刷新才会生效
        //      * 方式2：new AnnotationConfigApplicationContext(JavaConfiguration.class);
        //
        //      * 情况1. 单一配置类：new AnnotationConfigApplicationContext(JavaConfiguration.class);
        //      * 情况2. 多个配置类：new AnnotationConfigApplicationContext(JavaConfiguration.class, JavaConfiguration2.class, ...);
        //      * 结构更清晰的是将所有的配置类通过 @Import 放在一个配置类中，然后只导入这一个汇总的配置类；
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(JavaConfiguration.class);
        // step2. 获取 IOC 容器中的 bean
        StudentController studentController = annotationConfigApplicationContext.getBean(StudentController.class);
        studentController.show();
        // step3. close IOC 容器
        annotationConfigApplicationContext.close();
    }
}
