package com.example.library.Controllers;

import com.example.library.Dto.MapObject.UserDto;
import com.example.library.Facade.ProfileFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sibrary")
@Slf4j
public class UserController {

    private final ProfileFacade profileFacade;

    @Autowired
    public UserController(ProfileFacade profileFacade) {
        this.profileFacade = profileFacade;
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<?> getProfile(@PathVariable Long id){
        log.info("Started get profile");
        ResponseEntity<?> response = profileFacade.getProfile(id);
        log.info("End get profile");
        return response;
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestBody UserDto userDto){
        log.info("Started update profile");
        ResponseEntity<?> response = profileFacade.updateProfile(userDto);
        log.info("End update profile");
        return response;
    }


    @DeleteMapping("/profile/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable Long id){
        log.info("Started delete profile");
        ResponseEntity<?> response = profileFacade.deleteUser(id);
        log.info("End delete profile");
        return response;
    }
}
