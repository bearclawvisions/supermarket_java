package com.bearclawvisions.dto.user;

import com.bearclawvisions.enums.ApplicationRole;

import java.util.UUID;

public class UserDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean emailConfirmed;
    private String createdOn;
    private String lastLogin;
    private ApplicationRole role;

    public UserDto(
            UUID id,
            String firstName,
            String lastName,
            String email,
            boolean emailConfirmed,
            String createdOn,
            String lastLogin,
            ApplicationRole role
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.emailConfirmed = emailConfirmed;
        this.createdOn = createdOn;
        this.lastLogin = lastLogin;
        this.role = role;
    }
}
