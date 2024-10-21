package com.coagronet.unidad.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.coagronet.unidad.Unidad;
import com.coagronet.unidad.repositories.UnidadRepository;

@RestController
@RequestMapping("/api/v1/unidades")
@CrossOrigin(origins = "*")
public class UnidadController {

    private final UnidadRepository unidadRepository;

    private UnidadController(UnidadRepository unidadRepository) {
        this.unidadRepository = unidadRepository;
    }

    private Unidad findUnidad(Integer requestedId, Integer estado) {
        return unidadRepository.findByIdAndEstado(requestedId, 1);
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<Unidad> findById(@PathVariable Integer requestedId, Integer estado) {
        Unidad unidad = findUnidad(requestedId, 1);
        if (unidad != null) {
            return ResponseEntity.ok(unidad);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Unidad>> findAll(Pageable pageable, Integer estado) {
        Page<Unidad> page = unidadRepository.findAllByEstado(1, PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.by(Sort.Direction.ASC, "id"))));
        return ResponseEntity.ok(page.getContent());
    }

    @PostMapping
    private ResponseEntity<Void> createUnidad(@RequestBody Unidad newUnidadRequest,
            UriComponentsBuilder ucb) {
        Unidad unidad = new Unidad(null, newUnidadRequest.getNombre(),
                newUnidadRequest.getDescripcion(), newUnidadRequest.getEstado());
        Unidad savedUnidad = unidadRepository.save(unidad);
        URI locationOfNewUnidad = ucb
                .path("unidades/{id}")
                .buildAndExpand(savedUnidad.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewUnidad).build();
    }

    @PutMapping("/{requestedId}")
    private ResponseEntity<Void> putUnidad(@PathVariable Integer requestedId,
            @RequestBody Unidad unidadUpdate, Integer estado) {
        Unidad unidad = findUnidad(requestedId, 1);
        if (null != unidad) {
            Unidad updatedUnidad = new Unidad(unidad.getId(), unidadUpdate.getNombre(),
                    unidadUpdate.getDescripcion(), unidadUpdate.getEstado());
            unidadRepository.save(updatedUnidad);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteUnidad(@PathVariable Integer id) {
        if (unidadRepository.existsById(id)) {
            Unidad unidad = unidadRepository.getReferenceById(id);
            unidad.setEstado(2);
            unidadRepository.save(unidad);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
