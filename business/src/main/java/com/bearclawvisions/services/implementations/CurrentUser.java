package com.bearclawvisions.services.implementations;

import com.bearclawvisions.enums.ApplicationRole;

import java.util.UUID;

public record CurrentUser(UUID id, String email, ApplicationRole role) {

    public CurrentUser(UUID id, String email, String role) {
        this(id, email, ApplicationRole.valueOf(role));
    }
}
