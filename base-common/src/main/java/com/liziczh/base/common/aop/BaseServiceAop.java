package com.liziczh.base.common.aop;

import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.liziczh.common.jackson.util.JacksonUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public abstract class BaseServiceAop {
	@Pointcut("@annotation(ServiceLog)")
	public void executeService() {
	}
	@Pointcut("execution(public * com.liziczh..*.*(..))")
	public void serviceException() {
	}
	/**
	 * 前置通知
	 * @param joinPoint 切点
	 * @throws Throwable
	 */
	@Before("executeService()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 开始打印请求日志
		// 获取 @ServiceLog 注解的描述信息
		String methodDescription = getLogDescription(joinPoint);
		// 打印请求相关参数
		log.info("========================================== Start ==========================================");
		// 打印描述信息
		log.info("Description    : {}", methodDescription);
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
		// 打印调用 controller 的全路径以及执行方法
		log.info("Class Method   : {}.{}", proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName());
		// 打印请求入参
		String methodParams = null;
		Object[] args = proceedingJoinPoint.getArgs();
		if (args != null && args.length > 0) {
			methodParams = JacksonUtils.toJSONString(args);
		}
		log.info("Request Args   : {}", methodParams);
		// 执行方法
		Object result = proceedingJoinPoint.proceed();
		// 打印出参
		log.info("Response Args  : {}", JacksonUtils.toJSONString(result));
		// 执行耗时
		log.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
		return result;
	}
	@After("executeService()")
	public void doAfter() throws Throwable {
		// 接口结束后换行，方便分割查看
		log.info("=========================================== End ===========================================" + System.lineSeparator());
	}
	/**
	 * 返回通知
	 * @param joinPoint 切点
	 * @param returnValue 返回值
	 */
	@AfterReturning(value = "executeService()", returning = "returnValue")
	public void doAfterReturningAdvice(JoinPoint joinPoint, Object returnValue) {
		// 打印调用 controller 的全路径以及执行方法
		log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
		// 打印方法返回值
		String returnValueJson = (returnValue == null) ? "null" : JacksonUtils.toJSONString(returnValue);
		log.info("Return Value   : {}", JacksonUtils.toJSONString(returnValueJson));
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
	 * 获取注解descption信息
	 * @param joinPoint 切点
	 * @return descption
	 * @throws Exception
	 */
	private String getLogDescription(JoinPoint joinPoint) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		StringBuilder description = new StringBuilder("");
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description.append(method.getAnnotation(WebLog.class).description());
					break;
				}
			}
		}
		return description.toString();
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
