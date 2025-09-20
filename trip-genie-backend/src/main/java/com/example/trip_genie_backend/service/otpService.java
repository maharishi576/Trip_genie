package com.example.trip_genie_backend.service;

// import com.sendinblue.ApiClient;
// import com.sendinblue.auth.ApiKeyAuth;
// import sibApi.TransactionalEmailsApi;
// import sibModel.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.trip_genie_backend.model.Otp;
import com.example.trip_genie_backend.repository.OtpRepository;

@Service
public class OtpService {

    // The HashMap is removed and replaced by the JPA repository
    @Autowired
    private OtpRepository otpRepository;

    // Injected from application.properties for better security and configuration
    // Demo: Hardcoded sender info for SMTP stub
        private String senderEmail = "demo@tripgenie.com"; // Updated to match class name
    // private String senderName = "TripGenie Demo";

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
     * Sends an email using the Brevo (Sendinblue) API.
     * This method now uses configuration values injected from application.properties.
     */
    // Demo: Hardcoded SMTP stub for email sending
    private boolean sendEmail(String to, String otp) {
        System.out.println("[DEMO] Pretend to send OTP " + otp + " to " + to + " from " + senderEmail);
        return true; // Always succeed for demo
    }
}