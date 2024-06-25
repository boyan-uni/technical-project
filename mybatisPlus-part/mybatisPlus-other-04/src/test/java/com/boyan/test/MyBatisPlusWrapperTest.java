package com.boyan.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.boyan.mapper.UserMapper;
import com.boyan.pojo.UserOthers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MyBatisPlusWrapperTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * Wrapper 基本用法测试 - QueryWrapper 体验
     */
    @Test
    public void test1_1(){
        // 创建 Wrapper 并组装条件
        QueryWrapper<UserOthers> queryWrapper = new QueryWrapper<>();
        // 1 - [拼接写法]
        queryWrapper.eq("username", "root");   // 添加等于条件
        queryWrapper.ne("password", "222222"); // 添加不等于条件

        // 2 - 另一种写法 [链式写法]
        queryWrapper.eq("username","root").ne("password", "222222");

        // 正常使用 - 按条件查询user 并返回List<User>
        List<UserOthers> users = userMapper.selectList(queryWrapper);
        System.out.println("条件查询结果：" + users);
    }

    /**
     * QueryWrapper 实战测试
     * 练习按需编写条件组装
     * 1. 按照按照 id 升序排列用户；
     */
    @Test
    public void test1_2(){
        // 创建 Wrapper
        QueryWrapper<UserOthers> queryWrapper = new QueryWrapper<>();
        // 组装条件
        queryWrapper.orderByAsc("id");
        // 正常使用 - 按条件查询user 并返回List<User>
        List<UserOthers> users = userMapper.selectList(queryWrapper);
        System.out.println("条件查询结果：" + users);
    }

    /**
     * 2. 删除 name 为 null 的用户（按条件删除也可以用 wrapper）
     */
    @Test
    public void test1_3(){
        // 创建 Wrapper
        QueryWrapper<UserOthers> queryWrapper = new QueryWrapper<>();
        // 【1】
        queryWrapper.isNull("name");
        int result = userMapper.delete(queryWrapper);
        // 返回删除结果 int delete(Wrapper<T> queryWrapper);
        System.out.println("删除结果：" + result);
    }

    /**
     * 3. 修改：将年龄大于 20，且 用户名中包含 a，且邮箱为 null 的用户，的信息修改为传入值
     *    UPDATE user SET age=? email=? WHERE username LIKE ? AND age > ? OR email IS NULL
     *    - 【隐式】AND 拼接 默认
     *    - 【显示】OR 拼接 需要： 条件A.or().条件B
     */
    @Test
    public void test1_4(){
        // 创建 Wrapper
        QueryWrapper<UserOthers> queryWrapper = new QueryWrapper<>();
        // 组装条件
        queryWrapper.like("username", "a")
                .gt("age", 20)
                .or()
                .isNull("email");
        // 创建 User
        UserOthers user = new UserOthers(1, "test04", "123456");
        // 修改 并 返回结果
        int result = userMapper.update(user, queryWrapper);
        System.out.println("修改结果：" + result);
    }

    /**
     * 4. 根据传入参数，实现 动态条件判断 sql【把 sql -> java代码在写，简单了很多啊啊啊啊啊啊啊】
     */
    @Test
    public void test1_5(){
        // 模拟前端传入参数
        String username = "root";
        String password = "123456";
        // 创建 Wrapper
        QueryWrapper<UserOthers> queryWrapper = new QueryWrapper<>();
        // 组装条件
        if (username != null){
            queryWrapper.eq("username", username);
        }
        if (password != null){
            queryWrapper.eq("password", password);
        }
        // 等价于：可以直接把判断条件拼接在 sql 中【🐮且高效！！！又包装了一层】
        queryWrapper.eq(username != null, "username", username)
                .eq(password != null, "password", password);
        // 执行 并 输出执行结果
        List<UserOthers> users = userMapper.selectList(queryWrapper);
        System.out.println("条件查询结果：" + users);
    }


    /**
     * UpdateWrapper 实战测试
     * - 进一步简化了使用 QueryWrapper Update 实体的操作
     * - 使用updateWrapper可以随意设置列的值！！
     */
    @Test
    public void testQuick2_1(){
        // 创建
        UpdateWrapper<UserOthers> updateWrapper = new UpdateWrapper<>();

        //将id = 3 的email设置为null, age = 18
        updateWrapper.eq("id",3)
                .set("email",null)  // set 指定列和结果
                .set("age",18);

        //如果使用updateWrapper 实体对象写null即可!
        int result = userMapper.update(null, updateWrapper);
        System.out.println("result = " + result);
    }


    /**
     *  LamdaQueryMapper ｜ LamdaUpdateMapper：避免类名和方法名写错的方式：类名::方法名
     *  把列名 -》类的属性的 get 方法的 lamda 形式调用
     */
    @Test
    public void testQuick2_2(){

        UpdateWrapper<UserOthers> updateWrapper = new UpdateWrapper<>();
        //将id = 3 的email设置为null, age = 18
        updateWrapper.eq("id",3)
                .set("email",null)  // set 指定列和结果
                .set("age",18);

        //使用lambdaUpdateWrapper
        LambdaUpdateWrapper<UserOthers> updateWrapper1 = new LambdaUpdateWrapper<>();
        updateWrapper1.eq(UserOthers::getId,3)
                .set(UserOthers::getUsername,null)
                .set(UserOthers::getPassword,18);

        //如果使用updateWrapper 实体对象写null即可!
        int result = userMapper.update(null, updateWrapper);
        System.out.println("result = " + result);
    }







}
