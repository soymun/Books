package com.example.library.Security;

import com.example.library.Entity.Role;
import com.example.library.Service.Imp.UserServiceImp;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {

    private final UserServiceImp userServiceImp;

    @Autowired
    public JwtTokenProvider(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @Value("${jwt.provider.security}")
    private String security;

    @Value("${jwt.provider.validate}")
    private Long validityInMillisecond;

    @PostConstruct
    public void init(){
        security = Base64.getEncoder().encodeToString(security.getBytes(StandardCharsets.UTF_8));
    }

    public String getToken(String email, Role role){
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role", role);

        Date issue = new Date();
        Date ex = new Date(issue.getTime() + validityInMillisecond);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(issue)
                .setExpiration(ex)
                .signWith(SignatureAlgorithm.HS256, security)
                .compact();
    }

    public String getEmail(String token){
        return Jwts.parser().setSigningKey(security).parseClaimsJws(token).getBody().getSubject();
    }

    public Authentication authentication(String token){
        UserDetails userDetails = userServiceImp.loadUserByUsername(getEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest request){
        String bearer = request.getHeader("Authorization");
        if(bearer != null && bearer.startsWith("Bearer ")){
            return bearer.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token){
        try {
            Jws<Claims> jwtClaims = Jwts.parser().setSigningKey(security).parseClaimsJws(token);
            return !jwtClaims.getBody().getExpiration().before(new Date());
        } catch (Exception e){
            return false;
        }
    }
}
