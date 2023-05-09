package com.example.demobackend.user.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {

    private Long id;

    private String uuid;

    private String username;

    private String password;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;
}