package com.liziczh.base.common.exception;

/**
 * 业务INFO异常
 *
 * @author chenzhehao
 * @version 1.0
 * @description
 * @date 2021/7/17 21:52
 */
public class BizInfoException extends BaseException {
    private static final long serialVersionUID = -2999713747880719040L;

    public BizInfoException() {
        super();
    }

    public BizInfoException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public BizInfoException(String code, String message) {
        super(code, message);
    }

    public BizInfoException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizInfoException(String message) {
        super(message);
    }

    public BizInfoException(Throwable cause) {
        super(cause);
    }
}
