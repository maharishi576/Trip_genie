package com.example.trip_genie_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:19006", "http://localhost:3000"})
public class SignupController {

    // 1. Request OTP
    @PostMapping("/signup/request-otp")
    public ResponseEntity<Map<String, Object>> requestOtp(@RequestBody SignupRequest request) {
        // Demo: OTP request logic
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "OTP sent to " + request.getEmail() + " (demo mode)");
        response.put("email", request.getEmail());
        return ResponseEntity.ok(response);
    }

    // 2. Verify OTP and register
    @PostMapping("/signup/verify-otp")
    public ResponseEntity<Map<String, Object>> verifyOtp(@RequestBody OtpVerifyRequest request) {
        // Demo: OTP verification logic
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "User registered successfully (demo mode)");
        response.put("user", Map.of(
            "id", "user_" + System.currentTimeMillis(),
            "email", request.getEmail(),
            "name", request.getName(),
            "status", "VERIFIED"
        ));
        return ResponseEntity.ok(response);
    }

    // DTOs
    public static class SignupRequest {
        private String email;
        private String name;
        private String mobile;

        // Getters and setters
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getMobile() { return mobile; }
        public void setMobile(String mobile) { this.mobile = mobile; }
    }

    public static class OtpVerifyRequest {
        private String email;
        private String otp;
        private String name;
        private String mobile;

        // Getters and setters
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getOtp() { return otp; }
        public void setOtp(String otp) { this.otp = otp; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getMobile() { return mobile; }
        public void setMobile(String mobile) { this.mobile = mobile; }
    }
}