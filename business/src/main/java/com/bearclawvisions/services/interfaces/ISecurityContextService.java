package com.bearclawvisions.services.interfaces;

import com.bearclawvisions.enums.ApplicationRole;

import java.util.UUID;

public interface ISecurityContextService {
    UUID getCurrentUserId();
    String getCurrentUserEmail();
    ApplicationRole getCurrentUserRole();

    /**
     * Validates that the current user has the required role.
     * @param requiredRole The required role.
     * @throws SecurityException If the user does not have the required role.
     * @throws IllegalStateException If the user is not authenticated.
     */
    void validateUserRole(ApplicationRole requiredRole) throws SecurityException;
}
