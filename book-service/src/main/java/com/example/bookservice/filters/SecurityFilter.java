package com.example.bookservice.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class SecurityFilter extends GenericFilterBean {

    private final SecurityTokenContext securityTokenContext;

    public SecurityFilter(SecurityTokenContext securityTokenContext) {
        this.securityTokenContext = securityTokenContext;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String bearer = ((HttpServletRequest) servletRequest).getHeader("Authorization");
        if(bearer != null){
            securityTokenContext.setToken(bearer);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
