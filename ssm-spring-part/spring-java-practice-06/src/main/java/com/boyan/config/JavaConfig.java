package com.boyan.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.boyan")
@PropertySource("classpath:jdbc.properties")
public class JavaConfig {

    @Value("${boyan.url}")
    private String url;
    @Value("${boyan.driverClassName}")
    private String driver;
    @Value("${boyan.username}")
    private String username;
    @Value("${boyan.password}")
    private String password;

    @Bean(destroyMethod = "close")
    public DruidDataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

}
