package com.liziczh.base.common.exception;

import lombok.Data;

/**
 * 异常基类
 *
 * @author chenzhehao
 * @version 1.0
 * @description
 * @date 2021/7/17 21:52
 */
@Data
public abstract class BaseException extends RuntimeException {
    private static final long serialVersionUID = -6526269878339159057L;
    private String code;

    public BaseException() {
        super();
    }

    public BaseException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
