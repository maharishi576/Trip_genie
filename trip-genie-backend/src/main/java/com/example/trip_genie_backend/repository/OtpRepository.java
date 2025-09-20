package com.example.trip_genie_backend.repository;
import java.time.Instant;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.trip_genie_backend.model.Otp;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {

    // Spring Data JPA will automatically create a query for this method name
    Optional<Otp> findByEmail(String email);
    
    // We'll use this later for cleanup
    void deleteByExpiresAtBefore(Instant now);
}