package com.liziczh.base.common.result;

import java.util.List;

public class ResultWrapper<T> {
	/**
	 * ListResult
	 * @param results results
	 * @return ListResult
	 */
	public ListResult<T> success(List<T> results) {
		ListResult<T> result = new ListResult<T>();
		result.setCode(ResultConstant.RESULT_CODE.SUCCESS.getCode());
		result.setMessage(ResultConstant.RESULT_CODE.SUCCESS.getName());
		result.setError(null);
		result.setResults(results);
		return result;
	}
	/**
	 * SingleResult
	 * @param resultValue result
	 * @return SingleResult
	 */
	public SingleResult<T> success(T resultValue) {
		SingleResult<T> result = new SingleResult<T>();
		result.setCode(ResultConstant.RESULT_CODE.SUCCESS.getCode());
		result.setMessage(ResultConstant.RESULT_CODE.SUCCESS.getName());
		result.setError(null);
		result.setResult(resultValue);
		return result;
	}
	/**
	 * SingleResult Message
	 * @param message message
	 * @return SingleResult
	 */
	public SingleResult<T> message(String message) {
		SingleResult<T> result = new SingleResult<T>();
		result.setCode(ResultConstant.RESULT_CODE.SUCCESS.getCode());
		result.setMessage(message);
		result.setError(null);
		result.setResult(null);
		return result;
	}
}
