package com.boyan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.boyan.mapper")
@SpringBootApplication
public class Main {
    // 主程序入口
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}