package com.example.demobackend.jwt.repository.spec;

import com.example.demobackend.jwt.entity.JwtEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JwtSpecRepository extends JpaRepository<JwtEntity, Long>,
        JpaSpecificationExecutor<JwtEntity> {

    Optional<JwtEntity> findByRefreshToken(@NonNull String refreshToken);
}

