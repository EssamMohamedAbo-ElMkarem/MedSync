package com.medsync.authservice.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medsync.authservice.models.User;

public interface UserRepository extends JpaRepository<User, UUID>{
    Optional<User> findByEmail(String email);
}
