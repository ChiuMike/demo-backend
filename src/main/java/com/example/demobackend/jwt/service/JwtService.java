package com.example.demobackend.jwt.service;

import com.example.demobackend.jwt.object.Jwt;
import com.example.demobackend.jwt.config.JwtConfig;
import com.example.demobackend.jwt.entity.JwtEntity;
import com.example.demobackend.jwt.entity.mapper.JwtEntityMapper;
import com.example.demobackend.jwt.repository.JwtRepository;
import com.example.demobackend.user.entity.UserEntity;
import com.example.demobackend.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private JwtRepository jwtRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtEntityMapper jwtEntityMapper;

    public String extractUsername(String jwtToken) {

        return extractClaim(jwtToken, Claims::getSubject);
    }

    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {

        //after extract = {sub=Mike, iat=1682950072, exp=1682950158}
        final Claims claims = extractAllClaims(jwtToken);

        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {

        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {

        byte[] keyBytes = Decoders.BASE64.decode(jwtConfig.getSigningKey());

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaim,
            UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaim)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration((new Date(System.currentTimeMillis() + jwtConfig.getAccessTokenExpiration())))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private String createAccessToken(
            @NonNull String subject,
            @NonNull String userUuid,
            @NonNull Date issuedAt,
            @NonNull Date expiration
    ) {
        return Jwts.builder()
                .setSubject(subject)
                .claim("UUID", userUuid)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Jwt createJwtByUserUuid(@NonNull String uuid) {

        Optional<JwtEntity> jwtOptional = jwtRepository.findByUserUuid(uuid);

        if (jwtOptional.isPresent()) {
            final JwtEntity jwtEntity = jwtOptional.get();
            final Instant refreshExpiration = jwtEntity.getRefreshExpiration();
            final Instant tokenExpiration = jwtEntity.getExpiration();
            final Instant currentTimeMillis = Instant.now();

            if (currentTimeMillis.isBefore(refreshExpiration)) {
                if (currentTimeMillis.isBefore(tokenExpiration)) {
                    return jwtEntityMapper.toJwt(jwtEntity);
                }

                if (currentTimeMillis.isAfter(tokenExpiration)) {
                    deleteJwtByUserUuid(uuid);
                    return createJwt(uuid);
                }
            }

            if (currentTimeMillis.isAfter(refreshExpiration)) {
                deleteJwtByUserUuid(uuid);
                return createJwt(uuid);
            }
        }

        return createJwt(uuid);
    }

    public void deleteJwtByUserUuid(@NonNull String uuid) {

        jwtRepository.findByUserUuid(uuid).ifPresent(entity -> jwtRepository.delete(entity));

    }

    private Jwt createJwt(@NonNull String uuid) {

        final UserEntity user = userRepository.findByUuid(uuid)
                .orElseThrow(NoSuchElementException::new);

        final Instant currentTimeMillis = Instant.now();

        final Instant expiration = currentTimeMillis
                .plusMillis(jwtConfig.getAccessTokenExpiration() * 1000);

        final Instant refreshExpiration = currentTimeMillis
                .plusMillis(jwtConfig.getRefreshTokenExpiration() * 1000);

//        final String token = this.createAccessToken(user.getUsername(), user.getUuid(),
//                Date.from(currentTimeMillis), Date.from(expiration), new HashMap<>(), user);

        final String token = this.createAccessToken(user.getUsername(), user.getUuid(),
                Date.from(currentTimeMillis), Date.from(expiration));


        final String refreshToken = this.createRefreshToken();

        JwtEntity entity = JwtEntity.builder()
                .user(user)
                .token(token)
                .expiration(expiration)
                .refreshToken(refreshToken)
                .refreshExpiration(refreshExpiration)
                .build();

        jwtRepository.save(entity);

        return jwtEntityMapper.toJwt(entity);
    }

    private String createRefreshToken() {
        return Sha512DigestUtils.shaHex(UUID.randomUUID().toString());
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {

        final String username = extractUsername(token);

        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {

        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {

        return extractClaim(token, Claims::getExpiration);
    }
}
