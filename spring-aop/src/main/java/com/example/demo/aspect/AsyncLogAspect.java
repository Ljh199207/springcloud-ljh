package com.example.demo.aspect;

import com.example.demo.annotation.AsyncLog;
import com.example.demo.bean.SysLog;
import com.example.demo.listenter.SysLogEvent;
import com.example.demo.mapper.SysLogDao;
import com.example.demo.util.HttpContextUtils;
import com.example.demo.util.IPUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.Date;

@Component
@Aspect
public class AsyncLogAspect {

    @Autowired
    private SysLogDao sysLogDao;

    private SysLog sysLog = new SysLog();

    private ThreadLocal<SysLog> sysLogThreadLocal = new ThreadLocal<>();
    /**
     * 事件发布是由ApplicationContext对象管控的，我们发布事件前需要注入ApplicationContext对象调用publishEvent方法完成事件发布
     **/
    @Autowired
    private ApplicationContext applicationContext;


    @Pointcut(value = "@annotation(com.example.demo.annotation.AsyncLog)")
    public void poinCut() {

    }


    @Before(value = "poinCut()")
    public void recordLog(JoinPoint  joinPoint) {
        saveSysLog(joinPoint);
    }

    @AfterReturning(value = "poinCut()", returning = "ret")
    public void doAfterReturning(Object ret) {
        sysLogThreadLocal.get();
        System.out.println(ret.toString());
        applicationContext.publishEvent(new SysLogEvent(sysLog));
    }

    @AfterThrowing(value = "poinCut()",throwing = "e")
    public void doAfterThrowable(Exception e){
        // 异常
        sysLog.setType(2);
        // 发布事件
        applicationContext.publishEvent(new SysLogEvent(sysLog));
    }
    private void saveSysLog(JoinPoint joinPoint) {
        long beginTime = Instant.now().toEpochMilli();
        //获取方法上签名对象
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取方法
        Method method = signature.getMethod();
        AsyncLog annotation = method.getAnnotation(AsyncLog.class);
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
        long endTime = Instant.now().toEpochMilli();
        sysLog.setTime((int) (endTime - beginTime));
        HttpServletRequest httpServletRequest = HttpContextUtils.getHttpServletRequest();
        sysLog.setIp(IPUtils.getIpAddr(httpServletRequest));
    }
}
