package com.boyan.test;

import com.boyan.ioc_01.XxxController;
import com.boyan.ioc_01.XxxDao;
import com.boyan.ioc_01.XxxService;
import com.boyan.ioc_02.JavaBean;
import com.boyan.ioc_03.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIoCTest {

    /**
     *
     * XML + @Annotation 配置
      */

    /**
     *
     * IoC_01：测试 Bean IoC 配置
     * - 组件配置：@Component @Controller @Service @Repository
     */
    @Test
    public void testIoc_01() {
        // 1. 创建 IoC 容器
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-01.xml");

        // 2. 获取 Bean
        System.out.println(applicationContext.getBean(XxxController.class));
        System.out.println(applicationContext.getBean("xxxController"));
        System.out.println(applicationContext.getBean(XxxService.class));
        System.out.println(applicationContext.getBean("xxxService"));
        System.out.println(applicationContext.getBean(XxxDao.class));
        System.out.println(applicationContext.getBean("xxxDao"));

        // 3. close IoC 容器
        applicationContext.close();
    }

    /**
     *
     * IoC_02：测试 Bean 配置 周期方法 & 作用域
     * - 周期方法 @PostConstruct @PreDestroy
     * - 作用域 @Scope(scopeName = ConfigrableBeanFactory.SCOPE_PROTOTYPE 多例 / SINGLETON 单例)
     */
    @Test
    public void testIoc_021_CycleMethod() {
        // 1. 创建 IoC 容器: IoC 容器调用所有 Bean 的初始化方法（如果有的话）
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-02.xml");

        // 2. 获取 Bean
        System.out.println(applicationContext.getBean(JavaBean.class));

        // 3. close IoC 容器：IoC 容器关闭之前调用所有 Bean 的销毁方法（如果有的话）
        applicationContext.close();
    }
    @Test
    public void testIoc_022_Scope() {
        // 1. 创建 IoC 容器
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-02.xml");

        // 2. 获取 Bean，测试逻辑：在组件类 @配置中修改 scopeName值，并重新运行
        JavaBean javaBean1 = applicationContext.getBean(JavaBean.class);
        JavaBean javaBean2 = applicationContext.getBean(JavaBean.class);
        System.out.println(javaBean1 == javaBean2);

        // 3. close IoC 容器
        applicationContext.close();
    }

    /**
     *
     * IoC_DI_03：测试 Bean 配置 DI 依赖注入
     */
    @Test
    public void testIoc_03_Di() {
        // 1. 创建 IoC 容器
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-03.xml");

        // 2. 获取 Bean
        UserController userController = applicationContext.getBean(UserController.class);
        userController.show();

        // 3. close IoC 容器
        applicationContext.close();
    }


    /**
     *
     * IoC_DI_04：测试 Bean 配置 DI 依赖注入
     */
    @Test
    public void testIoc_04_DiValue() {
        // 1. 创建 IoC 容器
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-04.xml");

        // 2. 获取 Bean
        com.boyan.ioc_04.JavaBean javaBean = applicationContext.getBean(com.boyan.ioc_04.JavaBean.class);
        System.out.println(javaBean.getName());
        System.out.println(javaBean.getPassword());

        // 3. close IoC 容器
        applicationContext.close();
    }
}



























