package com.example.springribbon.redis.interceptor;

import com.example.springribbon.redis.annotion.Idempotent;
import com.example.springribbon.redis.util.TokenUtis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class IdempotentInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenUtis tokenUtis;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Idempotent methodAnnotation = method.getAnnotation(Idempotent.class);
        if (methodAnnotation != null) {
            // token 校验
            return tokenUtis.verifyToken(request);
        }
        return true;
    }
}
