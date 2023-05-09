package com.example.demobackend.init.controller;

import com.example.demobackend.http.EmptyDto;
import com.example.demobackend.http.Result;
import com.example.demobackend.init.dto.InitDto;
import com.example.demobackend.init.dto.RegisterDto;
import com.example.demobackend.init.service.InitService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/init")
@RequiredArgsConstructor
public class InitController {

    @Autowired
    private InitService initService;

    @PostMapping("/register")
    public Result<EmptyDto> register(
            @RequestBody RegisterDto registerDto
    ) {

        initService.register(registerDto);

        return Result.ok();
    }

//    @PostMapping("/authenticate")
//    public ResponseEntity<InitDto> authenticate(
//            @RequestBody RegisterDto registerDto
//    ) {
//        return ResponseEntity.ok(initService.authenticate(registerDto));
//    }
}
