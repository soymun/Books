package com.example.library.Security;

import com.example.library.Dto.Security.PersonDetails;
import com.example.library.Dto.Security.UserPrincipalData;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserPrincipalData userPrincipalData;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider, UserPrincipalData userPrincipalData) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userPrincipalData = userPrincipalData;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);
        if(token != null && jwtTokenProvider.validateToken(token)){
            Authentication authentication = jwtTokenProvider.authentication(token);
            if(authentication != null){
                SecurityContextHolder.getContext().setAuthentication(authentication);
                PersonDetails personDetails = (PersonDetails) authentication.getDetails();
                userPrincipalData.setId(personDetails.getId());
                userPrincipalData.setUserName(personDetails.getUsername());
                userPrincipalData.setRole(personDetails.getRole());
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
