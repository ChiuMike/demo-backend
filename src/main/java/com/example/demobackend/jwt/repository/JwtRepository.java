package com.example.demobackend.jwt.repository;

import com.example.demobackend.jwt.entity.JwtEntity;
import com.example.demobackend.jwt.repository.spec.JwtSpec;
import com.example.demobackend.jwt.repository.spec.JwtSpecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JwtRepository {

    @Autowired
    private JwtSpecRepository jwtSpecRepository;

    public JwtEntity save(JwtEntity entity) {
        return jwtSpecRepository.save(entity);
    }

    public void delete(JwtEntity entity) {
        jwtSpecRepository.delete(entity);
    }

    public Optional<JwtEntity> findByRefreshToken(@NonNull final String refreshToken) {
        return jwtSpecRepository.findByRefreshToken(refreshToken);
    }

    public Optional<JwtEntity> findByUserUuid(@NonNull final String userUuid) {
        Specification<JwtEntity> spec = JwtSpec.basicSpec();
        spec = spec.and(JwtSpec.userUuidEquals(userUuid));

        return jwtSpecRepository.findOne(spec);
    }

    public Optional<JwtEntity> findByUsername(@NonNull final String username) {
        Specification<JwtEntity> spec = JwtSpec.basicSpec();
        spec = spec.and(JwtSpec.usernameEquals(username));
        return jwtSpecRepository.findOne(spec);
    }
}
