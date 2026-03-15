package com.bearclawvisions.ports;

import com.bearclawvisions.entitities.User;

public interface UserRepository {
    User findByEmail(String email);
    User findByFirstName(String firstName);
    User findByLastName(String lastName);
    User save(User user);
}
