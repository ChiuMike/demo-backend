package com.example.demobackend.jwt.config;

import com.example.demobackend.exception.AuthAccessDeniedHandler;
import com.example.demobackend.jwt.filter.JwtAuthenticationFilter;
import com.example.demobackend.web.auth.AuthLogoutSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    
    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    public final String[] DEFAULT_ALLOWED_URI_LIST = {
            "/api/v1/init/**",
            "/api/v1/auth/signin"
    };

    @Bean
    public AuthLogoutSuccessHandler sessionLogoutSuccessHandlerBean() {
        return new AuthLogoutSuccessHandler();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .cors()
            .and()
            .csrf()
            .disable()

            .exceptionHandling()
            .accessDeniedHandler(new AuthAccessDeniedHandler())
            .and()
            .logout()
            .logoutSuccessHandler(this.sessionLogoutSuccessHandlerBean())
            .and()

            .authorizeHttpRequests()
            .requestMatchers(this.DEFAULT_ALLOWED_URI_LIST)
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
