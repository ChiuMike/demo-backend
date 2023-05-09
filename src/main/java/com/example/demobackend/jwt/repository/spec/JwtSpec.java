package com.example.demobackend.jwt.repository.spec;

import com.example.demobackend.jwt.entity.JwtEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

public class JwtSpec {

    public static Specification<JwtEntity> basicSpec() {
        Specification<JwtEntity> spec = Specification.where(null);
        spec = spec.and(JwtSpec.userNonDeleted());
        return spec;
    }

    public static Specification<JwtEntity> userIdEquals(@NonNull Long id) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(
                root.get("user").get("id"), id
        );
    }

    public static Specification<JwtEntity> userUuidEquals(@NonNull String uuid) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(
                root.get("user").get("uuid"), uuid
        );
    }

    public static Specification<JwtEntity> usernameEquals(@NonNull String username) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(
                root.get("user").get("username"), username
        );
    }

    public static Specification<JwtEntity> userNonDeleted() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(
                root.get("user").get("deleted"), false
        );
    }
}