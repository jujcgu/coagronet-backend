package com.coagronet.user.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coagronet.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
