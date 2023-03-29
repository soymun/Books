package com.example.library.Mapping;

import com.example.library.Dto.Security.RegistrationDto;
import com.example.library.Dto.User.UserDto;
import com.example.library.Entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    UserDto userToUserDto(User user);

    User registrationToUser(RegistrationDto registrationDto);
}
