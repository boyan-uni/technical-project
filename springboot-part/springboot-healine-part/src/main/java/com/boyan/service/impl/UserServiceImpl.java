package com.boyan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boyan.pojo.User;
import com.boyan.service.UserService;
import com.boyan.mapper.UserMapper;
import com.boyan.utils.BaseResponse;
import com.boyan.utils.MD5Util;
import com.boyan.utils.ResponseCodeEnum;
import com.boyan.utils.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * UserServiceImpl：@Service 业务逻辑层：核心业务逻辑实现，合理调用 @Mapper 持久层方法
 * @author boyan
 * @description 针对表【news_user】的数据库操作 Service实现
 * @createDate 2024-07-03 15:39:24
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtHelper jwtHelper;

    //TODO. 用户注册界面

    /**
     * url地址：user/checkUserName
     * 请求方式：POST
     * 请求参数：param形式
     * username=zhangsan
     * 响应数据:
     * {
     *    "code":"200",
     *    "message":"success"
     *    "data":{}
     * }
     *
     * 实现步骤:
     *   1. 获取账号数据
     *   2. 根据账号进行数据库查询
     *   3. 结果封装
     */
    @Override
    public BaseResponse checkUsername(String username) {
        // 封装条件查询：看username是否已经被占用
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);

        // 调用持久层方法
        User user = userMapper.selectOne(queryWrapper);

        // 封装查询结果到 BaseResponse，并返回 BaseResponse
        if (user != null) {
            return BaseResponse.build(null, ResponseCodeEnum.USERNAME_USED);
        }
        return BaseResponse.ok(null);
    }


    /**
     * url地址：user/regist
     * 请求方式：POST
     * 请求参数：Json形式
     * {
     *     "username":"zhangsan",
     *     "userPwd":"123456",
     *     "nickName":"张三"
     * }
     * 响应数据：
     * {
     *    "code":"200",
     *    "message":"success"
     *    "data":{}
     * }
     *
     * 实现步骤:
     *   1. 将密码加密
     *   2. 将数据插入
     *   3. 判断结果,成 返回200 失败 505
     */
    @Override
    public BaseResponse register(User user) {
        // 封装条件查询：二次验证，防止从上次验证到提交之间 username 被其他用户注册
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername());
        // 调用持久层方法
        Long count = userMapper.selectCount(queryWrapper);
        // 如果数据库中已经有了同名的用户，则返回错误信息
        if (count != 0) {
            return BaseResponse.build(null, ResponseCodeEnum.USERNAME_USED);
        }
        // 否则，正确执行插入操作
        // 1. 对用户密码进行加密后
        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
        // 2. 放入数据库
        int rows = userMapper.insert(user);
        // 3. 返回操作结果
        System.out.println("rows = " + rows);
        return BaseResponse.ok(null);
    }

    //TODO. 用户登录界面

    /**
     * 登录需求
     * 地址: /user/login
     * 方式: post
     * 参数: json
     *    {
     *     "username":"zhangsan", //用户名
     *     "userPwd":"123456"     //明文密码
     *    }
     * 返回:
     *   {
     *    "code":"200",         // 成功状态码
     *    "message":"success"   // 成功状态描述
     *    "data":{
     *         "token":"... ..." // 用户id的token
     *     }
     *  }
     *
     * 大概流程:
     *    1. 账号进行数据库查询 返回用户对象
     *    2. 对比用户密码(md5加密)
     *    3. 成功,根据userId生成token -> map key=token value=token值 - result封装
     *    4. 失败,判断账号还是密码错误,封装对应的枚举错误即可
     */
    @Override
    public BaseResponse login(User user) {
        // 封装 LambdaQueryWrapper 条件查询，返回数据库中查到的用户对象
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername());
        User loginUser = userMapper.selectOne(queryWrapper);

        // 账号判断
        if (loginUser == null) {
            return  BaseResponse.build(null, ResponseCodeEnum.USERNAME_ERROR);  // 输入用户名错误的情况；
        }

        // 密码判断（对比 MD5 加密过的密码形式）
        if (!loginUser.getUserPwd().equals(MD5Util.encrypt(user.getUserPwd()))) {
            return BaseResponse.build(null, ResponseCodeEnum.PASSWORD_ERROR);   // 输入密码错误的情况；
        }

        // 账号密码均正确，可以成功登录，对当前用户生成唯一 token 并返回给上层传到前端
        String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));

        // 成功,根据userId生成token -> map key=token value=token值 - result封装 [根据前端接口文档要求]
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);

        return BaseResponse.ok(data);
    }

    /**
     * 地址: user/getUserInfo
     * 方式: get
     * 请求头: token = token内容
     * 返回:
     *    {
     *     "code": 200,
     *     "message": "success",
     *     "data": {
     *         "loginUser": {
     *             "uid": 1,
     *             "username": "zhangsan",
     *             "userPwd": "",
     *             "nickName": "张三"
     *         }
     *      }
     *   }
     *
     * 大概流程:
     *    1.获取token,解析token对应的userId
     *    2.根据userId,查询用户数据
     *    3.将用户数据的密码置空,并且把用户数据封装到结果中key = loginUser
     *    4.失败返回504 (本次先写到当前业务,后期提取到拦截器和全局异常处理器)
     */
    @Override
    public BaseResponse getUserInfo(String token) {
        // 判断 token 是否过期
        if (jwtHelper.isExpiration(token)) {
            return BaseResponse.build(null, ResponseCodeEnum.NOTLOGIN);
        }
        // 调用 jwtHelper 从 token 中解析获取 userId
        int uid = jwtHelper.getUserId(token).intValue(); // 转换格式为 int 和数据库表属性相同；
        // 封装 LambdaQueryWrapper 条件查询，根据 userId 返回数据库中查到的用户对象
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUid, uid);
        User loginUser = userMapper.selectOne(queryWrapper);

        // 如果 显示没有 userId 的情况，则返回 504 未登录错误码
        if (loginUser == null){
            return BaseResponse.build(null, ResponseCodeEnum.NOTLOGIN);
        }
        // 如果 loginUser 非空，则需要把 userPwd 置空【不可以显示出现用户密码在整个执行过程中】，然后以 hashmap 的形式返回结果【该用户的详细信息】
        loginUser.setUserPwd(null);
        Map<String, Object> data = new HashMap<>();
        data.put("loginUser", loginUser);

        return BaseResponse.ok(data);
    }

}




