package com.liziczh.base.common.aop;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.liziczh.common.jackson.util.JacksonUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public abstract class BaseServiceAop {
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
			methodParams = JacksonUtils.toJSONString(args);
		}
		Object obj = proceedingJoinPoint.proceed();
		long exeTime = System.currentTimeMillis() - startTime;
		log.debug("[FLAG:{} EXETIME:{} METHOD:{} PARAMS:{}]", "SERVICE", exeTime, methodName, methodParams);
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
		String returnValueJson = (returnValue == null) ? "null" : JacksonUtils.toJSONString(returnValue);
		log.debug("[FLAG:{} METHOD:{} PARAMS:{}]", "SERVICE RETURN", methodName, returnValueJson);
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
