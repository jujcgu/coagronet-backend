package com.coagronet.infrastructure.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.coagronet.role.Role;
import com.coagronet.user.User;
import com.coagronet.user.repositories.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Verificar los diferentes estados del usuario
        switch (user.getUsuarioEstado().getId().intValue()) {
            case 0: // Usuario desactivado
                throw new DisabledException("User account is deactivated.");
            case 1: // Usuario registrado, pero no se ha activado el email
                throw new DisabledException("User account is pending email verification.");
            case 2: // Usuario activado, pero no ha llenado información personal y no se ha asociado
                    // a una empresa
            case 3: // Usuario activado, ha llenado información personal, pero no se ha asociado a
                    // una empresa
            case 4: // Usuario activado y completo
                break; // Permitir el acceso
            default:
                throw new IllegalStateException("Unexpected value: " + user.getUsuarioEstado().getId());
        }

        Set<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(Role::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
