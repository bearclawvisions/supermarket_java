package com.bearclawvisions.dto.user;

public record JwtAuthResponseDto(String token) {

    public String getTokenType() {
        return "Bearer";
    }
}
