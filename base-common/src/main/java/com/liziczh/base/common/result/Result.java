package com.liziczh.base.common.result;

import java.io.Serializable;

import lombok.Data;

@Data
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
}
