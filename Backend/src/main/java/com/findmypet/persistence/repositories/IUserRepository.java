package com.findmypet.persistence.repositories;

import com.findmypet.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = :input OR u.username = :input")
    Optional<User> findByEmailOrUsername(@Param("input") String input);
}
