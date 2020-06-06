package com.liziczh.base.mvc.aop;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.liziczh.base.common.util.JacksonUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class WebLogAop {
	@Pointcut("execution(public * com.liziczh..*.controller.*.*(..))")
	public void webLogController() {
	}
	/**
	 * 前置通知
	 * @param joinPoint 切点
	 */
	@Before("webLogController()")
	public void doBefore(JoinPoint joinPoint) {
		// WebLogIgnore
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		WebLogIgnore webLogIgnore = AnnotationUtils.findAnnotation(methodSignature.getMethod(), WebLogIgnore.class);
		if (webLogIgnore != null) {
			return;
		}
	}
	/**
	 * 环绕通知
	 * @param proceedingJoinPoint 切点
	 * @return result
	 * @throws Throwable
	 */
	@Around("webLogController()")
	public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		// WebLogIgnore
		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
		WebLogIgnore webLogIgnore = AnnotationUtils.findAnnotation(methodSignature.getMethod(), WebLogIgnore.class);
		if (webLogIgnore != null) {
			// 执行方法
			return proceedingJoinPoint.proceed();
		}
		long startTime = System.currentTimeMillis();
		// 开始打印请求日志
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		assert attributes != null;
		HttpServletRequest request = attributes.getRequest();
		// 打印请求相关参数
		log.info("========================================== Web Start ==========================================");
		// 打印请求 url
		log.info("URL            : {}", request.getRequestURL().toString());
		// 打印 Http method
		log.info("HTTP Method    : {}", request.getMethod());
		// 打印请求的 IP
		log.info("IP             : {}", request.getRemoteAddr());
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
		// 接口结束
		log.info("=========================================== Web End ===========================================");
		return result;
	}
	/**
	 * 后置通知
	 */
	@After("webLogController()")
	public void doAfter(JoinPoint joinPoint) {
		// WebLogIgnore
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		WebLogIgnore webLogIgnore = AnnotationUtils.findAnnotation(methodSignature.getMethod(), WebLogIgnore.class);
		if (webLogIgnore != null) {
			return;
		}
	}
	/**
	 * 返回通知
	 * @param joinPoint 切点
	 * @param returnValue 返回值
	 */
	@AfterReturning(value = "webLogController()", returning = "returnValue")
	public void doAfterReturningAdvice(JoinPoint joinPoint, Object returnValue) {
		// WebLogIgnore
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		WebLogIgnore webLogIgnore = AnnotationUtils.findAnnotation(methodSignature.getMethod(), WebLogIgnore.class);
		if (webLogIgnore != null) {
			return;
		}
		// 打印调用 controller 的全路径以及执行方法
		log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
		// 打印方法返回值
		String returnValueJson = (returnValue == null) ? "null" : JacksonUtils.toJSONString(returnValue);
		log.info("Return Value   : {}", JacksonUtils.toJSONString(returnValueJson));
		// 接口返回
		log.info("=========================================== Web Return ===========================================" + System.lineSeparator());
	}
}
