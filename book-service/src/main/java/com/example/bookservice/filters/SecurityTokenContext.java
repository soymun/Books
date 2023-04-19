package com.example.bookservice.filters;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class SecurityTokenContext {

    private ThreadLocal<String> token;

    public void setToken(String token){
        ThreadLocal<String> threadLocalToken = new ThreadLocal<>();
        threadLocalToken.set(token);
        this.token = threadLocalToken;
    }

    public String getToken(){
        return token.get();
    }
}
