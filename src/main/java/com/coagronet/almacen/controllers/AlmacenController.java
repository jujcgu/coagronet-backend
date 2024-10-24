package com.coagronet.almacen.controllers;

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

import com.coagronet.almacen.Almacen;
import com.coagronet.almacen.dtos.DatosListadoAlmacen;
import com.coagronet.almacen.services.AlmacenService;
import com.coagronet.empresa.Empresa;
import com.coagronet.user.User;
import com.coagronet.user.repositories.UserRepository;
import com.coagronet.userRole.UserRole;
import com.coagronet.userRole.repositories.UserRoleRepository;

@RestController
@RequestMapping("/api/v1/almacenes")
@CrossOrigin(origins = "*")
public class AlmacenController {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AlmacenService almacenService;

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
    public ResponseEntity<List<DatosListadoAlmacen>> listadoProducciones(@PathVariable Long sedeId) {
        User authenticatedUser = getAuthenticatedUser();
        Empresa empresa = getEmpresaFromUser(authenticatedUser);
        List<Almacen> almacenes = almacenService.ObtenerAlmacenesPorSede(sedeId, empresa.getId());
        List<DatosListadoAlmacen> datosListadoAlmacenes = almacenes.stream().map(DatosListadoAlmacen::new).collect(Collectors.toList());
        return ResponseEntity.ok(datosListadoAlmacenes);
    }

}
