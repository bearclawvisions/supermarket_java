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
        return new UserDto(
                new UUID(1, 1),
                "Firstname",
                "Lastname",
                "test@mail.com",
                false,
                "2025-12-12",
                null,
                ApplicationRole.NONE
        );
    }
}
