package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.model.User;

@Service
public class UserServiceDB {
    // In reality, you would use a UserRepository/JPA here
    public User registerUser(User user) {
        // Save user to DB (pseudo-code)
        // userRepository.save(user);
        return user;
    }
}