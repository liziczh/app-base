package com.liziczh.base.common.result;

import java.util.ArrayList;
import java.util.List;

public class ListResult<T> extends BaseResult {
	private static final long serialVersionUID = -1322923057487208145L;
	/**
	 * 请求结果
	 */
	private List<T> results = new ArrayList<T>();

	public List<T> getResults() {
		return results;
	}
	public void setResults(List<T> results) {
		this.results = results;
	}
}
