package com.example.springribbon.redis.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Component
public class TokenUtis {

    @Autowired
    RedisUtils redisUtils;
    // token 过期时间为30秒
    private final static Long TOKEN_EXPIRE = 30L;

    private final static String TOKEN_NAME = "token";

    /**
     * 生成token 放入缓存</p>
     */
    public String generateToken() {
        String uuid = UUID.randomUUID().toString();
        String token = DigestUtils.md5DigestAsHex(uuid.getBytes());
        redisUtils.set(TOKEN_NAME, token, TOKEN_EXPIRE);
        return token;
    }

    public boolean verifyToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_NAME);
        if (StringUtils.isEmpty(token)) {
            // 抛出自定义异常
            System.out.println("token不存在");
            return false;
        }
        if (!redisUtils.hasKey(TOKEN_NAME)) {
            System.out.println("token已经过期");
            return false;
        }
        String cacheToken = (String) redisUtils.get(TOKEN_NAME);
        if (!token.equals(cacheToken)) {
            System.out.println("token校验失败");
            return false;
        }
        // 移除token
        Boolean del = redisUtils.del(TOKEN_NAME);
        if (!del) {
            System.out.println("移除token失败");
            return false;
        }
        return true;
    }


}
