package com.liziczh.base.common.result;

import java.io.Serializable;

public class BaseResult implements Serializable {
	private static final long serialVersionUID = 5629679549953928644L;
	/**
	 * 状态码
	 */
	private String code;
	/**
	 * 消息描述
	 */
	private String message;
	/**
	 * 错误信息
	 */
	private String error;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
}
