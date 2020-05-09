package com.liziczh.base.common.exception;

public abstract class BaseException extends RuntimeException {
	private static final long serialVersionUID = -6912830046396198904L;
	private String code;

	public BaseException() {
		super();
	}
	public BaseException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}
	public BaseException(String code, String message) {
		super(message);
		this.code = code;
	}
	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}
	public BaseException(String message) {
		super(message);
	}
	public BaseException(Throwable cause) {
		super(cause);
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
