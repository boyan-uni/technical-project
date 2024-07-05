package com.boyan.service;

import com.boyan.mapper.UserMapper;
import com.boyan.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.boyan.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * UserService：@Service 业务逻辑层
 * @author boyan
 * @description 针对表【news_user】的数据库操作 Service 接口
 * @createDate 2024-07-03 15:39:24
*/
public interface UserService extends IService<User> {


    Result checkUsername(String username);

    Result register(User user);

    Result login(User user);

    Result getUserInfo(String token);
}
