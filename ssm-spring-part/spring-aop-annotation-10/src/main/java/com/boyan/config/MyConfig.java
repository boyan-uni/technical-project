package com.boyan.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"com.boyan"})
@EnableAspectJAutoProxy // == <aop:aspectj-autoproxy /> 在配置类上开启 Aspectj注解支持!
public class MyConfig {
}
