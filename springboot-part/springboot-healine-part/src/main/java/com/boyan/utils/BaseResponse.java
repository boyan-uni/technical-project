package com.boyan.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 全局统一返回结果类
 */
@Data
@NoArgsConstructor
public class BaseResponse<T> {

    private Integer code;       // 返回码

    private String message;     // 返回消息

    private T data;             // 返回数据


    /**
     * 返回数据 - 静态方法｜类方法
     */
    protected static <T> BaseResponse<T> build(T data) {
        BaseResponse<T> baseResponse = new BaseResponse<T>();
        if (data != null)
            baseResponse.setData(data);
        return baseResponse;
    }

    public static <T> BaseResponse<T> build(T body, Integer code, String message) {
        BaseResponse<T> baseResponse = build(body);
        baseResponse.setCode(code);
        baseResponse.setMessage(message);
        return baseResponse;
    }

    public static <T> BaseResponse<T> build(T body, ResponseCodeEnum responseCodeEnum) {
        BaseResponse<T> baseResponse = build(body);
        baseResponse.setCode(responseCodeEnum.getCode());
        baseResponse.setMessage(responseCodeEnum.getMessage());
        return baseResponse;
    }

    /**
     * 操作成功
     * @param data  baseCategory1List
     * @param <T>
     * @return
     */
    public static<T> BaseResponse<T> ok(T data){
        BaseResponse<T> baseResponse = build(data);
        return build(data, ResponseCodeEnum.SUCCESS);
    }
    public BaseResponse<T> message(String msg){
        this.setMessage(msg);
        return this;
    }
    public BaseResponse<T> code(Integer code){
        this.setCode(code);
        return this;
    }

}