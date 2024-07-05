package com.boyan.pojo;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * news_user
 *
 * @TableName 删去在 application.yaml 中统一配置
 */

@Data
@NoArgsConstructor
public class User implements Serializable {

    @TableId                                          // 默认值，雪花算法
    private Integer uid;

    private String username;

    private String userPwd;

    private String nickName;

    @Version
    private Integer version;                          // 乐观锁 @Version：标识用于乐观锁定的版本字段。

    @TableLogic
    private Integer isDeleted;                        // 逻辑删除参数

    private static final long serialVersionUID = 1L;  // 用于序列化的版本控制机制，在 Java 中实现 Serializable 接口的类中配置

    /**
     * 按需设置构造函数，其余属性自动配置：id，version，isDeleted；
     *
     * @param username
     * @param userPwd
     * @param nickName
     */
    public User(String username, String userPwd, String nickName) {
        this.username = username;
        this.userPwd = userPwd;
        this.nickName = nickName;
    }
}