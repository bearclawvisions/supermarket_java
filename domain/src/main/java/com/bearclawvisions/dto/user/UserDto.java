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

    private UserDto(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.emailConfirmed = builder.emailConfirmed;
        this.createdOn = builder.createdOn;
        this.lastLogin = builder.lastLogin;
        this.role = builder.role;
    }

    // this can be simplified with Lombok @Builder annotation
    // this way building can be customized like with a fullname? firstName + " " + lastName
    public static class Builder {
        private UUID id;
        private String firstName;
        private String lastName;
        private String email;
        private boolean emailConfirmed;
        private String createdOn;
        private String lastLogin;
        private ApplicationRole role;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder emailConfirmed(boolean emailConfirmed) {
            this.emailConfirmed = emailConfirmed;
            return this;
        }

        public Builder createdOn(String createdDate) {
            this.createdOn = createdDate;
            return this;
        }

        public Builder lastLogin(String updatedDate) {
            this.lastLogin = updatedDate;
            return this;
        }

        public Builder role(ApplicationRole role) {
            this.role = role;
            return this;
        }

        public UserDto build() {
            return new UserDto(this);
        }
    }
}
