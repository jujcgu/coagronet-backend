package com.coagronet.infrastructure.security;

import java.util.Set;
import java.util.stream.Collectors;

// import org.hibernate.mapping.Set;
import com.coagronet.usuarioEstado.UsuarioEstado;
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

        // Verificar si el usuario no está activo
        if (!UsuarioEstado.ACTIVE.equals(user.getUsuarioEstado())) {
            throw new DisabledException("User account is not active.");
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


