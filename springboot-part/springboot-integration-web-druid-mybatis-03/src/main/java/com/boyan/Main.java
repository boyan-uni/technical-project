package com.boyan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.boyan.mapper") // mapper 接口扫描配置
public class Main {
    public static void main(String[] args) {
        // Boot 运行入口
        SpringApplication.run(Main.class, args);
    }
}