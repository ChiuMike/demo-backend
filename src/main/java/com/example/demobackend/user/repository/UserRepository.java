package com.example.demobackend.user.repository;

import com.example.demobackend.user.entity.UserEntity;
import com.example.demobackend.user.object.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByUuid(String uuid);

    Optional<UserEntity> findByUsernameOrEmail(@NonNull final String username, @NonNull final String email);

}
