package com.example.library.Dto.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserGetDto {

    private Long id;

    private String userName;
}
