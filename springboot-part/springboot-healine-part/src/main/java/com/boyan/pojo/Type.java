package com.boyan.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * news_type
 *
 * @TableName 删去在 application.yaml 中统一配置
 */

@Data
@NoArgsConstructor
public class Type implements Serializable {

    @TableId(type = IdType.ASSIGN_UUID) // 默认值，雪花算法
    private Integer tid;

    private String tname;

    @Version
    private Integer version;    // 乐观锁 @Version：标识用于乐观锁定的版本字段。

    @TableLogic
    private Integer isDeleted;  // 逻辑删除

    private static final long serialVersionUID = 1L;
}