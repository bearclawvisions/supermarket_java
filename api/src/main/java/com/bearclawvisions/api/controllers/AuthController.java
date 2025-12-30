package com.bearclawvisions.api.controllers;

import com.bearclawvisions.api.contracts.ApiResponse;
import com.bearclawvisions.api.helpers.ApiResponseBuilder;
import com.bearclawvisions.api.helpers.JwtUtil;
import com.bearclawvisions.dto.user.JwtAuthResponseDto;
import com.bearclawvisions.dto.user.LoginDto;
import com.bearclawvisions.dto.user.RegisterDto;
import com.bearclawvisions.dto.user.UserDto;
import com.bearclawvisions.services.interfaces.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/auth", produces = "application/json")
public class AuthController {

    private final IUserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(IUserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> registerUser(@RequestBody RegisterDto userDto) {
        try {
            String result = userService.createUser(userDto);
            return ApiResponseBuilder.success(result);
        } catch (Exception e) {
            // todo GlobalExceptionHandler - middleware
            List<String> errors = List.of(e.getMessage());
            return ApiResponseBuilder.error("Failed to register user", errors);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JwtAuthResponseDto>> login(@RequestBody LoginDto loginDto) {
        try {
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
        } catch (IllegalArgumentException e) {
            List<String> errors = List.of(e.getMessage());
            return ApiResponseBuilder.error("Authentication failed", errors);
        } catch (Exception e) {
            List<String> errors = List.of(e.getMessage());
            return ApiResponseBuilder.error("Login failed", errors);
        }
    }
}
