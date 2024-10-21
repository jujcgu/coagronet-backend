package com.coagronet.bloque.controllers;

import com.coagronet.bloque.dtos.DatosListadoBloque;
import com.coagronet.userRole.UserRole;
import com.coagronet.bloque.Bloque;
import com.coagronet.bloque.services.BloqueService;
import com.coagronet.empresa.Empresa;
import com.coagronet.user.User;
import com.coagronet.user.repositories.UserRepository;
import com.coagronet.userRole.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/bloques")
@CrossOrigin(origins = "*")
public class BloqueController {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BloqueService bloqueService;

    private Empresa getEmpresaFromUser(User user) {
        return userRoleRepository.findByUser(user).stream()
                .map(UserRole::getEmpresa)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada para el usuario"));
    }

    private User getAuthenticatedUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

    @GetMapping("/short/{sedeId}")
    public ResponseEntity<List<DatosListadoBloque>> listadoBloques(@PathVariable Long sedeId) {
        User authenticatedUser = getAuthenticatedUser();
        Empresa empresa = getEmpresaFromUser(authenticatedUser);
        List<Bloque> bloques = bloqueService.obtenerBloquesPorSede(sedeId, empresa.getId());
        List<DatosListadoBloque> datosListadoBloques = bloques.stream().map(DatosListadoBloque::new).collect(Collectors.toList());
        return ResponseEntity.ok(datosListadoBloques);
    }
}

