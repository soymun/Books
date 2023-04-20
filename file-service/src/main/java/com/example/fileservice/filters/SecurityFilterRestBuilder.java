package com.example.fileservice.filters;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class SecurityFilterRestBuilder extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final SecurityTokenContext securityTokenContext;

    public SecurityFilterRestBuilder(SecurityTokenContext securityTokenContext) {
        this.securityTokenContext = securityTokenContext;
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        SecurityFilter securityFilter = new SecurityFilter(securityTokenContext);
        builder.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
