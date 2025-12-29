package com.bearclawvisions.repositories;

import com.bearclawvisions.entitities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
    User findByFirstName(String firstName);
    User findByLastName(String lastName);
}
