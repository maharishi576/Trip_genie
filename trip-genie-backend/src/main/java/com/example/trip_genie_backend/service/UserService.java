package com.example.trip_genie_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// import com.example.trip_genie_backend.repository.UserRepository;

/**
 * Service class for handling user-related business logic.
 * It uses a UserRepository to interact with the database.
 */
@Service
public class UserService {

    // @Autowired
    // private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Registers a new user.
     * 
     * 
     * 
     * The password is encrypted before the user is saved to the database.
     *
     * @param userDetails The User object with the new user's details.
     * @return The registered and saved User object.
     */
    // Demo: User registration logic commented out
    // public User registerUser(User userDetails) {
    //     String encodedPassword = passwordEncoder.encode(userDetails.getPassword());
    //     userDetails.setPassword(encodedPassword);
    //     User savedUser = userRepository.save(userDetails);
    //     System.out.println("New user registered and saved to database: " + savedUser.getEmail());
    //     return savedUser;
    // }
}
