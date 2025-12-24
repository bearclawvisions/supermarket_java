package com.bearclawvisions.api.controllers;

import com.bearclawvisions.services.implementations.UserService;
import com.bearclawvisions.services.interfaces.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController { // java has no [AllowAnonymous] like C#
    // due to spring security controller is protected by default
    // set [AllowAnonymous] in config which is .permitAll() in Spring Security configuration

    final private IUserService userService; // could also be automagically @Autowired

    public TestController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World, directly from API!";
    }

    @GetMapping("/testuser")
    public String testUser() {
        var user = userService.getTestUser();

        if (user == null)
            return "No test user found!";

        return "Test from service success!";
    }
}
