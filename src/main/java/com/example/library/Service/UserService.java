package com.example.library.Service;

import com.example.library.Dto.MapObject.UserDto;
import com.example.library.Entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User getUser(String email);

    boolean findEmail(String email);

    UserDto saveUser(User user);

    UserDto getUserProfile(Long id);

    void deleteUser(Long id);
}
