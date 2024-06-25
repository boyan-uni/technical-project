package com.boyan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boyan.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
