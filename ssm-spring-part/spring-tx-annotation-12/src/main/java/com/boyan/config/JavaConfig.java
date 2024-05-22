package com.boyan.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"com.boyan"})
@PropertySource(value = {"classpath:jdbc.properties"})
@EnableTransactionManagement    // 开启事务管理
// @EnableAspectJAutoProxy      // 开启 Aspectj 注解支持
public class JavaConfig {

    @Value("${boyan.url}")
    private String url;
    @Value("${boyan.driverClassName}")
    private String driverClassName;
    @Value("${boyan.username}")
    private String username;
    @Value("${boyan.password}")
    private String password;

    @Bean
    // durid 连接池
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    // jdbcTemplate
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

    /**
     * 装配事务管理实现对象
     * @param dataSource
     * @return
     */
    @Bean
    public TransactionManager transactionManager(DataSource dataSource){
        // 拆分步骤代码
        // 内部要进行事务的操作，基于连接池，选择具体持久层框架对应的事务管理器，并放入 IoC 容器中！
        // DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        // 1 - 需要连接池对象
        // dataSourceTransactionManager.setDataSource(dataSource);
        // 2 - 放入 IoC 容器
        // return dataSourceTransactionManager;
        // 等价于⬇️
        return new DataSourceTransactionManager(dataSource);
    }
}
