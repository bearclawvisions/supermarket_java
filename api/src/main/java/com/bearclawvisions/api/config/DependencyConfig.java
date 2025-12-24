package com.bearclawvisions.api.config;

import com.bearclawvisions.services.implementations.UserService;
import com.bearclawvisions.services.interfaces.IUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DependencyConfig {

    @Bean
    public IUserService userService() {
        return new UserService();
    }
}
