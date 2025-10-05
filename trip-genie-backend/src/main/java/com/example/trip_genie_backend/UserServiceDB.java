package com.example.trip_genie_backend;

import org.springframework.stereotype.Service;

@Service
public class UserServiceDB {
    // In reality, you would use a UserRepository/JPA here
    public User registerUser(User user) {
        // Save user to DB (pseudo-code)
        // userRepository.save(user);
        return user;
    }
}