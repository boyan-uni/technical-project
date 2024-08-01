package com.boyan.controller;

import com.boyan.pojo.User;
import com.boyan.service.UserService;
import com.boyan.utils.JwtHelper;
import com.boyan.utils.BaseResponse;
import com.boyan.utils.ResponseCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * UserController：Controller层 表述层【只负责：简化接收request、简化发送response、调用@Service层业务逻辑，这3件事】
 * 用户模块
 */

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

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
    @PostMapping("checkUserName")
    public BaseResponse checkUsername(String username){
        BaseResponse baseResponse = userService.checkUsername(username);
        return baseResponse;
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
    @PostMapping("regist")
    public BaseResponse register(@RequestBody User user){
        BaseResponse baseResponse = userService.register(user);
        return baseResponse;
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
     *    2. 对比用户密码 (md5加密)
     *    3. 成功,根据userId生成token -> map key=token value=token值 - result封装
     *    4. 失败,判断账号还是密码错误,封装对应的枚举错误即可
     */
    @PostMapping("login")
    public BaseResponse login(@RequestBody User user){
        BaseResponse baseResponse = userService.login(user);
        return baseResponse;
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

    @GetMapping("getUserInfo")
    public BaseResponse getUserInfo(@RequestHeader String token){
        BaseResponse baseResponse = userService.getUserInfo(token);
        return baseResponse;
    }

    // todo. user/checkLogin 在用户模块创建一个登录状态校验接口，用于配合拦截器，在特定路径访问前进行登录校验，前端根据返回值决定跳转至哪个页面
    /**
     * url地址：user/checkLogin
     * 请求方式：get
     * 请求参数:  无
     * 请求头： token: 用户token【元数据 使用请求头传递，而非请求参数】
     * 响应数据：
     * 未过期：
     * {
     *     "code":"200",
     *     "message":"success",
     *     "data":{}
     * }
     * 过期：
     * {
     *     "code":"504",
     *     "message":"loginExpired",
     *     "data":{}
     * }
     */
    @GetMapping("checkLogin")
    public BaseResponse checkLogin(@RequestHeader String token){
        // 调用 jwtHelper 校验 token 是否有效：方法 boolean isExpiration(String token)；有效，返回false; 无效，返回true
        Boolean isExpiration = jwtHelper.isExpiration(token);
        // 根据校验结果封装 BaseResponse，无需到 Service 层
        if (isExpiration == false) {
            return BaseResponse.ok(null); // 封装 ResponseCodeEnum.SUCCESS
        } else {
            return BaseResponse.build(null, ResponseCodeEnum.LOGIN_EXPIRED);
        }
    }
}
