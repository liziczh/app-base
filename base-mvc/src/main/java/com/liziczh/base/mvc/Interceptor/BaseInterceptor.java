package com.liziczh.base.mvc.Interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public abstract class BaseInterceptor extends HandlerInterceptorAdapter {
	/**
	 * 获取ServletContext对象
	 * @param request HTTP请求
	 * @return ServletContext
	 */
	protected ServletContext getServletContext(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return session.getServletContext();
	}
	/**
	 * 获取session
	 * @param request HTTP请求
	 * @return HttpSession
	 */
	protected HttpSession getSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return session;
	}
	/**
	 * 获取sessionId
	 * @param request HTTP请求
	 * @return sessionId
	 */
	protected String getSessionId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return session.getId();
	}
}
