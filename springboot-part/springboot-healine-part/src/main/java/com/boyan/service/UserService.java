package com.boyan.service;

import com.boyan.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.boyan.utils.BaseResponse;

/**
 * UserService：@Service 业务逻辑层
 * @author boyan
 * @description 针对表【news_user】的数据库操作 Service 接口
 * @createDate 2024-07-03 15:39:24
*/
public interface UserService extends IService<User> {


    BaseResponse checkUsername(String username);

    BaseResponse register(User user);

    BaseResponse login(User user);

    BaseResponse getUserInfo(String token);
}
