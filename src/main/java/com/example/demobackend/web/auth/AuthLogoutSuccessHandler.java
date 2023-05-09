package com.example.demobackend.web.auth;

import com.example.demobackend.jwt.service.JwtService;
import com.example.demobackend.jwt.userdetails.AuthUserDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import java.io.IOException;
import java.util.Objects;

public class AuthLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    @Autowired
    private JwtService jwtService;

    @Override
    public void onLogoutSuccess(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Authentication authentication
    ) throws IOException, ServletException {
        if (!Objects.isNull(authentication)) {
            final AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();
            jwtService.deleteJwtByUserUuid(userDetails.getUuid());
        }

        super.onLogoutSuccess(httpServletRequest, httpServletResponse, authentication);
    }
}

