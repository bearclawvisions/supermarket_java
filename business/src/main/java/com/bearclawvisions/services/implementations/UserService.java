package com.bearclawvisions.services.implementations;

import com.bearclawvisions.dto.user.UserDto;
import com.bearclawvisions.enums.ApplicationRole;
import com.bearclawvisions.services.interfaces.IUserService;

import java.util.UUID;

public class UserService implements IUserService {

    public UserService() {
    }

    @Override
    public UserDto getTestUser() {
        return new UserDto.Builder()
                .id(new UUID(1, 1))
                .firstName("Firstname")
                .lastName("Lastname")
                .email("test@mail.com")
                .emailConfirmed(false)
                .createdOn("2025-12-12")
                .lastLogin(null)
                .role(ApplicationRole.NONE)
                .build();
    }
}
