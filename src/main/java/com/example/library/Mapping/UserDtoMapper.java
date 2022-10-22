package com.example.library.Mapping;

import com.example.library.Dto.MapObject.UserDto;
import com.example.library.Entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    UserDto userToUserDto(User user);
}
