package com.liziczh.base.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.liziczh.base.common.context.BaseContextHelper;
import com.liziczh.base.jackson.util.JacksonUtils;

@Component
@Aspect
public class WebAop {
	public static final Logger logger = LoggerFactory.getLogger(WebAop.class);

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
		logger.info("[FLAG:{} USERID:{} USERIP:{} CLIENTVERSION:{} CLIENTNUM:{} RTIME:{} METHOD:{} PARAMS:{}]", "WEB", BaseContextHelper.getUserId(), BaseContextHelper.getIpAddress(), BaseContextHelper.getClientVersion(), BaseContextHelper.getComefrom(), timeConsum, methodName, methodParams);
		return obj;
	}
	@AfterReturning(value = "executeController()", returning = "returnValue")
	public void doAfterReturningAdvice(JoinPoint joinPoint, Object returnValue) {
		String methodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
		String returnValueJson = (returnValue == null) ? "null" : JacksonUtils.toJSONString(returnValue);
		logger.info("[FLAG:{} USERID:{} CLIENTVERSION:{} CLIENTNUM:{} METHOD:{} RETURN:{}]", "WEB RETURN", BaseContextHelper.getUserId(), BaseContextHelper.getClientVersion(), BaseContextHelper.getComefrom(), methodName, returnValueJson);
	}
}
