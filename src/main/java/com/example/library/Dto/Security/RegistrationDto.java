package com.example.library.Dto.Security;

import com.example.library.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {

    private String email;

    private String userName;

    private String password;

    private Role role;
}
