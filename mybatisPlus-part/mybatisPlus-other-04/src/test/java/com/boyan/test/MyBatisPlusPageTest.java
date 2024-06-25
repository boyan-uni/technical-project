package com.boyan.test;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boyan.mapper.UserMapper;
import com.boyan.pojo.UserOthers;
import com.boyan.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MyBatisPlusPageTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    /**
     * 测试 - 01 - 分页插件的使用
     */
    @Test
    public void testPage() {
        // IPage -> Page(页码,页容量)
        Page<UserOthers> page = new Page<>(1, 5);
        // 调用 Mapper -> BaseMapper 中的方法
        userMapper.selectPage(page,null);

        // 结果 page最后也会被封装结果
        long current = page.getCurrent();                    // 页码
        System.out.println("current = " + current);
        long size = page.getSize();                          // 页容量
        System.out.println("size = " + size);
        List<UserOthers> userRecords = page.getRecords();    // 当前页的数据
        System.out.println("userRecords = " + userRecords);
        long total = page.getTotal();                        // 总条数
        System.out.println("total = " + total);
    }

    /**
     * 测试 - 02 - 自定义分页的使用
     */
    @Test
    public void testMyPage(){
        // IPage -> Page(页码,页容量)
        Page<UserOthers> page = new Page<>(1,5);
        // 调用 Mapper -> 自定义的方法
        userMapper.queryById(page,2);

        // 结果 page最后也会被封装结果
        long current = page.getCurrent();                    // 页码
        System.out.println("current = " + current);
        long size = page.getSize();                          // 页容量
        System.out.println("size = " + size);
        List<UserOthers> userRecords = page.getRecords();    // 当前页的数据
        System.out.println("userRecords = " + userRecords);
        long total = page.getTotal();                        // 总条数
        System.out.println("total = " + total);
    }
}
