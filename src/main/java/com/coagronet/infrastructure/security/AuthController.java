package com.coagronet.infrastructure.security;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coagronet.role.Role;
import com.coagronet.role.repositories.RoleRepository;
import com.coagronet.user.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Value("${jwt.default_role}")
    private String default_role;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(default_role);

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);

        return ResponseEntity.ok(myUserDetailsService.saveUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        String token = jwtService.createJwtToken(user.getUsername(), user.getPassword());
        return ResponseEntity.ok(token);
    }

    @GetMapping("/roles")
    public Set<String> getUserRoles() {
        System.out.println("roles()");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {

            System.out.println("aquiii");
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            System.out.println(userDetails);

            return userDetails.getAuthorities().stream()
                    .map(grantedAuthority -> grantedAuthority.getAuthority())
                    .collect(Collectors.toSet());
        }
        return null;
    }

    private String getUserName() {
        String currentUserName = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            // return authentication.getName();
            currentUserName = authentication.getName();
        }
        return currentUserName;
    }

    @GetMapping("/home")
    public String home() {

        String userName = this.getUserName();
        return "Welcome to the home page! UserName: " + userName;
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
