package com.liziczh.base.common.aop;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.liziczh.common.jackson.util.JacksonUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public abstract class BaseExceptionMailAop {
	@Pointcut("@annotation(ServiceLog)")
	public void serviceException() {
	}
	/**
	 * 异常通知
	 * @param joinPoint 切入点
	 * @param exception 异常
	 */
	@AfterThrowing(value = "serviceException()", throwing = "exception")
	public void handleThrowing(JoinPoint joinPoint, Exception exception) {
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		try {
			this.sendErrorMessage(exception, className, methodName, JacksonUtils.toJSONString(args), new Date());
		} catch (Exception e) {
			log.debug(e.getMessage(), e);
		}
	}
	/**
	 * 发送异常邮件
	 * @param exception 异常
	 * @param className 类名
	 * @param methodName 方法名
	 * @param methodParams 方法参数
	 * @param occurTime 异常发生时间
	 */
	protected abstract void sendErrorMessage(Exception exception, String className, String methodName, String methodParams, Date occurTime);
}
