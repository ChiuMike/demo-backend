package com.example.demobackend.init.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InitDto {

    @Size(min = 1, max = 50)
    private String username;

    @Size(min = 1, max = 50)
    private String password;

    @Size(min = 1, max = 50)
    private String email;

    @Size(min = 1, max = 50)
    private String tenantName = "WiBASE";

}
