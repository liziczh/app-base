package com.liziczh.base.api.common.response;

import lombok.Data;

/**
 * 响应体
 *
 * @author chenzhehao
 * @version 1.0
 * @description
 * @date 2021/12/10 11:06 下午
 */
@Data
public class BaseResponse<T> {

    /**
     * 状态码
     */
    private String code;

    /**
     * 状态信息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    public BaseResponse<T> code(String code) {
        this.code = code;
        return this;
    }

    public BaseResponse<T> msg(String msg) {
        this.msg = msg;
        return this;
    }

    public BaseResponse<T> data(T data) {
        this.data = data;
        return this;
    }

}
