package com.bearclawvisions.services.implementations;

import com.bearclawvisions.enums.ApplicationRole;
import com.bearclawvisions.services.interfaces.SecurityContextService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultSecurityContextService implements SecurityContextService {

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

    @Override
    public void validateUserRole(ApplicationRole requiredRole) throws SecurityException {
        ApplicationRole currentUserRole = getCurrentUserRole();
        if (currentUserRole != requiredRole) {
            throw new SecurityException("User role does not match required role");
        }
    }

    /**
     * Retrieves the current user from the security context.
     * @return The current user.
     * @throws IllegalStateException If the user is not authenticated.
     */
    private CurrentUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof CurrentUser)) {
            throw new IllegalStateException("User not authenticated");
        }
        return (CurrentUser) authentication.getPrincipal();
    }
}
