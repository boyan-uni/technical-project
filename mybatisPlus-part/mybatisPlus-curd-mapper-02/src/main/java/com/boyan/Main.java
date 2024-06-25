package com.boyan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 直接通过 Mapper 测试 User 的 insert｜delete 操作
 * 测试：通过 @SpringBootTest 注解单元测试
 * 方法：
 *  1 - insert(Entity user)
 *  2 - deleteById(Serializable id)
 *  3 - delete(Wrapper<T> queryWrapper) 测试条件构造器的初步使用，后面还会完整的使用
 */
@MapperScan("com.boyan.mapper")
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        // 启动入口
        SpringApplication.run(Main.class, args);
    }
}