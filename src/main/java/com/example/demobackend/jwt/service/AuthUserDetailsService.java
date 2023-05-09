package com.example.demobackend.jwt.service;

import com.example.demobackend.jwt.userdetails.AuthUserDetails;
import com.example.demobackend.user.entity.UserEntity;
import com.example.demobackend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Optional;

@Service
@Qualifier("jwtAuthUserDetails")
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public AuthUserDetails loadUserByUsername(String account) throws UsernameNotFoundException {

        final Optional<UserEntity> userEntityOptional = userRepository.findByUsername(account);

        if (userEntityOptional.isEmpty()) {
            // report status code 500
            throw new UsernameNotFoundException("");
        }

        UserEntity userEntity = userEntityOptional.get();

        String username = userEntity.getUsername();

        return new AuthUserDetails(userEntity.getUsername(), userEntity.getPassword(), new HashSet<>(),
                userEntity.getUuid());
    }
}
