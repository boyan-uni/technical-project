package com.boyan.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeadlineDTO implements Serializable {
    private Long hid;
    private String title;
    private String type;
    private Long pageViews;
    private Integer pastHours;  // 运算在 HeadlineMapper.xml 中的自定义 sql 查询语句中实现
    private String publisher;
}
