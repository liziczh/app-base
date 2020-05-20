package com.liziczh.base.common.result;

public class ResultBuilder<T> {
	public Result<T> success() {
		Result<T> result = new Result<T>();
		result.setCode(ResultConstant.RESULT_CODE.SUCCESS.getCode());
		result.setMessage(ResultConstant.RESULT_CODE.SUCCESS.getName());
		result.setData(null);
		return result;
	}
	public Result<T> success(String message) {
		Result<T> result = new Result<T>();
		result.setCode(ResultConstant.RESULT_CODE.SUCCESS.getCode());
		result.setMessage(message);
		result.setData(null);
		return result;
	}
	public Result<T> complete(T data) {
		Result<T> result = new Result<T>();
		result.setCode(ResultConstant.RESULT_CODE.SUCCESS.getCode());
		result.setMessage(ResultConstant.RESULT_CODE.SUCCESS.getName());
		result.setData(data);
		return result;
	}
	public Result<T> build(String code, String message, T data) {
		Result<T> result = new Result<T>();
		result.setCode(code);
		result.setMessage(message);
		result.setData(data);
		return result;
	}
}
