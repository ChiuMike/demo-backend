package com.example.demobackend.user.entity;

import com.example.demobackend.init.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Table(name = "users", indexes = {
        @Index(name = "idx_user_uuid", columnList = "user_uuid"),
        @Index(name = "idx_user_hashid", columnList = "user_hashid")
}, uniqueConstraints= {
        @UniqueConstraint(name = "uk_user_uuid", columnNames= "user_uuid"),
        @UniqueConstraint(name = "uk_user_hashid", columnNames= "user_hashid"),
})
@SQLDelete(sql = "UPDATE users SET is_deleted = TRUE WHERE user_id = ?")
@Where(clause = "is_deleted = false OR is_deleted IS NULL")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "user_uuid", nullable = true, length = 36)
    private String uuid;

    @Column(name = "user_hashid", nullable = true, length = 32)
    private String hashId;

    @Column(name = "name", nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = true, length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder.Default
    @Column(name = "is_non_locked", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean accountNonLocked = false;

    @Builder.Default
    @Column(name = "is_enabled", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean enabled = false;

    @Builder.Default
    @Column(name = "is_deleted", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean deleted = false;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(role.name()));
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
