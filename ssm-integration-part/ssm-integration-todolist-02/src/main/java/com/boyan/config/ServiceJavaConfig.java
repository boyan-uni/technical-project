package com.boyan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 业务层配置类 - 需要配置 service ioc aop tx
 *
 * 1 - service
 * 2 - aop - 开启aspect注解的支持实现[@Before @After @AfterReturning @AfterThrowing @Around @Aspect切面 @Order优先级]
 * 3 - tx - 1）对应的事务管理器实现[TransactionManager - DataSource|Hibernate|Jpa] 2）开启事务注解的支持以开启[@Transactional]
 */
@Configuration
@EnableAspectJAutoProxy             // 开启aop
@EnableTransactionManagement        // 开启txManager 事务管理器
@ComponentScan("com.boyan.service")
public class ServiceJavaConfig {

    // 配置事务管理器
    @Bean
    public TransactionManager transactionManager(DataSource dataSource){ // 在 mybatis 配置部分
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
        return dataSourceTransactionManager;
    }
}
