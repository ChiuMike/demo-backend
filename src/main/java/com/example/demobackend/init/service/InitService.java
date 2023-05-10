package com.example.demobackend.init.service;

import com.example.demobackend.auth.util.AuthUtils;
import com.example.demobackend.exception.DuplicateDataException;
import com.example.demobackend.init.Role;
import com.example.demobackend.init.dto.InitDto;
import com.example.demobackend.user.entity.UserEntity;
import com.example.demobackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    private AuthUtils authUtils;

    @Transactional
    public void register(InitDto initDto) {

        Optional<UserEntity> userEntity = userRepository.findByUsername(initDto.getUsername());

        if (userEntity.isPresent()) {
            throw new DuplicateDataException("duplicate user");
        }

        UserEntity user = UserEntity.builder()
                .username(authUtils.convertUsernameCase(initDto.getUsername()))
                .password(passwordEncoder.encode(initDto.getPassword()))
                .role(Role.ADMIN)
                .uuid(UUID.randomUUID().toString())
                .email(authUtils.convertEmailCase(initDto.getEmail()))
                .accountNonLocked(true)
                .enabled(true)
                .build();

        userRepository.save(user);

    }
}
