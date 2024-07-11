package com.boyan.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeadlineInsertVo {
    private String title;
    private String article;
    private Integer type;
}
