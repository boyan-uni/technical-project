package com.boyan.utils;

/**
 * 统一返回结果：状态信息类
 *
 */

public enum ResponseCodeEnum {


    SUCCESS(200,"success"),
    USERNAME_ERROR(501,"usernameError"),
    PASSWORD_ERROR(503,"passwordError"),
    NOTLOGIN(504,"notLogin"),
    LOGIN_EXPIRED(504,"loginExpired"),
    USERNAME_USED(505,"userNameUsed");

    private Integer code;
    private String message;

    private ResponseCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

}

