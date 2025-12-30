package com.bearclawvisions.dto.user;

public class RegisterDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    private boolean termsAndConditions;

    // No-arg constructor for Jackson deserialization, otherwise construction of JSON fails
    // could also be done with @JsonProperty
    public RegisterDto() {
        this.firstName = null;
        this.lastName = null;
        this.email = null;
        this.password = null;
        this.confirmPassword = null;
        this.termsAndConditions = false;
    }

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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setTermsAndConditions(boolean termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
    }
}
