package com.bearclawvisions.dto.user;

public class RegisterDto {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final String confirmPassword;
    private final boolean termsAndConditions;

    public RegisterDto(String firstName, String lastName, String email, String password, String confirmPassword, boolean termsAndConditions) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.termsAndConditions = termsAndConditions;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public boolean acceptedTermsAndConditions() {
        return termsAndConditions;
    }
}
