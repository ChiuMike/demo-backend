package com.example.demobackend.auth.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto implements Serializable {

    @Size(min = 1, max = 50)
    private String username;

    @Size(min = 1, max = 50)
    private String password;
}
