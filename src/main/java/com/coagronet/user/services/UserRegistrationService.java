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
        return LocalDateTime.now().plus(24, ChronoUnit.HOURS);
    }

    public void registerUser(User user) {
        // Estado inicial: Usuario registrado, pero no se ha activado el email
        user.setUsuarioEstado(UsuarioEstado.PENDIENTE_VERIFICACION);
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
                verificationToken.setExpiryDate(calculateExpiryDate());
            } else {
                // Crear un nuevo token
                verificationToken = VerificationToken.builder()
                        .email(user.getUsername())
                        .token(verificationCode)
                        .expiryDate(calculateExpiryDate())
                        .build();
            }

            // Guardar el token en la base de datos
            verificationTokenRepository.save(verificationToken);

            // Enviar correo de verificación
            emailVerificationService.sendVerificationEmail(user.getUsername(), verificationCode);
        } catch (Exception e) {
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

                // Cambiar el estado a 2: Usuario activado, pero no ha llenado información
                // personal y no se ha asociado a una empresa
                user.setUsuarioEstado(UsuarioEstado.ACTIVADO_SIN_INFO);
                userRepository.save(user);

                return true; // Activación exitosa
            } else {
                throw new RuntimeException("User not found with email: " + verificationToken.getEmail());
            }
        } else {
            return false; // Token no válido o ha expirado
        }
    }
}
