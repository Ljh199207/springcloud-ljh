package com.example.demo.aspect;

import com.example.demo.annotation.Log;
import com.example.demo.bean.SysLog;
import com.example.demo.mapper.SysLogDao;
import com.example.demo.util.HttpContextUtils;
import com.example.demo.util.IPUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAspect {

    @Autowired
    private SysLogDao sysLogDao;

    @Pointcut(value = "@annotation(com.example.demo.annotation.Log)")
    public void poinCut() {
    }

    @Around(value = "poinCut()")
    public void arount(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // 执行方法
        joinPoint.proceed();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        saveSysLog(joinPoint, time);
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        //获取方法上签名对象
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取方法
        Method method = signature.getMethod();
        SysLog sysLog = new SysLog();
        Log annotation = method.getAnnotation(Log.class);
        if (annotation != null) {
            sysLog.setOperation(annotation.value());
        }
        System.out.println(method.getName() + "----joinPont----" + joinPoint.getTarget().getClass().getName());
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        // 请求的方法参数值
        Object[] args = joinPoint.getArgs();
        // 请求的方法类型名称
        LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
        if (args != null && parameterNames != null) {
            String param = "";
            for (int i = 0; i < args.length; i++) {
                param = " " + parameterNames[i] + " " + args[i];
            }
            sysLog.setParams(param);
        }
        sysLog.setCreateTime(new Date());
        sysLog.setUsername("l-j-h");
        sysLog.setTime((int) time);
        HttpServletRequest httpServletRequest = HttpContextUtils.getHttpServletRequest();
        sysLog.setIp(IPUtils.getIpAddr(httpServletRequest));
        sysLogDao.saveSysLog(sysLog);
    }
}
