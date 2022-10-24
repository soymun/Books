package com.example.library.Dto.Response.Imp;

import com.example.library.Dto.Author.AuthorDto;
import com.example.library.Dto.Response.Response;
import lombok.Data;

import java.util.List;

@Data
public class AuthorListResponse implements Response {

    List<AuthorDto> authorDtos;
}
