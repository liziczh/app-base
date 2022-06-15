package com.liziczh.base.common.exception;

/**
 * 业务WARN异常
 *
 * @author chenzhehao
 * @version 1.0
 * @description
 * @date 2021/7/17 21:52
 */
public class BizWarnException extends BaseException {
    private static final long serialVersionUID = -6620154554283372520L;

    public BizWarnException() {
        super();
    }

    public BizWarnException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public BizWarnException(String code, String message) {
        super(code, message);
    }

    public BizWarnException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizWarnException(String message) {
        super(message);
    }

    public BizWarnException(Throwable cause) {
        super(cause);
    }
}
