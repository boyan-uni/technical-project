package com.boyan.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")                                 // 名称
public class UserOthers {

    @TableId(value = "id", type = IdType.AUTO)     // 名称、数据库自增长
    private Integer id;

    @TableField
    private String username;

    @TableField(value = "password", exist = true)  // 名称、是否是存在在数据库表中的列名？默认true，false则数据库操作时会忽略
    private String password;
}
