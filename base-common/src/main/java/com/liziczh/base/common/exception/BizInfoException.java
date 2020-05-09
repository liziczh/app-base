package com.liziczh.base.common.exception;

/**
 * 用户操作异常
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
