package com.bearclawvisions.entitities;

import com.bearclawvisions.dto.user.RegisterDto;
import com.bearclawvisions.dto.user.UserDto;
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
    private final LocalDateTime createdOn = LocalDateTime.now();

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

    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static User registerUser(RegisterDto newUser) {
        return new User(
            newUser.getEmail(),
            newUser.getPassword(),
            newUser.getFirstName(),
            newUser.getLastName()
        );
    }
}
