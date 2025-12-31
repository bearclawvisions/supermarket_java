package com.bearclawvisions.services.implementations;

import com.bearclawvisions.dto.user.LoginDto;
import com.bearclawvisions.dto.user.RegisterDto;
import com.bearclawvisions.dto.user.UserDto;
import com.bearclawvisions.entitities.User;
import com.bearclawvisions.enums.ApplicationRole;
import com.bearclawvisions.exceptions.BusinessException;
import com.bearclawvisions.repositories.UserRepository;
import com.bearclawvisions.services.interfaces.IUserService;
import org.jspecify.annotations.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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
    public String createUser(@NonNull RegisterDto registerDto) throws BusinessException {
        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            throw new BusinessException("Passwords do not match");
        }

        String plainPassword = registerDto.getPassword();
        String encodedPassword = passwordEncoder.encode(plainPassword);
        registerDto.setPassword(encodedPassword);

        if (!registerDto.acceptedTermsAndConditions()) {
            throw new BusinessException("Terms and conditions must be accepted");
        }

        if (userRepository.findByEmail(registerDto.getEmail()) != null) {
            throw new BusinessException("Email already registered");
        }

        User newUser = User.registerUser(registerDto);
        User savedUser = userRepository.save(newUser);

        if (savedUser.getId() == null) {
            throw new BusinessException("Failed to create user");
        }

        return "User created successfully";
    }

    @Override
    public UserDto loginUser(@NonNull LoginDto loginDto) throws BusinessException {
        User user = userRepository.findByEmail(loginDto.getEmail());

        if (user == null) {
            throw new BusinessException("Invalid email or password");
        }

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new BusinessException("Invalid email or password");
        }

        return new UserDto.Builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .emailConfirmed(true)
                .createdOn(user.getCreatedOn().toString())
                .lastLogin(user.getLastLogin() != null ? user.getLastLogin().toString() : null)
                .role(user.getRole())
                .build();
    }
}
