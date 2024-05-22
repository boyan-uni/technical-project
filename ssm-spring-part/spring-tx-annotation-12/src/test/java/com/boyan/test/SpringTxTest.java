package com.boyan.test;

import com.boyan.service.StudentService;
import com.boyan.service.TopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.FileNotFoundException;

@SpringJUnitConfig(classes = {com.boyan.config.JavaConfig.class})
public class SpringTxTest {
    @Autowired
    private StudentService studentService;

    /**
     * 测试 - 事务：正常执行方法
     */
    @Test
    public void testTxSuccess()
    {
        studentService.changeInfo1();    // update id=1 的数据项的 name，age属性值；
    }

    /**
     * 测试 - 事务：执行失败回滚方法
     */
    @Test
    public void testTxRollback()
    {
        studentService.changeInfo2();    // 方法中间会报一个错，应该执行失败并回滚事务
    }

    /**
     * 测试 - 事务：超时回滚
     */
    @Test
    public void testTxTimeout()
    {
        studentService.changeInfo3();
    }

    /**
     * 测试 - 事务：超时回滚
     */
    @Test
    public void testTxRollbackFor() throws FileNotFoundException {
        studentService.changeInfo4();
    }

    /**
     * 测试 - 事务：传播行为
     */
    @Autowired
    private TopService topService;

    @Test
    public void  testTxPropagation() throws FileNotFoundException {
        topService.topService();
        // 整合成一个事务，执行失败回滚
    }

}
