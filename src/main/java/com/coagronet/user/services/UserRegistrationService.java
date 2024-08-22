package com.coagronet.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.coagronet.email.services.EmailVerificationService;
import com.coagronet.user.User;
import com.coagronet.user.repositories.UserRepository;
import com.coagronet.usuarioEstado.UsuarioEstado;

@Service
public class UserRegistrationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailVerificationService emailVerificationService;

    public void registerUser(User user) {
        user.setUsuarioEstado(UsuarioEstado.PENDING_VERIFICATION); // Estado inicial
        userRepository.save(user);
        emailVerificationService.sendVerificationEmail(user.getUsername());
    }

    public boolean activateUser(String email, String code) {
        if (emailVerificationService.verifyCode(email, code)) {
            User user = userRepository.findByUsername(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            user.setUsuarioEstado(UsuarioEstado.ACTIVE); // Cambiar el estado a activo
            userRepository.save(user);
            return true;
        }
        return false;
    }

}
