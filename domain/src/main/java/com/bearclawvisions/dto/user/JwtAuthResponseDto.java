package com.bearclawvisions.dto.user;

import com.bearclawvisions.enums.ApplicationRole;

import java.util.UUID;

public class JwtAuthResponseDto {
    private final String token;
    private final String tokenType = "Bearer";
    private final String email;
    private final UUID id;
    private ApplicationRole role = ApplicationRole.NONE;

    // region Constructors
    public JwtAuthResponseDto(String token, String email, UUID id, ApplicationRole role) {
        this.token = token;
        this.email = email;
        this.id = id;
        this.role = role;
    }
    // endregion

    // region Getters
    public String getToken() {
        return token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getEmail() {
        return email;
    }

    public UUID getId() {
        return id;
    }
    public ApplicationRole getRole() {
        return role;
    }
    // endregion
}
