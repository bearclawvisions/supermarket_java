package com.bearclawvisions.api.controllers;

import com.bearclawvisions.api.contracts.ApiResponse;
import com.bearclawvisions.api.helpers.ApiResponseBuilder;
import com.bearclawvisions.dto.user.UserDto;
import com.bearclawvisions.services.interfaces.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/test", produces = "application/json")
public class TestController { // java has no [AllowAnonymous] like C#
    // due to spring security controller is protected by default
    // set [AllowAnonymous] in config which is .permitAll() in Spring Security configuration

    private final IUserService userService; // could also be automagically @Autowired

    public TestController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public ResponseEntity<ApiResponse<String>> hello() {
        // this creates a simple JSON response
        //Map.of("message", "Hello World, directly from API!")
        return ApiResponseBuilder.success("Hello World, directly from API!");
    }


    @GetMapping("/testuser")
    public ResponseEntity<ApiResponse<UserDto>> testUser() {
        var user = userService.getTestUser();

        if (user == null)
            return ApiResponseBuilder.error(HttpStatus.NOT_FOUND, "User not found");

        return ApiResponseBuilder.success(user);
    }
}
