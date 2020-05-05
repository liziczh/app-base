package com.liziczh.base.common.result;

public class SingleResult<T> extends BaseResult {
	private static final long serialVersionUID = 970482277155560949L;
	/**
	 * 请求结果
	 */
	public T result;

	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
}
