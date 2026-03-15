package com.bearclawvisions.repositories;

import com.bearclawvisions.entitities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
    User findByFirstName(String firstName);
    User findByLastName(String lastName);
}
