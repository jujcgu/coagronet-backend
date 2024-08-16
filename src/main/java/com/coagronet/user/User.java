package com.coagronet.user;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.coagronet.persona.Persona;
import com.coagronet.role.Role;
import com.coagronet.usuarioEstado.UsuarioEstado;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_id")
    private Long id;

    @Email
    @Column(name = "usu_email", unique = true, nullable = false)
    private String username;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$")
    @Column(name = "usu_password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "usu_persona_id", referencedColumnName = "per_id")
    private Persona persona;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usr_usuario_id"), inverseJoinColumns = @JoinColumn(name = "usr_rol_id"))
    private Set<Role> roles;

    @OneToOne
    @JoinColumn(name = "usu_estado", referencedColumnName = "use_id")
    private UsuarioEstado usuarioEstado;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Debugging: Print roles
        System.out.println("Roles for user " + username + ": " + roles);

        // return getRoles().stream()

        System.out.println("roles " + roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet()));

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
    }

    // Other methods from UserDetails interface
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}