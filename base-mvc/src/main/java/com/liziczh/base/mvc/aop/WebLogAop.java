package com.liziczh.base.mvc.aop;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.liziczh.common.jackson.util.JacksonUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class WebLogAop {
	@Pointcut("@annotation(com.liziczh.base.mvc.aop.WebLog)")
	public void webLogController() {
	}
	/**
	 * 前置通知
	 * @param joinPoint 切点
	 * @throws Throwable
	 */
	@Before("webLogController()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 开始打印请求日志
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		assert attributes != null;
		HttpServletRequest request = attributes.getRequest();
		// 获取 @WebLog 注解的描述信息
		String methodDescription = getLogDescription(joinPoint);
		// 打印请求相关参数
		log.info("========================================== Start ==========================================");
		// 打印请求 url
		log.info("URL            : {}", request.getRequestURL().toString());
		// 打印描述信息
		log.info("Description    : {}", methodDescription);
		// 打印 Http method
		log.info("HTTP Method    : {}", request.getMethod());
		// 打印请求的 IP
		log.info("IP             : {}", request.getRemoteAddr());
	}
	/**
	 * 环绕通知
	 * @param proceedingJoinPoint 切点
	 * @return result
	 * @throws Throwable
	 */
	@Around("webLogController()")
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
	/**
	 * 后置通知
	 */
	@After("webLogController()")
	public void doAfter() {
		// 接口结束
		log.info("=========================================== End ===========================================" + System.lineSeparator());
	}
	/**
	 * 返回通知
	 * @param joinPoint 切点
	 * @param returnValue 返回值
	 */
	@AfterReturning(value = "webLogController()", returning = "returnValue")
	public void doAfterReturningAdvice(JoinPoint joinPoint, Object returnValue) {
		// 打印调用 controller 的全路径以及执行方法
		log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
		// 打印方法返回值
		String returnValueJson = (returnValue == null) ? "null" : JacksonUtils.toJSONString(returnValue);
		log.info("Return Value   : {}", JacksonUtils.toJSONString(returnValueJson));
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
}
