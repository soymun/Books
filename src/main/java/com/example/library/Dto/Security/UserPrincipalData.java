package com.example.library.Dto.Security;

import com.example.library.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@RequiredArgsConstructor
public class UserPrincipalData {

    private Long id;

    private String userName;

    private Role role;
}
