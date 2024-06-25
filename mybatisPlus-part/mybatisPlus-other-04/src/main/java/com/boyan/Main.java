package com.boyan;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@MapperScan(basePackages = "com.boyan.mapper")
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    /**
     * MyBatis-Plus 的 插件配置专区
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        // MP 的插件集合【分页插件、乐观锁插件... 都在这里直接添加就可以了】
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 添加 - 分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL)); // 指定 数据库类型
        // 返回插件集合
        return interceptor;
    }
}