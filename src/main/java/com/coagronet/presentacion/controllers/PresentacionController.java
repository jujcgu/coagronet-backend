package com.coagronet.presentacion.controllers;

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

import com.coagronet.presentacion.Presentacion;
import com.coagronet.presentacion.repositories.PresentacionRepository;

@RestController
@RequestMapping("/api/v1/presentaciones")
@CrossOrigin(origins = "*")
public class PresentacionController {

    private final PresentacionRepository presentacionRepository;

    private PresentacionController(PresentacionRepository presentacionRepository) {
        this.presentacionRepository = presentacionRepository;
    }

    private Presentacion findPresentacion(Long requestedId, Integer estado) {
        return presentacionRepository.findByIdAndEstado(requestedId, 1);
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<Presentacion> findById(@PathVariable Long requestedId, Integer estado) {
        Presentacion presentacion = findPresentacion(requestedId, 1);
        if (presentacion != null) {
            return ResponseEntity.ok(presentacion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Presentacion>> findAll(Pageable pageable, Integer estado) {
        Page<Presentacion> page = presentacionRepository.findAllByEstado(1, PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.by(Sort.Direction.ASC, "id"))));
        return ResponseEntity.ok(page.getContent());
    }

    @PostMapping
    private ResponseEntity<Void> createPresentacion(@RequestBody Presentacion newPresentacionRequest,
            UriComponentsBuilder ucb) {
        Presentacion presentacion = new Presentacion(null, newPresentacionRequest.getNombre(),
                newPresentacionRequest.getDescripcion(), newPresentacionRequest.getEstado());
        Presentacion savedPresentacion = presentacionRepository.save(presentacion);
        URI locationOfNewPresentacion = ucb
                .path("presentaciones/{id}")
                .buildAndExpand(savedPresentacion.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewPresentacion).build();
    }

    @PutMapping("/{requestedId}")
    private ResponseEntity<Void> putPresentacion(@PathVariable Long requestedId,
            @RequestBody Presentacion presentacionUpdate, Integer estado) {
        Presentacion presentacion = findPresentacion(requestedId, 1);
        if (null != presentacion) {
            Presentacion updatedPresentacion = new Presentacion(presentacion.getId(), presentacionUpdate.getNombre(),
                    presentacionUpdate.getDescripcion(), presentacionUpdate.getEstado());
            presentacionRepository.save(updatedPresentacion);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deletePresentacion(@PathVariable Long id) {
        if (presentacionRepository.existsById(id)) {
            Presentacion presentacion = presentacionRepository.findPresentacionById(id);
            presentacion.setEstado(2);
            presentacionRepository.save(presentacion);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
