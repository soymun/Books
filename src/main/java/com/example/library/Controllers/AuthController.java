package com.example.library.Controllers;


import com.example.library.Dto.Security.AuthenticationDto;
import com.example.library.Dto.Security.RegistrationDto;
import com.example.library.Facade.AuthFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    public AuthFacade authFacade;

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody RegistrationDto registrationFto){
        log.info("Registration user started");
        return authFacade.registration(registrationFto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDto authenticationDto){
        log.info("Authentication started");
        return authFacade.authorization(authenticationDto);
    }
}
