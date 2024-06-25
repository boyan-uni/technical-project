package com.boyan.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User {
    @TableId                     // IdType = AUTO[数据库自增ID]/ASSIGN_ID[默认：雪花算法分配ID，Long/String类型]
    private Integer id;
    private String username;
    private String password;
}
