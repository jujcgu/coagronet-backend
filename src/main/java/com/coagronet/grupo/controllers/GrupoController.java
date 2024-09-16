package com.coagronet.grupo.controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.coagronet.grupo.Grupo;
import com.coagronet.grupo.repositories.GrupoRepository;

@RestController
@RequestMapping("/api/v1/grupo")
public class GrupoController {
    private final GrupoRepository grupoRepository;

    private GrupoController(GrupoRepository grupoRepository) {
        this.grupoRepository = grupoRepository;
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<Grupo> findById(@PathVariable Long requestedId) {
        Optional<Grupo> grupoOptional = grupoRepository.findById(requestedId);
        if (grupoOptional.isPresent()) {
            return ResponseEntity.ok(grupoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    private ResponseEntity<Void> createGrupo(@RequestBody Grupo newGrupoRequest, UriComponentsBuilder ucb) {
        Grupo savedGrupo = grupoRepository.save(newGrupoRequest);
        URI locationOfNewGrupo = ucb
                .path("grupo/{id}")
                .buildAndExpand(savedGrupo.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewGrupo).build();
    }
}
