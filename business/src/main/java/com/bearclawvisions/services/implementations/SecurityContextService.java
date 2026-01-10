package com.bearclawvisions.services.implementations;

import com.bearclawvisions.enums.ApplicationRole;
import com.bearclawvisions.services.interfaces.ISecurityContextService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

public class SecurityContextService implements ISecurityContextService {

    @Override
    public UUID getCurrentUserId() {
        CurrentUser user = getCurrentUser();
        return user.id();
    }

    @Override
    public String getCurrentUserEmail() {
        CurrentUser user = getCurrentUser();
        return user.email();
    }

    @Override
    public ApplicationRole getCurrentUserRole() {
        CurrentUser user = getCurrentUser();
        return user.role();
    }

    private CurrentUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof CurrentUser)) {
            throw new IllegalStateException("User not authenticated");
        }
        return (CurrentUser) authentication.getPrincipal();
    }
}
