package com.boyan.mapper;

import com.boyan.pojo.UserBoot;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user")
    List<UserBoot> queryAll();
}
