package com.coagronet.verificationToken.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coagronet.verificationToken.repositories.VerificationTokenRepository;

@Service
public class TokenCleanupService {

    private final VerificationTokenRepository verificationTokenRepository;

    public TokenCleanupService(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Transactional
    public void deleteExpiredTokens() {
        verificationTokenRepository.deleteByExpiryDateBefore(LocalDateTime.now());
        System.out.println("Tokens caducados eliminados.");
    }
}
