package com.bearclawvisions.api.controllers;

import com.bearclawvisions.dto.user.RegisterDto;
import com.bearclawvisions.services.interfaces.IUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final IUserService userService;

    public AuthController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterDto userDto) {
        return userService.createUser(userDto);
    }
}
