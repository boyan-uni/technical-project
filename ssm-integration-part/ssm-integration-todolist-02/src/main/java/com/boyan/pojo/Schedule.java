package com.boyan.pojo;

import lombok.Data;

/**
 * @TableName schedule
 */
@Data
public class Schedule {

    private Integer id;

    private String title;

    private Integer completed;

}