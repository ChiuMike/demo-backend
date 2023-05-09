package com.example.demobackend.auth.controller;

import com.example.demobackend.auth.dto.AccountDto;
import com.example.demobackend.auth.dto.LoginDetailDto;
import com.example.demobackend.auth.util.AuthContext;
import com.example.demobackend.http.EmptyDto;
import com.example.demobackend.http.Result;
import com.example.demobackend.jwt.config.JwtConfig;
import com.example.demobackend.jwt.object.Jwt;
import com.example.demobackend.jwt.service.JwtService;
import com.example.demobackend.jwt.userdetails.AuthUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtConfig jwtConfig;

    @PostMapping("/signin")
    public Result<LoginDetailDto> signIn(@RequestBody AccountDto accountDto) throws AuthenticationException {

        accountDto.setUsername(accountDto.getUsername());

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        accountDto.getUsername(),
                        accountDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();

        final String uuid = userDetails.getUuid();
        final Jwt jwt = jwtService.createJwtByUserUuid(uuid);

        LoginDetailDto detail = LoginDetailDto.builder()
                .uuid(uuid)
                .tokenType(jwtConfig.getTokenType())
                .token(jwt.getToken())
                .expiration(jwt.getExpiration().toEpochMilli())
                .refreshToken(jwt.getRefreshToken())
                .refreshExpiration(jwt.getRefreshExpiration().toEpochMilli())
                .build();

        return Result.ok(detail);
    }

    @GetMapping("/signout")
    public Result<EmptyDto> logout() {

        jwtService.deleteJwtByUserUuid(AuthContext.build().getUserUuid());

        return Result.ok();
    }
}
