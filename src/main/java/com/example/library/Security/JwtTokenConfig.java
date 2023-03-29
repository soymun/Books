package com.example.library.Security;

import com.example.library.Dto.Security.UserPrincipalData;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtTokenConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserPrincipalData userPrincipalData;

    public JwtTokenConfig(JwtTokenProvider jwtTokenProvider, UserPrincipalData userPrincipalData) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userPrincipalData = userPrincipalData;
    }

    @Override
    public void configure(HttpSecurity builder) {
        JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(jwtTokenProvider, userPrincipalData);
        builder.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
