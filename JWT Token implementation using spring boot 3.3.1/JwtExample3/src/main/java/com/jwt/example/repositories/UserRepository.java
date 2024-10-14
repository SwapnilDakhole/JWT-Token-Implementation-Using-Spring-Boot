package com.jwt.example.repositories;

import com.jwt.example.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByEmail(String email);
}