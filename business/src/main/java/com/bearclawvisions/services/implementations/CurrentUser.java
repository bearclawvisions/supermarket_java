package com.bearclawvisions.services.implementations;

import com.bearclawvisions.enums.ApplicationRole;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.UUID;

public record CurrentUser(
        UUID id,
        String firstName,
        ApplicationRole role,
        Collection<? extends GrantedAuthority> permissions
) { }