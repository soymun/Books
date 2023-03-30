package com.example.library.Dto.Response.FactoryResponse;

import com.example.library.Dto.Author.AuthorDto;
import com.example.library.Dto.Response.Imp.*;
import com.example.library.Dto.Response.ResponseDto;
import com.example.library.Dto.User.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FactoryResponse {

    public ResponseDto getResponse(UserDto userDto){
        ResponseDto responseDto = new ResponseDto();
        ProfileDto profileDto = new ProfileDto();
        profileDto.setEmail(userDto.getEmail());
        profileDto.setUsername(userDto.getUsername());
        profileDto.setId(userDto.getId());
        responseDto.setResponse(profileDto);
        return responseDto;
    }

    public ResponseDto getResponse(String message){
        ResponseDto responseDto = new ResponseDto();
        Registration profileDto = new Registration();
        profileDto.setMessage(message);
        responseDto.setResponse(profileDto);
        return responseDto;
    }
    public ResponseDto getResponse(AuthorDto authorDto){
        ResponseDto responseDto = new ResponseDto();
        AuthorResponse authorResponse = new AuthorResponse();
        authorResponse.setId(authorDto.getId());
        authorResponse.setNameAuthor(authorDto.getNameAuthor());
        authorResponse.setSurnameAuthor(authorDto.getSurnameAuthor());
        responseDto.setResponse(authorResponse);
        return responseDto;
    }

    public ResponseDto getResponse(List<AuthorDto> authorDto){
        ResponseDto responseDto = new ResponseDto();
        AuthorListResponse authorResponse = new AuthorListResponse();
        authorResponse.setAuthorDtos(authorDto);
        responseDto.setResponse(authorResponse);
        return responseDto;
    }
}
