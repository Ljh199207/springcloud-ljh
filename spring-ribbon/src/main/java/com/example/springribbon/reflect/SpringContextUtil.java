package com.example.springribbon.reflect;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class SpringContextUtil {

    public static ApplicationContext applicationContext;

    @Autowired
    public void setApplicationContext(ApplicationContext context) {
        this.applicationContext = context;
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static Object methodInvoke(String beanMethod, Map<String, Object> params) {
        String[] a = StringUtils.split(beanMethod, ".");
        String beanName = a[0];
        String methodName = a[1];
        Object bean = applicationContext.getBean(beanName);
        AtomicReference<Method> method = new AtomicReference<>();
        Optional.ofNullable(params).ifPresentOrElse(p -> {
            method.set(ReflectionUtils.findMethod(bean.getClass(), methodName, new Class[]{Map.class}));
        }, () -> {
            method.set(ReflectionUtils.findMethod(bean.getClass(), methodName));
        });
        AtomicReference<Object> o = new AtomicReference<>();
        Optional.ofNullable(params).ifPresentOrElse(m ->
        {
            o.set(ReflectionUtils.invokeMethod(method.get(), bean, new Object[]{params}));
        }, () -> {
            o.set(ReflectionUtils.invokeMethod(method.get(), bean));
        });
        return o.get();
    }
}
