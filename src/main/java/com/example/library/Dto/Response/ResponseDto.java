package com.example.library.Dto.Response;

import com.example.library.Dto.Response.Imp.Response;
import lombok.Data;

@Data
public class ResponseDto {

    private Response response;

    private String error;
}
