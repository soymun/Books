package com.example.library.Service;

import com.example.library.Dto.Security.RegistrationDto;
import com.example.library.Dto.User.UserDto;
import com.example.library.Dto.User.UserUpdateDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    boolean findEmail(String email);

    void saveUser(RegistrationDto registration);

    UserDto updateUser(UserUpdateDto userUpdateDto);

    UserDto getUserByEmail(String email);

    void deleteUser(Long id);

    UserDto getUserById(Long id);
}
