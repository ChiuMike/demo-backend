package com.example.demobackend.jwt.object;

import com.example.demobackend.user.object.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Jwt {

    private Long id;

    private String token;

    private String refreshToken;

    private User user;

    private Instant expiration;

    private Instant refreshExpiration;
}
