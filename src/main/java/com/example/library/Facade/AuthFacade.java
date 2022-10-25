package com.example.library.Facade;

import com.example.library.Dto.Response.FactoryResponse.FactoryResponse;
import com.example.library.Dto.Response.Imp.AuthResponse;
import com.example.library.Dto.Response.Imp.Registration;
import com.example.library.Dto.Response.ResponseDto;
import com.example.library.Dto.User.AuthenticationDto;
import com.example.library.Dto.User.RegistrationFto;
import com.example.library.Entity.Role;
import com.example.library.Entity.User;
import com.example.library.Exeption.BadValues;
import com.example.library.Exeption.FindDoulesException;
import com.example.library.Exeption.NotFoundException;
import com.example.library.Security.JwtTokenProvider;
import com.example.library.Service.Imp.UserServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class AuthFacade {

    private final UserServiceImp userServiceImp;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final FactoryResponse factoryResponse;

    @Autowired
    public AuthFacade(UserServiceImp userServiceImp, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, FactoryResponse factoryResponse) {
        this.userServiceImp = userServiceImp;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.factoryResponse = factoryResponse;
    }

    public ResponseEntity<?> registration(RegistrationFto registrationFto){
        if(userServiceImp.findEmail(registrationFto.getEmail())){
            log.debug("User found with this email {}", registrationFto.getEmail());
            throw new FindDoulesException("User with this email, has already");
        }
        User user = new User();
        log.info("User creating");
        user.setEmail(registrationFto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationFto.getPassword()));
        user.setUsername(registrationFto.getUserName());
        user.setRole(Role.User);

        userServiceImp.saveUser(user);
        log.info("User created");
        ResponseDto responseDto = factoryResponse.getResponse("User save, performed");
        log.info("Response created");
        return ResponseEntity.ok(responseDto);
    }

    public ResponseEntity<?> authorization(AuthenticationDto authenticationDto){
        try {
            User user = userServiceImp.getUser(authenticationDto.getEmail());
            if(user == null){
                log.info("User with email {}, not found", authenticationDto.getEmail());
                throw new NotFoundException("User with this id, not found");
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authenticationDto.getEmail(), authenticationDto.getPassword());
            authenticationManager.authenticate(authenticationToken);
            log.info("User authentication");
            String token = jwtTokenProvider.getToken(user.getEmail(), user.getRole());
            log.info("Token created");
            ResponseDto responseDto = factoryResponse.getResponse(user.getId(), token);
            log.info("Response created");
            return ResponseEntity.ok(responseDto);
        }
        catch (AuthenticationException e){
            throw new BadValues(e.getMessage());
        }
    }

    public void logout(HttpServletRequest request, HttpServletResponse response){
        log.info("User logout");
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }

    public ResponseEntity<?> createAuthor(String email){
        if(email.isEmpty()){
            log.debug("User can't find");
            throw new BadValues("Email is invalid");
        }

        log.info("User is author started");
        User user = userServiceImp.getUser(email);
        user.setRole(Role.AUTHOR);
        log.info("User is author");
        ResponseDto responseDto = factoryResponse.getResponse(userServiceImp.saveUser(user));
        return ResponseEntity.ok(responseDto);
    }
}
