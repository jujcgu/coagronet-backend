package com.coagronet.produccion.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coagronet.empresa.Empresa;
import com.coagronet.produccion.Produccion;
import com.coagronet.produccion.dtos.DatosListadoProduccion;
import com.coagronet.produccion.services.ProduccionService;
import com.coagronet.user.User;
import com.coagronet.user.repositories.UserRepository;
import com.coagronet.userRole.UserRole;
import com.coagronet.userRole.repositories.UserRoleRepository;

@RestController
@RequestMapping("/api/v1/producciones")
@CrossOrigin(origins = "*")
public class ProduccionController {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ProduccionService produccionService;

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

    @GetMapping("/short/{espacioId}")
    public ResponseEntity<List<DatosListadoProduccion>> listadoProducciones(@PathVariable Integer espacioId) {
        User authenticatedUser = getAuthenticatedUser();
        Empresa empresa = getEmpresaFromUser(authenticatedUser);
        List<Produccion> producciones = produccionService.obtenerProduccionPorEspacios(espacioId, empresa.getId());
        List<DatosListadoProduccion> datosListadoProducciones = producciones.stream().map(DatosListadoProduccion::new).collect(Collectors.toList());
        return ResponseEntity.ok(datosListadoProducciones);
    }

}
