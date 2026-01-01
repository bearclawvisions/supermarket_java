package com.bearclawvisions.entitities;

import com.bearclawvisions.dto.user.RegisterDto;
import com.bearclawvisions.enums.ApplicationRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Size(max = 50)
    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Size(max = 50)
    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(nullable = false, updatable = false, name = "created_on")
    private final LocalDateTime createdOn = LocalDateTime.now(); // actually conflicts with postgresql CURRENT_TIMESTAMP, overrides database default

    @Column(nullable = false, name = "role")
    private ApplicationRole role;

    // region Constructors
    public User() {
        this.id = null;
        this.email = null;
        this.password = null;
        this.firstName = null;
        this.lastName = null;
        this.birthDate = null;
        this.lastLogin = null;
        this.role = ApplicationRole.NONE;
    }

    public User(String email, String password, String firstName, String lastName, ApplicationRole role) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public static User registerUser(RegisterDto newUser) {
        return new User(
                newUser.getEmail(),
                newUser.getPassword(),
                newUser.getFirstName(),
                newUser.getLastName(),
                ApplicationRole.CUSTOMER // self-registration is only for customers, other roles are managed by admin
        );
    }
    // endregion

    // region Getters
    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public ApplicationRole getRole() {
        return role;
    }
    // endregion
}
