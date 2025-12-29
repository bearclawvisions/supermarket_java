package com.bearclawvisions.api.controllers;

import com.bearclawvisions.api.contracts.ApiResponse;
import com.bearclawvisions.api.helpers.ApiResponseBuilder;
import com.bearclawvisions.dto.user.RegisterDto;
import com.bearclawvisions.services.interfaces.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final IUserService userService;

    public AuthController(IUserService userService) {
        this.userService = userService;
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
}
