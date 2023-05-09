package com.example.demobackend.jwt.entity.mapper;

import com.example.demobackend.jwt.object.Jwt;
import com.example.demobackend.jwt.entity.JwtEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JwtEntityMapper {

    Jwt toJwt(JwtEntity entity);

}