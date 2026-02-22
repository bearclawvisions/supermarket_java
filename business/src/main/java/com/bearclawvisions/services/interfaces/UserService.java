package com.bearclawvisions.services.interfaces;

import com.bearclawvisions.dto.user.LoginDto;
import com.bearclawvisions.dto.user.RegisterDto;
import com.bearclawvisions.dto.user.UserDto;
import org.jspecify.annotations.NonNull;

public interface UserService {
    UserDto getTestUser();
    String createUser(RegisterDto userDto);
    UserDto loginUser(@NonNull LoginDto loginDto);
}
