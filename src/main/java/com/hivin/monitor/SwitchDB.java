package com.hivin.monitor;


import com.hivin.configure.DatabaseContextHolder;
import com.hivin.configure.DatabaseType;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.JoinPoint;
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
public class SwitchDB {
    private static Logger LOG = LoggerFactory.getLogger(SwitchDB.class);

    @Pointcut("@annotation(com.hivin.configure.Master)")
    public void masterAop() {
    }

    @Before("masterAop()")
    public void toMaster(JoinPoint joinPoint) throws Throwable {
        LOG.info("切换至master");
        DatabaseContextHolder.setDatabaseType(DatabaseType.master);
    }

    @Pointcut("@annotation(com.hivin.configure.Slave)")
    public void slaveAop() {
    }

    @Before("slaveAop()")
    public void toSlave(JoinPoint joinPoint) throws Throwable {
        LOG.info("切换至slave");
        DatabaseContextHolder.setDatabaseType(DatabaseType.slave);

    }

    @After("slaveAop()")
    public void slaveToMastr(JoinPoint joinPoint) throws Throwable {
        LOG.info("由slave切换至master");
        DatabaseContextHolder.setDatabaseType(DatabaseType.master);
    }


}