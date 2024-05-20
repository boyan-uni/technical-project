package com.boyan.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Java配置类 完全替代 xml 配置文件
 *      1. 包扫描注解配置
 *      2. 引用外部的配置文件
 *      3. 声明第三方依赖的 bean 组件
 *
 *  步骤1. 添加 @Configuration 注解，代表是配置类
 *  步骤2. 实现上面 3 个功能的注解
 *          1. 单包扫描：@ComponentScan(value = "com.boyan")
 *             多包扫描：@ComponentScan(value = {"com.boyan.service", "com.boyan.dao"})
 *          2. 引用外部的配置文件：@PropertySource(value = "classpath:jdbc.properties")
 *          3. 声明第三方依赖的 bean 组件：@Bean
 *
 *   问题：如果项目中有多个配置类，如何使用多个配置类？
 *          1 - 在创建 IoC 容器时，new AnnotationConfigApplicationContext(JavaConfiguration1.class, JavaConfiguration2.class, ...)
 *          1 - 配置类汇总机制：使用 @Import(被汇总配置类1.class) 汇总所有配置类 到 一个配置类文件中，在创建配置类时，只需导入一个汇总好的即可。
 */
@ComponentScan(value = "com.boyan")
@PropertySource(value = "classpath:jdbc.properties")
@Import(JavaConfigurationPlus.class)
@Configuration
public class JavaConfiguration {

    @Value("${boyan.driverClassName}")
    private String driverClassName;
    @Value("${boyan.url}")
    private String url;
    @Value("${boyan.username}")
    private String username;
    @Value("${boyan.password}")
    private String password;

    /**
     * XML 中的 <bean></bean> 对应的 @Bean 注解 -> 一个方法函数
     *
     * 方法 - 返回值类型 == bean 组件的类型 / 其接口 或 父类
     * 方法 - 方法名 == bean 组件的 id
     * 在方法上加上 @Bean 注解 会真正让配置类的方法创建的组件 存储到 IoC 容器中
     *
     * 问题1: Bean Name 的问题
     *          1 - 默认：方法名
     *          2 - 指定：@Bean(value = "beanName") / @Bean(name = "beanName")：通过 value/name 属性起名字，覆盖默认方法
     * 问题2: Bean 周期方法
     *          1 - 依然用注解：@PostConstruct / @PreDestroy
     *          2 - @Bean 的属性 initMethod/destroyMethod，eg. @Bean(initMethod = "init", destroyMethod = "destroy")
     * 问题3: Bean 的作用域问题
     *          依然用 @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)，默认单例 SCOPE_SINGLETON
     * 问题4: 如何在一个 @Bean 方法中，引用 IoC 中的其他组件？
     *          1 - 直接调用被引用的组件的 @Bean 方法，返回值就是引用的组件（不推荐），要求被引用组件也被 @Bean 注解
     *          2 - 使用形参列表声明想要的组件类型，可以是 1/多个，从 IoC 容器中获取组件
     *              要求必须有对应类型的组件，如果没有 -> 抛出异常
     *                                    如果有多个 -> 可以用形参名称 = bean id 标识获取
     */
    @Bean(name = "dataSourceA")
    public DruidDataSource druidDataSourceA() {
        // 使用 Java 代码 实例化
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        return druidDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DruidDataSource dataSourceA) { // 多个，两个数据源：使用 bean id 唯一标识声明使用
        // 使用 Java 代码 实例化
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSourceA);
        return jdbcTemplate;
    }

    @Bean(name = "dataSourceB")
    public DruidDataSource druidDataSourceB() {
        // 使用 Java 代码 实例化
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        return druidDataSource;
    }

}
