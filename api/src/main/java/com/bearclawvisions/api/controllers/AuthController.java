package com.bearclawvisions.api.controllers;

import com.bearclawvisions.api.contracts.ApiResponse;
import com.bearclawvisions.api.helpers.ApiResponseBuilder;
import com.bearclawvisions.api.helpers.JwtUtil;
import com.bearclawvisions.dto.user.JwtAuthResponseDto;
import com.bearclawvisions.dto.user.LoginDto;
import com.bearclawvisions.dto.user.RegisterDto;
import com.bearclawvisions.dto.user.UserDto;
import com.bearclawvisions.services.interfaces.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> registerUser(@RequestBody RegisterDto userDto) {
        String result = userService.createUser(userDto);
        return ApiResponseBuilder.success(result);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JwtAuthResponseDto>> login(@RequestBody LoginDto loginDto) {
        UserDto user = userService.loginUser(loginDto);

        String token = jwtUtil.generateToken(
                user.getId(),
                user.getEmail(),
                user.getRole().toString()
        );

        JwtAuthResponseDto authResponse = new JwtAuthResponseDto(
                token,
                user.getEmail(),
                user.getId(),
                user.getRole()
        );

        return ApiResponseBuilder.success(authResponse);
    }
}
