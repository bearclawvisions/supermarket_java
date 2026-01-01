package com.bearclawvisions.dto.user;

public class LoginDto {
    private final String email;
    private final String password;

    // region Constructors
    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
    // endregion

    // region Getters
    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
    // endregion
}
