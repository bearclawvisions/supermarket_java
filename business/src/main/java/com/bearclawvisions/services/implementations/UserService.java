package com.bearclawvisions.services.implementations;

import com.bearclawvisions.dto.user.RegisterDto;
import com.bearclawvisions.dto.user.UserDto;
import com.bearclawvisions.entitities.User;
import com.bearclawvisions.enums.ApplicationRole;
import com.bearclawvisions.repositories.UserRepository;
import com.bearclawvisions.services.interfaces.IUserService;

import java.util.UUID;

public class UserService implements IUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    @Override
    public String createUser(RegisterDto userDto) throws IllegalArgumentException {
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        if (!userDto.acceptedTermsAndConditions()) {
            throw new IllegalArgumentException("Terms and conditions must be accepted");
        }

        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new IllegalArgumentException("Email already registered");
        }

        User newUser = User.registerUser(userDto);
        User savedUser = userRepository.save(newUser);

        if (savedUser.getId() == null) {
            throw new IllegalStateException("Failed to create user");
        }

        return "User created successfully";
    }

}
