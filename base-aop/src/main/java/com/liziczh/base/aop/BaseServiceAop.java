package com.liziczh.base.aop;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liziczh.base.jackson.util.JacksonUtils;

public abstract class BaseServiceAop {
	protected static final Logger logger = LoggerFactory.getLogger(BaseServiceAop.class);

	@Pointcut("execution(public * com.liziczh..*.service.*.*(..))")
	public void executeService() {
	}
	@Pointcut("execution(public * com.liziczh..*.*(..))")
	public void serviceException() {
	}
	/**
	 * 环绕通知
	 * @param proceedingJoinPoint 切入点
	 * @return
	 * @throws Throwable
	 */
	@Around("executeService()")
	public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		String methodName = proceedingJoinPoint.getSignature().getDeclaringTypeName() + "." + proceedingJoinPoint.getSignature().getName();
		String methodParams = null;
		Object[] args = proceedingJoinPoint.getArgs();
		if (args != null && args.length > 0) {
			try {
				methodParams = JacksonUtils.toJSONString(args);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		Object obj = proceedingJoinPoint.proceed();
		long timeConsum = System.currentTimeMillis() - startTime;
		logger.debug("[FLAG:{} RTIME:{} METHOD:{} PARAMS:{}]", "S", timeConsum, methodName, methodParams);
		return obj;
	}
	/**
	 * 返回通知
	 * @param joinPoint 切入点
	 * @param returnValue 返回值
	 */
	@AfterReturning(value = "executeService()", returning = "returnValue")
	public void doAfterReturningAdvice(JoinPoint joinPoint, Object returnValue) {
		String methodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
		String returnValueJson = returnValue == null ? "null" : JacksonUtils.toJSONString(returnValue);
		logger.debug("[FLAG:{} METHOD:{} PARAMS:{}]", "SR", methodName, returnValueJson);
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
			logger.debug(e.getMessage(), e);
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
