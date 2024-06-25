package com.boyan.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO类 User: BaseMapper<User> -> 找对应的 url 的数据库中的表名，所以 类名小写 = 表名，如果不一致，可以通过 @TableName 指定类对应的表名
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User {
    private Integer id;
    private String username;
    private String password;
}
