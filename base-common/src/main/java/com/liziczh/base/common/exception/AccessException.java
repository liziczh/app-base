package com.liziczh.base.common.exception;

public class AccessException extends BaseException {
	private static final long serialVersionUID = -8627751473332917178L;
	public AccessException() {
		super();
	}
	public AccessException(String code, String message, Throwable cause) {
		super(code, message, cause);
	}
	public AccessException(String code, String message) {
		super(code, message);
	}
	public AccessException(String message, Throwable cause) {
		super(message, cause);
	}
	public AccessException(String message) {
		super(message);
	}
	public AccessException(Throwable cause) {
		super(cause);
	}
}
