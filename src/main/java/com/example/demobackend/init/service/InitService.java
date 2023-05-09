package com.example.demobackend.init.service;

import com.example.demobackend.exception.DuplicateDataException;
import com.example.demobackend.init.Role;
import com.example.demobackend.init.dto.InitDto;
import com.example.demobackend.init.dto.RegisterDto;
import com.example.demobackend.jwt.service.JwtService;
import com.example.demobackend.user.entity.UserEntity;
import com.example.demobackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InitService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Transactional
    public void register(RegisterDto registerDto) {

        Optional<UserEntity> userEntity = userRepository.findByUsername(registerDto.getUsername());

        if (userEntity.isPresent()) {
            throw new DuplicateDataException("duplicate user");
        }

        UserEntity user = UserEntity.builder()
                .username(registerDto.getUsername())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .role(Role.ADMIN)
                .uuid(UUID.randomUUID().toString())
                .build();

        userRepository.save(user);

    }


//    public InitDto authenticate(RegisterDto registerDto) {
//
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        registerDto.getUsername(),
//                        registerDto.getPassword()
//                )
//        );
//
//        var user = userRepository.findByUsername(registerDto.getUsername())
//                .orElseThrow();
//
//        var jwtToken = jwtService.generateToken(user);
//
//        return InitDto.builder()
//                .token(jwtToken)
//                .build();
//    }
}
