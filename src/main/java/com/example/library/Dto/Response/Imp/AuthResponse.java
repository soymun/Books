package com.example.library.Dto.Response.Imp;

import lombok.Data;

import javax.naming.ldap.PagedResultsControl;

@Data
public class AuthResponse implements Response{

    private Long id;

    private String token;
}
