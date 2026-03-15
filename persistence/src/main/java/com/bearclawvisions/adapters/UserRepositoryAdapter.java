package com.bearclawvisions.adapters;

import com.bearclawvisions.entitities.User;
import com.bearclawvisions.ports.UserRepository;
import com.bearclawvisions.repositories.JpaUserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryAdapter implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryAdapter(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public User findByEmail(String email) {
        return jpaUserRepository.findByEmail(email);
    }

    @Override
    public User findByFirstName(String firstName) {
        return jpaUserRepository.findByFirstName(firstName);
    }

    @Override
    public User findByLastName(String lastName) {
        return jpaUserRepository.findByLastName(lastName);
    }

    @Override
    public User save(User user) {
        return jpaUserRepository.save(user);
    }
}
