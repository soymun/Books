package com.example.library.Dto.User;

import com.example.library.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {

    private Long id;

    private String userName;

    private String email;

    private String password;

    private Role role;

    private Long summary;
}
