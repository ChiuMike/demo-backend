package com.example.demobackend.jwt.entity;

import com.example.demobackend.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.ZonedDateTime;

@Table(name = "jwts", indexes = {
        @Index(name = "idx_access_token", columnList = "access_token"),
        @Index(name = "idx_refresh_token", columnList = "refresh_token")
}, uniqueConstraints= {
        @UniqueConstraint(name = "uk_access_token", columnNames= "access_token"),
        @UniqueConstraint(name = "uk_refresh_token", columnNames= "refresh_token"),
})
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JwtEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "access_token", nullable = false)
    private String token;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private UserEntity user;

    @Column(name = "expiration", nullable = false)
    private Instant expiration;

    @Column(name = "refresh_expiration", nullable = false)
    private Instant refreshExpiration;
}
