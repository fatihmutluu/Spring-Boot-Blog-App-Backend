package com.fatih.blogrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatih.blogrestapi.model.Role;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
