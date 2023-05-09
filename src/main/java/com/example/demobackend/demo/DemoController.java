package com.example.demobackend.demo;

import com.example.demobackend.http.EmptyDto;
import com.example.demobackend.http.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

    @GetMapping
    public Result<String> sayHello() {

        return Result.ok("hi i am good!!!");
    }
}
