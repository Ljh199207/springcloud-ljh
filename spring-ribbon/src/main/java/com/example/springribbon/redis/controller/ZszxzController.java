package com.example.springribbon.redis.controller;

import com.example.springribbon.redis.annotion.Idempotent;
import com.example.springribbon.redis.util.TokenUtis;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("zzz")
public class ZszxzController {

    @Autowired
    TokenUtis tokenUtis;

    @GetMapping("token")
    public String getToken() throws JSONException {
        String token = tokenUtis.generateToken();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);
        return token;
    }

    @Idempotent
    @GetMapping("test")
    public String testIdempotent() {
        return "校验成功";
    }
}
