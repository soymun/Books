package com.example.bookservice.filters;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class SecurityTokenContext {

    public HttpEntity<String> getToken(){
        Jwt principal = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " +  principal.getTokenValue());

        return new HttpEntity<>(headers);
    }
}
