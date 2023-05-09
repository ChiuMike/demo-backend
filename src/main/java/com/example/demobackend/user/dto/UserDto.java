package com.example.demobackend.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Integer id;

    @Size(min = 1, max = 50)
    private String username;

    @Size(min = 1, max = 50)
    private String password;

//    @Email
//    private String email;
}
