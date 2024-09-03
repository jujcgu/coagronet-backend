package com.coagronet.user.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coagronet.email.services.EmailVerificationService;
import com.coagronet.user.User;
import com.coagronet.user.repositories.UserRepository;
import com.coagronet.usuarioEstado.UsuarioEstado;
import com.coagronet.verificationToken.VerificationToken;
import com.coagronet.verificationToken.repositories.VerificationTokenRepository;

@Service
public class UserRegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailVerificationService emailVerificationService;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    // Método para calcular la fecha de expiración del token
    private LocalDateTime calculateExpiryDate() {
        // Establece la fecha de expiración a 24 horas a partir del momento actual
        return LocalDateTime.now().plus(24, ChronoUnit.HOURS);
    }

    public void registerUser(User user) {
        user.setUsuarioEstado(UsuarioEstado.PENDING_VERIFICATION); // Estado inicial
        userRepository.save(user);

        // Generar código de verificación
        String verificationCode = UUID.randomUUID().toString();

        VerificationToken verificationToken;

        try {
            // Verificar si ya existe un token para este email
            Optional<VerificationToken> existingToken = verificationTokenRepository.findByEmail(user.getUsername());

            if (existingToken.isPresent()) {
                // Actualizar el token existente
                verificationToken = existingToken.get();
                verificationToken.setToken(verificationCode);
                verificationToken.setExpiryDate(calculateExpiryDate()); // Expiración en 24 horas
            } else {
                // Crear un nuevo token
                verificationToken = VerificationToken.builder()
                        .email(user.getUsername())
                        .token(verificationCode)
                        .expiryDate(calculateExpiryDate()) // Expiración en 24 horas
                        .build();
            }

            // Guardar el token en la base de datos
            verificationTokenRepository.save(verificationToken);

            // Enviar correo de verificación
            emailVerificationService.sendVerificationEmail(user.getUsername(), verificationCode);
        } catch (Exception e) {
            // Manejar cualquier excepción que ocurra al intentar guardar el token
            throw new RuntimeException("Error al generar o guardar el token de verificación.", e);
        }
    }

    public boolean activateUser(String token) {
        Optional<VerificationToken> tokenOptional = verificationTokenRepository.findByToken(token);
        if (tokenOptional.isPresent()) {
            VerificationToken verificationToken = tokenOptional.get();

            Optional<User> userOptional = userRepository.findByUsername(verificationToken.getEmail());
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                user.setUsuarioEstado(UsuarioEstado.ACTIVE); // Activa la cuenta
                userRepository.save(user);
                return true; // Retorna true si la activación es exitosa
            } else {
                throw new RuntimeException("User not found with email: " + verificationToken.getEmail());
            }
        } else {
            return false; // Retorna false si el token no es válido o ha expirado
        }
    }
}
