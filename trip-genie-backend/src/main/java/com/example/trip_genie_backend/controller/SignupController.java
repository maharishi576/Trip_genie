package com.example.trip_genie_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class SignupController {

    @Autowired
    // private OtpService otpService;
    // @Autowired
    // private UserService userService;

    // 1. Request OTP
    @PostMapping("/signup/request-otp")
    public ResponseEntity<?> requestOtp(@RequestBody SignupRequest request) {
        // Demo: OTP request logic commented out
        boolean sent = true;
        if (sent) {
            return ResponseEntity.ok("OTP sent to (demo mode)");
        }
        return ResponseEntity.status(500).body("Failed to send OTP");
    }

    // 2. Verify OTP and register
    @PostMapping("/signup/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody OtpVerifyRequest request) {
        // Demo: OTP verification logic commented out
        boolean verified = true;
        if (verified) {
            return ResponseEntity.ok("User registered (demo mode)");
        }
        return ResponseEntity.status(401).body("Invalid OTP");
    }

    // DTOs
    public static class SignupRequest {
    // private String email;
        // + mobile, name, etc.
        // getters & setters
    }
    public static class OtpVerifyRequest {
    // private String email;
    // private String otp;
    // private User userDetails; // contains other fields
        // getters & setters
    }
}