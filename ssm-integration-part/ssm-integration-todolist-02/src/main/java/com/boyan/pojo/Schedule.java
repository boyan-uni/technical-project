package com.boyan.pojo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @TableName schedule
 */
@Data
@NoArgsConstructor
public class Schedule {

    private Integer id; // 自增长

    @NotBlank
    private String title;
    @NotNull
    private Integer completed;
}