package com.boyan.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserBoot {

    private Integer id;

    private String userName;

    private String password;
}
