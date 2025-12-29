package com.bearclawvisions.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // automagically injected by component scan
@EnableWebSecurity
public class SecurityConfig {

    @Bean // sort of auto DI with spring, default scope is singleton
    @Scope("singleton") // for some reason magic string? why not enum? has to be done manually, either create enum or custom Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(request -> request
                .requestMatchers(
                    "/test/**",
                    "/auth/**",
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/v3/api-docs/**"
                ).permitAll()
                .anyRequest().authenticated()
            );

        return http.build();
    }
}
