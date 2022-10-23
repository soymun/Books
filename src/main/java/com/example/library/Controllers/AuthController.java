package com.example.library.Controllers;


import com.example.library.Dto.User.AuthenticationDto;
import com.example.library.Dto.User.RegistrationFto;
import com.example.library.Facade.AuthFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/sibrary")
@Slf4j
public class AuthController {

    public AuthFacade authFacade;

    @Autowired
    public AuthController(AuthFacade authFacade) {
        this.authFacade = authFacade;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody RegistrationFto registrationFto){
        log.info("Registration user started");
        ResponseEntity<?> response = authFacade.registration(registrationFto);
        log.info("Registration user closed");
        return response;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDto authenticationDto){
        log.info("Authentication started");
        ResponseEntity<?> response = authFacade.authorization(authenticationDto);
        log.info("Authorication closed");
        return response;
    }

    @DeleteMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response){
        log.info("Logout started");
        authFacade.logout(request, response);
        log.info("Logout closed");
    }

    @PostMapping("/user/author")
    public ResponseEntity<?> stateAuthor(@RequestBody String email){
        log.info("User author start");
        return authFacade.createAuthor(email);
    }
}
