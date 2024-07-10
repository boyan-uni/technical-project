package com.boyan.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeadlineDetailDTO {
    private Integer hid;
    private String title;
    private String article;
    private Integer type;
    private String typeName;    // 去 news_type 表中查
    private Integer pageViews;
    private Integer pastHours;  // 运算在 HeadlineMapper.xml 中的自定义 sql 查询语句中实现
    private Integer publisher;
    private String author;      // 去 news_user 表中查
    private Integer version;
}
