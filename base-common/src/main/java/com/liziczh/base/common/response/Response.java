package com.liziczh.base.common.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> implements Serializable {
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
	/**
	 * 操作成功
	 * @return 响应信息
	 */
	public Response<T> success(){
		return new Response<>(RESPONSE_CODE.SUCCESS.getCode(), RESPONSE_CODE.SUCCESS.getMsg(), null);
	}
	/**
	 * 操作成功
	 * @param message 响应描述
	 * @return 响应信息
	 */
	public Response<T> success(String message){
		return new Response<>(RESPONSE_CODE.SUCCESS.getCode(), message, null);
	}
	/**
	 * 操作失败
	 * @param message 响应描述
	 * @return 响应信息
	 */
	public Response<T> failed(String message){
		return new Response<>(RESPONSE_CODE.FAILED.getCode(), message, null);
	}
	/**
	 * 操作完成
	 * @param data 数据
	 * @return 响应信息
	 */
	public Response<T> complete(T data){
		return new Response<>(RESPONSE_CODE.SUCCESS.getCode(), RESPONSE_CODE.SUCCESS.getMsg(), data);
	}
	/**
	 * 操作完成
	 * @param code 响应Code
	 * @param message 响应描述
	 * @param data 数据
	 * @return 响应信息
	 */
	public Response<T> complete(String code, String message, T data){
		return new Response<>(code, message, data);
	}
	/**
	 * 请求结果状态码
	 */
	public static enum RESPONSE_CODE {
		SUCCESS("200", "操作成功"),
		FAILED("500", "操作失败"),
		ERROR("102", "系统错误");
		private String code;
		private String msg;

		private RESPONSE_CODE(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}
		public String getCode() {
			return code;
		}
		public String getMsg() {
			return msg;
		}
	}
}
