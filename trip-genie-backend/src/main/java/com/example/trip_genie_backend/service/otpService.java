package com.example.trip_genie_backend.service;

import com.example.trip_genie_backend.model.Otp;
import com.example.trip_genie_backend.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sendinblue.ApiClient;
import com.sendinblue.auth.ApiKeyAuth;
import sibApi.TransactionalEmailsApi;
import sibModel.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class OtpService {

    // The HashMap is removed and replaced by the JPA repository
    @Autowired
    private OtpRepository otpRepository;

    // Injected from application.properties for better security and configuration
    @Value("${brevo.api.key}")
    private String brevoApiKey;

    @Value("${brevo.sender.email}")
    private String senderEmail;

    @Value("${brevo.sender.name}")
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
     * Sends an email using the Brevo (Sendinblue) API.
     * This method now uses configuration values injected from application.properties.
     */
    private boolean sendEmail(String to, String otp) {
        // 1. Configure API client using the injected API key
        ApiClient defaultClient = sibApi.Configuration.getDefaultApiClient();
        ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
        apiKey.setApiKey(brevoApiKey);

        TransactionalEmailsApi apiInstance = new TransactionalEmailsApi(defaultClient);

        // 2. Define Sender (from config) and Recipient
        SendSmtpEmailSender sender = new SendSmtpEmailSender()
                .name(senderName)
                .email(senderEmail);

        List<SendSmtpEmailTo> toList = Collections.singletonList(new SendSmtpEmailTo().email(to));

        // 3. Create the Email Object
        SendSmtpEmail sendSmtpEmail = new SendSmtpEmail()
                .sender(sender)
                .to(toList)
                .subject("Your One-Time Password")
                .htmlContent("<html><body><h1>Your OTP Code</h1><p>Your one-time password is: <b>" + otp + "</b></p><p>It is valid for 5 minutes.</p></body></html>")
                .textContent("Your OTP is: " + otp);

        try {
            // 4. Send the email
            CreateSmtpEmail result = apiInstance.sendTransacEmail(sendSmtpEmail);
            System.out.println("Email sent successfully. Message ID: " + result.getMessageId());
            return true;
        } catch (sibApi.ApiException e) {
            System.err.println("Exception when calling TransactionalEmailsApi#sendTransacEmail");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            e.printStackTrace();
            return false;
        }
    }
}