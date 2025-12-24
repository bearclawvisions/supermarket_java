package com.bearclawvisions.entitities;

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

}
