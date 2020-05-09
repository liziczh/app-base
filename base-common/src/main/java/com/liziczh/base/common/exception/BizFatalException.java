package com.liziczh.base.common.exception;

/**
 * 最严重异常
 */
public class BizFatalException extends BaseException {
	private static final long serialVersionUID = -964606973164363631L;

	public BizFatalException() {
		super();
	}
	public BizFatalException(String code, String message, Throwable cause) {
		super(code, message, cause);
	}
	public BizFatalException(String code, String message) {
		super(code, message);
	}
	public BizFatalException(String message, Throwable cause) {
		super(message, cause);
	}
	public BizFatalException(String message) {
		super(message);
	}
	public BizFatalException(Throwable cause) {
		super(cause);
	}
}
