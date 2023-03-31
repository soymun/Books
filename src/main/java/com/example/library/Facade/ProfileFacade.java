package com.example.library.Facade;

import com.example.library.Dto.User.UserUpdateDto;
import com.example.library.Exeption.BadValues;
import com.example.library.Service.Imp.UserServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProfileFacade {

    private final UserServiceImp userServiceImp;

    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<?> getProfile(Long id){
        if(id == null){
            throw new BadValues("This profile, can't find");
        }

        return ResponseEntity.ok(userServiceImp.getUserById(id));
    }

    public ResponseEntity<?> updateProfile(UserUpdateDto userDto){
        if(userDto == null){
            throw new BadValues("This profile, can't find");
        }

        if(userDto.getPassword() != null){
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        return ResponseEntity.ok(userServiceImp.updateUser(userDto));
    }

    public ResponseEntity<?> deleteUser(Long id){
        if(id == null){
            throw new BadValues("This profile, can't find");
        }
        userServiceImp.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
