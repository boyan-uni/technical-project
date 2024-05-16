package com.boyan.test;

import com.boyan.ioc_03.HappyComponent;
import com.boyan.ioc_04.JavaBean2;
import com.boyan.ioc_05.JavaBean;
import com.boyan.ioc_05.JavaBeanFactory;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * projectName: com.boyan.test
 *
 * @author Boyan
 * description: 实验3. ioc 容器创建和读取组件的测试类
 */

public class SpringIoCTest {

    /**
     * 讲解 如何创建 ioc 容器并且读取配置文件
     * - 创建容器，选择合适的容器创建即可：ApplicationContext -> ClassPathXMLApplicationContext
     */
    @Test
    public void createIoCContainer() {
        // 方法一 * 最推荐
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-bean-03.xml");
        // 传入一个或多个配置文件，位置：resources/spring-bean-03.xml

        // 方法二：先实例化，再指定配置文件，最后刷新容器触发Bean实例化动作 [springmvc源码和contextLoadListener源码方式]
        ClassPathXmlApplicationContext context2 = new ClassPathXmlApplicationContext();
        context2.setConfigLocation("spring-bean-03.xml");   // 外部配置文件的设置
        context2.refresh();                                 // 调用 ioc 和 di 的流程
    }

    /**
     * 讲解 如何在 ioc 容器中获取组件 Bean 对象
     */
    @Test
    public void getBeanFromIoC() {
        // 1. 创建 IoC Container 对象
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-bean-03.xml");

        // 2. 读取 IoC Container 的 Bean 组件
        // - 方法1. 通过 Bean 的 id 获取（不推荐，需要强制类型转换）
        HappyComponent happyComponent1 = (HappyComponent) context.getBean("happyComponent");
        // - 方法2. 通过 Bean 的 id 和 类型 获取（仍然需要写name，有点麻烦）
        HappyComponent happyComponent2 = context.getBean("happyComponent", HappyComponent.class);
        // - 方法3. 通过 Bean 的 类型 获取（最简化）
        HappyComponent happyComponent3 = context.getBean(HappyComponent.class);
        // - ⚠️注意：第三种可能出现问题：根据 Bean 的类型获取，如果容器中存在多个相同类型的 Bean，则获取失败；
        //          会抛出异常：org.springframework.beans.factory.NoUniqueBeanDefinitionException
        // - ⚠️注意：IoC 的 bean 配置一定是实现类，但是可以在使用时根据接口类型获取值
        //          只要 "getBean(类型); instanceOf ioc容器的类型 == true“ 即可；
        //          eg. 接口类型 happyComponent3 = context.getBean(接口类型.class); 是可以的！

        // 3. 测试
        happyComponent3.doWork();
        System.out.println(happyComponent1 == happyComponent2);
        System.out.println(happyComponent1 == happyComponent3);
    }

    /**
     * 实验4.1 测试 Bean 周期方法 配置
     */
    @Test
    public void testBeanLifeCycle() {
        // 1.创建 IoC 容器：容器创建时，会自动调用 Bean 的初始化方法
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-bean-04.xml");

        // 2.正常结束 IoC 容器：容器关闭时，会自动调用 Bean 的销毁方法
        context.close();
    }

    /**
     * 实验4.2 测试 Bean 作用域 配置
     */
    @Test
    public void testBeanScope() {
        // 创建 IoC 容器：容器创建时，会自动调用 Bean 的初始化方法
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-bean-04.xml");

        // scope = “singleten” 单例模式：IoC容器中始终是这一个对象
        JavaBean2 bean1 = context.getBean("javaBean2",JavaBean2.class);
        JavaBean2 bean2 = context.getBean("javaBean2",JavaBean2.class);
        System.out.println(bean1 == bean2);

        // scope = “prototype” 多例模式：外部每获取一次创建一个对象
        JavaBean2 bean3 = context.getBean("javaBean3",JavaBean2.class);
        JavaBean2 bean4 = context.getBean("javaBean3",JavaBean2.class);
        System.out.println(bean3 != bean4);

        // 正常结束 IoC 容器：容器关闭时，会自动调用 Bean 的销毁方法
        context.close();
    }

    /**
     * 实验5. 读取使用 factoryBean 工厂配置的 Bean 组件对象
     */
    @Test
    public void testFactoryBean() {
        // 创建 IoC 容器：容器创建时，会自动调用 Bean 的初始化方法
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-bean-05.xml");

        // 读取组件
        // 1 - 读取 javaBean 对象
        JavaBean javaBean = context.getBean("javaBean",JavaBean.class);
        System.out.println(javaBean);
        System.out.println("javaBean.getName() = " + javaBean.getName());

        // 2 - 读取 javaBeanFactory 对象
        Object javaBeanFactory = context.getBean("&javaBean");
        System.out.println(javaBeanFactory);

        // 正常结束 IoC 容器：容器关闭时，会自动调用 Bean 的销毁方法
        context.close();
    }
}
