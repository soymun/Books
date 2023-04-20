package com.example.fileservice.filters;

import org.springframework.stereotype.Component;

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
