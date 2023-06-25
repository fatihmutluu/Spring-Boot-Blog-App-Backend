package com.fatih.blogrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatih.blogrestapi.model.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
