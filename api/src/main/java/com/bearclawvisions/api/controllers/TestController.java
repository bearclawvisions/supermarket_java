package com.bearclawvisions.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController { // java has no [AllowAnonymous] like C#
    // due to spring security controller is protected by default
    // set [AllowAnonymous] in config which is .permitAll() in Spring Security configuration

    @GetMapping("/hello")
    public String hello() {
        return "Hello World, directly from API!";
    }
}
