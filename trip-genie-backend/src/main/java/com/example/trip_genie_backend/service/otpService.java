package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.util.*;

import com.sendinblue.ApiClient;
import com.sendinblue.auth.ApiKeyAuth;
import sibApi.TransactionalEmailsApi;
import sibModel.*;

@Service
public class OtpService {
    private Map<String, String> otpStorage = new HashMap<>();

    public boolean sendOtp(String email) {
        String otp = String.valueOf(100000 + new Random().nextInt(900000));
        otpStorage.put(email, otp);
        return sendEmail(email, otp);
    }

    public boolean verifyOtp(String email, String otp) {
        return otpStorage.containsKey(email) && otpStorage.get(email).equals(otp);
    }

    private boolean sendEmail(String to, String otp) {
        // 1. Configure API client
        ApiClient defaultClient = sibApi.Configuration.getDefaultApiClient();
        ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
        apiKey.setApiKey("YOUR_BREVO_API_KEY"); // 👈 Replace with your Brevo API Key

        TransactionalEmailsApi apiInstance = new TransactionalEmailsApi(defaultClient);

        // 2. Define Sender and Recipient
        SendSmtpEmailSender sender = new SendSmtpEmailSender()
                .name("Your App Name") // Optional: Sender's name
                .email("yourapp@example.com"); // 👈 Replace with your verified sender email

        List<SendSmtpEmailTo> toList = Collections.singletonList(
            new SendSmtpEmailTo().email(to)
        );

        // 3. Create the Email Object
        SendSmtpEmail sendSmtpEmail = new SendSmtpEmail()
                .sender(sender)
                .to(toList)
                .subject("Your OTP Code")
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