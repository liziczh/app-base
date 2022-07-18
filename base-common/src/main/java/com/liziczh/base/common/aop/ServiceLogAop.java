package com.liziczh.base.common.aop;

import com.liziczh.base.common.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class ServiceLogAop {
    /**
     * 切入点
     */
    @Pointcut("execution(public * com.liziczh..*.service.impl.*.*(..))")
    public void executeService() {
    }

    /**
     * 前置通知
     *
     * @param joinPoint 切点
     */
    @Before("executeService()")
    public void doBefore(JoinPoint joinPoint) {

    }

    /**
     * 环绕通知
     *
     * @param proceedingJoinPoint 切入点
     * @return
     * @throws Throwable
     */
    @Around("executeService()")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        // 开始打印请求日志
        log.info("========================================== Service Start ==========================================");
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", proceedingJoinPoint.getSignature().getDeclaringTypeName(),
                proceedingJoinPoint.getSignature().getName());
        // 打印请求入参
        String methodParams = null;
        Object[] args = proceedingJoinPoint.getArgs();
        if (args != null && args.length > 0) {
            methodParams = JsonUtils.toJson(args);
        }
        log.info("Request Args   : {}", methodParams);
        // 执行方法
        Object result = proceedingJoinPoint.proceed();
        // 打印出参
        log.info("BsaeResponse Args  : {}", JsonUtils.toJson(result));
        // 执行耗时
        log.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
        // 接口结束后换行，方便分割查看
        log.info("=========================================== Service End ===========================================");
        return result;
    }

    @After("executeService()")
    public void doAfter(JoinPoint joinPoint) {
    }

    /**
     * 返回通知
     *
     * @param joinPoint   切点
     * @param returnValue 返回值
     */
    @AfterReturning(value = "executeService()", returning = "returnValue")
    public void doAfterReturningAdvice(JoinPoint joinPoint, Object returnValue) {
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());
        // 打印方法返回值
        String returnValueJson = (returnValue == null) ? "null" : JsonUtils.toJson(returnValue);
        log.info("Return Value   : {}", JsonUtils.toJson(returnValueJson));
        // 接口返回结束
        log.info("========================================= Service Return =========================================");
    }
}
