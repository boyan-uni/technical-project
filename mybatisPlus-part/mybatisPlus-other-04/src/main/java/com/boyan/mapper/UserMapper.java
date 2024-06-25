package com.boyan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.boyan.pojo.UserOthers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<UserOthers> {

    /**
     * 自定义分页
     * 定义一个根据 id 参数查询，并且分页的方法
     */
    IPage<UserOthers> queryById(IPage<UserOthers> page, @Param("id") Integer id);

}
