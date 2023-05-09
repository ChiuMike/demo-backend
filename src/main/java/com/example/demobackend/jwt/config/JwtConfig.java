package com.example.demobackend.jwt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "jwt")
@Component
public class JwtConfig {

    private Long accessTokenExpiration = 86400L;

    private Long refreshTokenExpiration = 604800L;

    private String tokenType = "Bearer";

    private String signingKey = "404D635166546A576E5A7234753778214125432A462D4A614E645267556B5870";
}
