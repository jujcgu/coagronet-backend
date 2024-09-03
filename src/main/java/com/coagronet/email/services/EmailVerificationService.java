package com.coagronet.email.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.coagronet.verificationToken.VerificationToken;
import com.coagronet.verificationToken.repositories.VerificationTokenRepository;

@Service
public class EmailVerificationService {

    private final VerificationTokenRepository verificationTokenRepository;
    private final JavaMailSender mailSender;

    @Value("${app.verification-url}")
    private String verificationUrl; // URL base para la verificación

    public EmailVerificationService(VerificationTokenRepository verificationTokenRepository,
            JavaMailSender mailSender) {
        this.verificationTokenRepository = verificationTokenRepository;
        this.mailSender = mailSender;
    }

    public String createVerificationToken(String email) {
        String token = UUID.randomUUID().toString(); // Genera un UUID en lugar de un código numérico
        LocalDateTime expiryDate = LocalDateTime.now().plusHours(24);

        VerificationToken verificationToken = VerificationToken.builder()
                .email(email)
                .token(token)
                .expiryDate(expiryDate)
                .build();
        verificationTokenRepository.save(verificationToken);

        return token;
    }

    public void sendVerificationEmail(String email, String token) {
        String verificationLink = verificationUrl + "?token=" + token; // Construye el enlace de verificación

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Verify your account");
        message.setText("Click the link to verify your account: " + verificationLink);

        mailSender.send(message);

        System.out.println("Verification link sent to " + email + ": " + verificationLink);
    }

    public boolean verifyToken(String token) {
        Optional<VerificationToken> tokenOptional = verificationTokenRepository.findByToken(token);
        if (tokenOptional.isPresent()) {
            VerificationToken verificationToken = tokenOptional.get();
            if (!verificationToken.isExpired()) {
                verificationTokenRepository.delete(verificationToken);
                return true;
            }
        }
        return false;
    }
}
