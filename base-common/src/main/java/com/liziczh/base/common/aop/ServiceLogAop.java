package com.liziczh.base.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import com.liziczh.base.common.util.JacksonUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class ServiceLogAop {
	/**
	 * 切入点
	 */
	@Pointcut("execution(public * com.liziczh..*.service..*.*(..))")
	public void executeService() {
	}
	/**
	 * 前置通知
	 * @param joinPoint 切点
	 */
	@Before("executeService()")
	public void doBefore(JoinPoint joinPoint) {
		// ServiceLogIgnore
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		ServiceLogIgnore serviceLogIgnore = AnnotationUtils.findAnnotation(methodSignature.getMethod(), ServiceLogIgnore.class);
		if (serviceLogIgnore != null) {
			return;
		}
	}
	/**
	 * 环绕通知
	 * @param proceedingJoinPoint 切入点
	 * @return
	 * @throws Throwable
	 */
	@Around("executeService()")
	public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		// ServiceLogIgnore
		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
		ServiceLogIgnore serviceLogIgnore = AnnotationUtils.findAnnotation(methodSignature.getMethod(), ServiceLogIgnore.class);
		if (serviceLogIgnore != null) {
			return proceedingJoinPoint.proceed();
		}
		long startTime = System.currentTimeMillis();
		// 开始打印请求日志
		log.info("========================================== Start ==========================================");
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
		// 接口结束后换行，方便分割查看
		log.info("=========================================== End ===========================================");
		return result;
	}
	@After("executeService()")
	public void doAfter(JoinPoint joinPoint) {
		// ServiceLogIgnore
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		ServiceLogIgnore serviceLogIgnore = AnnotationUtils.findAnnotation(methodSignature.getMethod(), ServiceLogIgnore.class);
		if (serviceLogIgnore != null) {
			return;
		}
	}
	/**
	 * 返回通知
	 * @param joinPoint 切点
	 * @param returnValue 返回值
	 */
	@AfterReturning(value = "executeService()", returning = "returnValue")
	public void doAfterReturningAdvice(JoinPoint joinPoint, Object returnValue) {
		// ServiceLogIgnore
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		ServiceLogIgnore serviceLogIgnore = AnnotationUtils.findAnnotation(methodSignature.getMethod(), ServiceLogIgnore.class);
		if (serviceLogIgnore != null) {
			return;
		}
		// 打印调用 controller 的全路径以及执行方法
		log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
		// 打印方法返回值
		String returnValueJson = (returnValue == null) ? "null" : JacksonUtils.toJSONString(returnValue);
		log.info("Return Value   : {}", JacksonUtils.toJSONString(returnValueJson));
		// 接口返回结束
		log.info("=========================================== Return ===========================================");
	}
}
