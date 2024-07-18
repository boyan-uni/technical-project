package com.boyan.mybatis.dao;

import com.boyan.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface UserMapper {
    /**
     * 登录（直接使用注解指定传入参数名称）
     * @param userName
     * @param password
     * @return
     */
    public User login(@Param("userName") String userName, @Param("password") String password);

    /**
     * 根据表名查询用户信息（直接使用注解指定传入参数名称）
     * @param tableName
     * @return
     */
    public List<User> queryUserByTableName(@Param("tableName") String tableName);

    /**
     * 根据Id查询用户信息
     * @param id
     * @return
     */
    public User queryUserById(String id);

    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> queryUserAll();

    /**
     * 新增用户信息
     * @param user
     */
    public void insertUser(User user);

    /**
     * 根据id更新用户信息
     * @param user
     */
    public void updateUser(User user);

    /**
     * 根据id删除用户信息
     * @param id
     */
    public void deleteUserById(String id);
}
