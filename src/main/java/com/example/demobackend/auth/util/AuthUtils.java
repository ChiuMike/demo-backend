package com.example.demobackend.auth.util;

import com.example.demobackend.config.WiIotConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class AuthUtils {

    @Autowired
    private WiIotConfig wiIotConfig;

    public String convertUsernameCase(@NonNull final String username) {
        return wiIotConfig.getIgnoreUsernameCase() ? username.toLowerCase() : username;
    }

    public String convertEmailCase(@NonNull final String email) {
        return wiIotConfig.getIgnoreEmailCase() ? email.toLowerCase() : email;
    }
}