package com.boyan.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * news_headline
 *
 * @TableName 删去在 application.yaml 中统一配置
 */

@Data
@NoArgsConstructor
public class Headline implements Serializable {

    @TableId(type = IdType.AUTO) // 默认值，雪花算法
    private Integer hid;

    private String title;

    private String article;

    private Integer type;

    private Integer publisher;

    private Integer pageViews;

    private Date createTime;

    private Date updateTime;

    @Version
    private Integer version;    // 乐观锁 @Version：标识用于乐观锁定的版本字段。

    @TableLogic
    private Integer isDeleted;  // 逻辑删除

    private static final long serialVersionUID = 1L;
}