package com.liziczh.base.common.result;

import java.io.Serializable;

public class Result<T> implements Serializable {
	private static final long serialVersionUID = -149250316051709651L;
	/**
	 * 状态码
	 */
	private String code;
	/**
	 * 消息描述
	 */
	private String message;
	/**
	 * 数据
	 */
	private T data;

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
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
