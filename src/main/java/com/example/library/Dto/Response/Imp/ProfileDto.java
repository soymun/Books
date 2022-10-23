package com.example.library.Dto.Response.Imp;

import com.example.library.Dto.Response.Response;
import lombok.Data;

@Data
public class ProfileDto implements Response {

    private Long id;

    private String email;

    private String username;
}
