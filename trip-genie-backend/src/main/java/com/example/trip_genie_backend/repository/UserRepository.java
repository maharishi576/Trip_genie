package com.example.trip_genie_backend.repository;

import com.example.trip_genie_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for User entities.
 * Extends JpaRepository to provide standard database operations.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
