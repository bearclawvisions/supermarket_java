package com.bearclawvisions.api.config;

import com.bearclawvisions.api.helpers.CustomJwtAuthToken;
import com.bearclawvisions.enums.ApplicationRole;
import com.bearclawvisions.services.implementations.CurrentUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.security.autoconfigure.actuate.web.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.UUID;

@Configuration // automagically injected by component scan
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true) // this allows for bean validation on endpoints with PreAuthorize
public class SecurityConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String jwkSetUri;

    @Bean
    @Order(1)
    public SecurityFilterChain actuatorFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher(EndpointRequest.toAnyEndpoint()) // specific to actuator endpoints, comes from package
                .authorizeHttpRequests(request -> request
                        .anyRequest().permitAll()
                );

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // c -> c.disable()
            .httpBasic(AbstractHttpConfigurer::disable) // h -> h.disable()
            .cors(Customizer.withDefaults())
            .authorizeHttpRequests(authorize -> {
                authorize.requestMatchers(
                        "/test/**",
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/v3/api-docs/**"
                ).permitAll();
                authorize.anyRequest().authenticated();
            })
            .oauth2ResourceServer(oauth2 -> oauth2
//                    .jwt(Customizer.withDefaults())
                    .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
            );

        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
    }

    private Converter<Jwt, AbstractAuthenticationToken> jwtAuthenticationConverter() {
        return jwt -> {
            try {
                Collection<GrantedAuthority> authorities = extractAuthorities(jwt);
                CurrentUser principal = extractPrincipal(jwt, authorities);

                return new CustomJwtAuthToken(jwt, authorities, principal);
            } catch (Exception e) {
                // Log the error to see what's happening
                System.err.println("Error converting JWT: " + e.getMessage());
                System.err.println("JWT Claims: " + jwt.getClaims());
                throw e;
            }
        };
    }

    // For using the PreAuthorize annotation
    private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
        converter.setAuthoritiesClaimName("role");
        converter.setAuthorityPrefix("ROLE_");
        return converter.convert(jwt);
    }

    // Construct the CurrentUser object
    private CurrentUser extractPrincipal(Jwt jwt, Collection<GrantedAuthority> authorities) {
        ApplicationRole role = ApplicationRole.valueOf(jwt.getClaimAsString("role"));

        return new CurrentUser(
                UUID.fromString(jwt.getClaimAsString("id")),
                jwt.getSubject(),
                role,
                authorities
        );
    }
}
