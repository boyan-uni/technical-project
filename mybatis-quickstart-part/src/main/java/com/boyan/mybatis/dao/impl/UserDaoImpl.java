package com.boyan.mybatis.dao.impl;

import com.boyan.mybatis.dao.UserDao;
import com.boyan.mybatis.pojo.User;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * UserDao interface 的实现类
 */
public class UserDaoImpl implements UserDao {

    public SqlSession sqlSession;

    public UserDaoImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    @Override
    public User queryUserById(String id) {
        return this.sqlSession.selectOne("UserDao.queryUserById", id);
    }

    /**
     * 查询所有用户信息
     *
     * @return
     */
    @Override
    public List<User> queryUserAll() {
        return this.sqlSession.selectList("UserDao.queryUserAll");
    }

    /**
     * 新增用户
     *
     * @param user
     */
    @Override
    public void insertUser(User user) {
        this.sqlSession.insert("UserDao.insertUser", user);
    }

    /**
     * 更新用户信息
     *
     * @param user
     */
    @Override
    public void updateUser(User user) {
        this.sqlSession.update("UserDao.updateUser", user);
    }

    /**
     * 根据id删除用户信息
     *
     * @param id
     */
    @Override
    public void deleteUser(String id) {
        this.sqlSession.delete("UserDao.deleteUser", id);
    }
}
