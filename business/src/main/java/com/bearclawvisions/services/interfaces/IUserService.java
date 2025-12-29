package com.bearclawvisions.services.interfaces;

import com.bearclawvisions.dto.user.RegisterDto;
import com.bearclawvisions.dto.user.UserDto;

public interface IUserService {
    UserDto getTestUser();
    String createUser(RegisterDto userDto);
}
