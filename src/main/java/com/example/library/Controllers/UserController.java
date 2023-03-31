package com.example.library.Controllers;

import com.example.library.Dto.User.UserUpdateDto;
import com.example.library.Facade.ProfileFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final ProfileFacade profileFacade;

    @GetMapping("/profile/{id}")
    public ResponseEntity<?> getProfile(@PathVariable Long id){
        return profileFacade.getProfile(id);
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestBody UserUpdateDto userDto){
        return profileFacade.updateProfile(userDto);
    }


    @DeleteMapping("/profile/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable Long id){
        return profileFacade.deleteUser(id);
    }
}
