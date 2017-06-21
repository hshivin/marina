package com.hivin.monitor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by hivin on 17/1/18.
 */
@Aspect
@Component
public class ControllerMonitor {
    private static Logger LOG = LoggerFactory.getLogger(ControllerMonitor.class);


    @Pointcut("execution(* com.hivin.controller..*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void controllorAop() {
    }

    @Around("controllorAop()")
    public Object Interceptor(ProceedingJoinPoint pjp) {
        long beginTime = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String methodName = pjp.getSignature().getName(); //获取被拦截的方法名

        LOG.info("URL : {}", request.getRequestURL().toString());
        LOG.info("HTTP_METHOD : " + request.getMethod());
        LOG.info("IP : " + request.getRemoteAddr());
        LOG.info("CLASS_METHOD : " + pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());
        LOG.info("ARGS : " + Arrays.toString(pjp.getArgs()));

        Object result = null;
        try {
            // 一切正常的情况下，继续执行被拦截的方法
            result = pjp.proceed();

        } catch (Throwable e) {
            LOG.info("exception: ", e);
        }
        long costMs = System.currentTimeMillis() - beginTime;
        LOG.info("{}请求结束，耗时：{}ms", methodName, costMs);
        LOG.info("Response = {}", result);
        return result;
    }


}
