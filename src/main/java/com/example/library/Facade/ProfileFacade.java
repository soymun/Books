package com.example.library.Facade;

import com.example.library.Dto.User.UserDto;
import com.example.library.Dto.Response.FactoryResponse.FactoryResponse;
import com.example.library.Dto.Response.ResponseDto;
import com.example.library.Entity.User;
import com.example.library.Exeption.BadValues;
import com.example.library.Exeption.NotFoundException;
import com.example.library.Service.Imp.UserServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProfileFacade {

    private final UserServiceImp userServiceImp;

    private final FactoryResponse factoryResponse;

    @Autowired
    public ProfileFacade(UserServiceImp userServiceImp, FactoryResponse factoryResponse) {
        this.userServiceImp = userServiceImp;
        this.factoryResponse = factoryResponse;
    }

    public ResponseEntity<?> getProfile(Long id){
        if(id == null){
            log.debug("Id with profile is null");
            throw new BadValues("This profile, can't find");
        }

        UserDto userDto = userServiceImp.getUserById(id);
        if(userDto == null){
            log.debug("User with id {}, not found", id);
            throw new NotFoundException("User with id not found");
        }
        ResponseDto responseDto = factoryResponse.getResponse(userDto);
        log.info("Profile is get");
        return ResponseEntity.ok(responseDto);
    }

    public ResponseEntity<?> updateProfile(UserDto userDto){
        if(userDto == null){
            log.debug("User is null");
            throw new BadValues("This profile, can't find");
        }
        UserDto user = userServiceImp.getUserById(userDto.getId());
        if(user == null){
            log.debug("User with id {}, not found", userDto.getId());
            throw new NotFoundException("User with id not found");
        }
        log.info("User update start");
        User userUpdate = new User();
        userUpdate.setId(userDto.getId());
        userUpdate.setEmail(userDto.getEmail());
        userUpdate.setUserName(userDto.getUsername());
//        userUpdate.setRole(user.getRole());
//        userUpdate.setPassword(user.getPassword());
//        UserDto userSaved = userServiceImp.saveUser(userUpdate);
//        ResponseDto responseDto = factoryResponse.getResponse(userSaved);
//        log.info("User update end");
        return ResponseEntity.ok(null);
    }

    public ResponseEntity<?> deleteUser(Long id){
        if(id == null){
            log.debug("Id with profile is null");
            throw new BadValues("This profile, can't find");
        }
        log.info("Delete start");
        userServiceImp.deleteUser(id);
        log.info("Delete end");
        ResponseDto responseDto = factoryResponse.getResponse("User delete, suggest");
        return ResponseEntity.ok(responseDto);
    }
}
