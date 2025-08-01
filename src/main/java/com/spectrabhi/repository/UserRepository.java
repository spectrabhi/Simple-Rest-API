package com.spectrabhi.repository;

import com.spectrabhi.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Additional query methods can be defined here if needed
    Optional<User> findByEmail(String email); // For finding user by email during login
}
