package com.example.trip_genie_backend.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.trip_genie_backend.model.Otp;
import com.example.trip_genie_backend.repository.OtpRepository;

@Service
public class otpService {

    private static final Logger logger = LoggerFactory.getLogger(otpService.class);

    // The HashMap is removed and replaced by the JPA repository
    @Autowired
    private OtpRepository otpRepository;

    // Mock email configuration - in production, use actual email service
    @Value("${app.sender.email:noreply@tripgenie.com}")
    private String senderEmail;

    @Value("${app.sender.name:Trip Genie}")
    private String senderName;

    private static final long OTP_VALID_DURATION_MINUTES = 5;

    /**
     * Generates an OTP, saves it to the database with an expiration time,
     * and sends it to the user's email.
     * @param email The recipient's email address.
     * @return true if the email was sent successfully, false otherwise.
     */
    @Transactional
    public boolean sendOtp(String email) {
        // Delete any existing OTP for this email to ensure only the latest one is valid.
        otpRepository.findByEmail(email).ifPresent(otpRepository::delete);

        String otpCode = String.valueOf(100000 + new Random().nextInt(900000));

        // Create a new Otp entity to be stored in the database
        Otp otp = new Otp();
        otp.setEmail(email);
        otp.setOtp(otpCode);
        otp.setExpiresAt(Instant.now().plus(OTP_VALID_DURATION_MINUTES, ChronoUnit.MINUTES));

        // Save the OTP to the 'otps' table
        otpRepository.save(otp);

        return sendEmail(email, otpCode);
    }

    /**
     * Verifies the provided OTP against the one stored in the database.
     * Checks for expiration and deletes the OTP upon successful verification.
     * @param email The user's email address.
     * @param otpCode The OTP code entered by the user.
     * @return true if the OTP is valid and not expired, false otherwise.
     */
    @Transactional
    public boolean verifyOtp(String email, String otpCode) {
        Optional<Otp> otpOptional = otpRepository.findByEmail(email);

        // Case 1: No OTP was ever requested for this email.
        if (otpOptional.isEmpty()) {
            return false;
        }

        Otp otp = otpOptional.get();

        // Case 2: The OTP has expired. Clean it up from the DB.
        if (otp.getExpiresAt().isBefore(Instant.now())) {
            otpRepository.delete(otp);
            return false;
        }

        // Case 3: The provided OTP is correct.
        if (otp.getOtp().equals(otpCode)) {
            // As requested, delete the OTP after successful verification.
            otpRepository.delete(otp);
            return true;
        }

        // Case 4: The OTP is incorrect.
        return false;
    }

    /**
     * Sends an email using a mock implementation.
     * In production, replace this with actual email service integration.
     */
    private boolean sendEmail(String to, String otp) {
        // Mock email sending - in production, integrate with actual email service
        logger.info("Sending OTP email to: {}", to);
        logger.info("OTP Code: {}", otp);
        logger.info("Subject: Your One-Time Password");
        logger.info("Content: Your OTP is: {} (valid for 5 minutes)", otp);
        
        // Simulate successful email sending
        // In production, replace with actual email service call
        return true;
    }
}