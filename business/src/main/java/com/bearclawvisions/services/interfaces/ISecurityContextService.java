package com.bearclawvisions.services.interfaces;

import com.bearclawvisions.enums.ApplicationRole;

import java.util.UUID;

public interface ISecurityContextService {
    UUID getCurrentUserId();
    String getCurrentUserEmail();
    ApplicationRole getCurrentUserRole();
}
