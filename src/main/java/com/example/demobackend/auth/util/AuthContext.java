package com.example.demobackend.auth.util;

import com.example.demobackend.exception.AuthenticationContextException;
import com.example.demobackend.jwt.userdetails.AuthUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class AuthContext {

    private final Authentication authentication;

    private final AuthUserDetails authUserDetails;

    public AuthContext() {

        this.authentication = SecurityContextHolder.getContext().getAuthentication();

        if (Objects.isNull(authentication)) {
            throw new AuthenticationContextException();
        }

        this.authUserDetails = (AuthUserDetails) authentication.getPrincipal();

        if (Objects.isNull(authUserDetails)) {
            throw new AuthenticationContextException();
        }
    }

    public Authentication getAuthentication() {
        return this.authentication;
    }

    public AuthUserDetails getUserDetails() {
        return this.authUserDetails;
    }

    public String getUserUuid() {
        return this.authUserDetails.getUuid();
    }

    public static AuthContext build() {
        return new AuthContext();
    }
}
