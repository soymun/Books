package com.example.library.Facade;

import com.example.library.Dto.Security.AuthenticationDto;
import com.example.library.Dto.Security.RegistrationDto;
import com.example.library.Dto.User.UserDto;
import com.example.library.Entity.Role;
import com.example.library.Exeption.FindDoublesException;
import com.example.library.Exeption.NotFoundException;
import com.example.library.Security.JwtTokenProvider;
import com.example.library.Service.Imp.UserServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthFacade {

    private final UserServiceImp userServiceImp;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> registration(RegistrationDto registrationFto) {

        if (userServiceImp.findEmail(registrationFto.getEmail())) {
            throw new FindDoublesException("User with this email, already exist");
        }
        registrationFto.setRole(Role.User);
        registrationFto.setPassword(passwordEncoder.encode(registrationFto.getPassword()));
        userServiceImp.saveUser(registrationFto);

        return ResponseEntity.status(201).build();
    }

    public ResponseEntity<?> authorization(AuthenticationDto authenticationDto) {
        try {
            UserDto user = userServiceImp.getUserByEmail(authenticationDto.getEmail());
            if (user == null) {
                throw new NotFoundException("User with email " + authenticationDto.getEmail() + ", not found");
            }

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDto.getEmail(), authenticationDto.getPassword()));

            String token = jwtTokenProvider.getToken(user.getEmail(), user.getRole());

            Map<String, Object> map = new HashMap<>();
            map.put("id", user.getId());
            map.put("role", user.getRole());
            map.put("token", token);

            return ResponseEntity.ok(map);
        } catch (AuthenticationException authenticationException){
            log.info("Authentication failed");
            throw new RuntimeException("Ошибка авторизации");
        }
    }
}
