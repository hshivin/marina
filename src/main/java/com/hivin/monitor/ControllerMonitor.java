package com.hivin.monitor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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
    private static Logger log = LoggerFactory.getLogger(ControllerMonitor.class);


    @Pointcut("execution(* com.hivin.controller..*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void controllorAop() {
    }

    @Around("controllorAop()")
    public Object Interceptor(ProceedingJoinPoint pjp) {
        long beginTime = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String methodName = pjp.getSignature().getName(); //获取被拦截的方法名

        log.info("URL : {}", request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(pjp.getArgs()));

        Object result = null;
        try {
            // 一切正常的情况下，继续执行被拦截的方法
            result = pjp.proceed();
        } catch (Throwable e) {
            log.info("monitor exception: ", e);
        }
        long costMs = System.currentTimeMillis() - beginTime;
        log.info("{}请求结束，耗时：{}ms", methodName, costMs);
        log.info("Response = {}", result);
        return result;
    }


}
