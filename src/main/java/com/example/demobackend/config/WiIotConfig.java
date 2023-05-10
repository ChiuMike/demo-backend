package com.example.demobackend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "wiiot")
@Component
public class WiIotConfig {

    private Boolean ignoreUsernameCase = false;

    private Boolean ignoreEmailCase = false;

    private String deviceRegisterKey = "secret";
}
