package com.liziczh.base.mvc.handler;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.liziczh.base.common.exception.BizErrorException;
import com.liziczh.base.common.exception.BizFatalException;
import com.liziczh.base.common.exception.BizInfoException;

public class ControllerExceptionHandler {
	protected static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);
	public final String DEFAULT_MESSAGE = "系统错误";

	@ExceptionHandler(BizInfoException.class)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Map<String, String> handleBizInfoException(BizInfoException e) {
		return buildErrorMessage(e.getCode(), e.getMessage());
	}
	@ExceptionHandler(BizErrorException.class)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Map<String, String> handleBizErrorException(BizErrorException e) {
		logger.error(e.getMessage(), e.getCause() != null ? e.getCause() : e);
		return buildErrorMessage(DEFAULT_MESSAGE);
	}
	@ExceptionHandler(BizFatalException.class)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Map<String, String> handleBizFatalException(BizFatalException ex) {
		logger.error(ex.getMessage(), ex.getCause() != null ? ex.getCause() : ex);
		return buildErrorMessage(DEFAULT_MESSAGE);
	}
	/**
	 * 构建错误响应信息
	 * @param code 错误码
	 * @param message 错误信息
	 * @return Map
	 */
	private Map<String, String> buildErrorMessage(String code, String message) {
		Map<String, String> model = new HashMap<String, String>();
		model.put("code", StringUtils.isNotBlank(code) ? code : "0");
		model.put("message", message == null ? DEFAULT_MESSAGE : message);
		return model;
	}
	/**
	 * 构建错误响应信息
	 * @param message 错误信息
	 * @return Map
	 */
	private Map<String, String> buildErrorMessage(String message) {
		Map<String, String> model = new HashMap<String, String>();
		model.put("code", "0");
		model.put("message", message == null ? DEFAULT_MESSAGE : message);
		return model;
	}
}
