package com.example.demobackend.auth.dto;

import com.example.demobackend.common.annotation.IsUuid;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDetailDto implements Serializable {

    @IsUuid
    private String uuid;

    @NotBlank
    @JsonProperty(value = "token_type")
    private String tokenType;

    @NotBlank
    private String token;

    @NotNull
    @JsonProperty(value = "expires_at")
    public Long expiration;

    @NotBlank
    @JsonProperty(value = "refresh_token")
    private String refreshToken;

    @NotNull
    @JsonProperty(value = "refresh_expires_at")
    private Long refreshExpiration;

}
