package com.liziczh.base.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.liziczh.base.common.context.BaseContextHelper;
import com.liziczh.base.jackson.util.JacksonUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class WebAop {
	@Pointcut("execution(public * com.liziczh..*.controller.*.*(..))")
	public void executeController() {
	}
	@Around("executeController()")
	public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		String methodName = proceedingJoinPoint.getSignature().getDeclaringTypeName() + "." + proceedingJoinPoint.getSignature().getName();
		String methodParams = null;
		Object[] args = proceedingJoinPoint.getArgs();
		if (args != null && args.length > 0) {
			methodParams = JacksonUtils.toJSONString(args);
		}
		Object obj = proceedingJoinPoint.proceed();
		long timeConsum = System.currentTimeMillis() - startTime;
		log.info("[FLAG:{} USERID:{} USERIP:{} CLIENTVERSION:{} CLIENTNUM:{} RTIME:{} METHOD:{} PARAMS:{}]", "WEB", BaseContextHelper.getUserId(), BaseContextHelper.getIpAddress(), BaseContextHelper.getClientVersion(), BaseContextHelper.getComefrom(), timeConsum, methodName, methodParams);
		return obj;
	}
	@AfterReturning(value = "executeController()", returning = "returnValue")
	public void doAfterReturningAdvice(JoinPoint joinPoint, Object returnValue) {
		String methodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
		String returnValueJson = (returnValue == null) ? "null" : JacksonUtils.toJSONString(returnValue);
		log.info("[FLAG:{} USERID:{} CLIENTVERSION:{} CLIENTNUM:{} METHOD:{} RETURN:{}]", "WEB RETURN", BaseContextHelper.getUserId(), BaseContextHelper.getClientVersion(), BaseContextHelper.getComefrom(), methodName, returnValueJson);
	}
}
