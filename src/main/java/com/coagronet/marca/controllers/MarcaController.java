package com.coagronet.marca.controllers;

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

import com.coagronet.marca.Marca;
import com.coagronet.marca.repositories.MarcaRepository;

@RestController
@RequestMapping("/api/v1/marcas")
@CrossOrigin(origins = "*")
public class MarcaController {

    private final MarcaRepository marcaRepository;

    private MarcaController(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    private Marca findMarca(Long requestedId, Integer estado) {
        return marcaRepository.findByIdAndEstado(requestedId, 1);
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<Marca> findById(@PathVariable Long requestedId, Integer estado) {
        Marca marca = findMarca(requestedId, 1);
        if (marca != null) {
            return ResponseEntity.ok(marca);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Marca>> findAll(Pageable pageable, Integer estado) {
        Page<Marca> page = marcaRepository.findAllByEstado(1, PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.by(Sort.Direction.ASC, "id"))));
        return ResponseEntity.ok(page.getContent());
    }

    @PostMapping
    private ResponseEntity<Void> createMarca(@RequestBody Marca newMarcaRequest,
            UriComponentsBuilder ucb) {
        Marca marca = new Marca(null, newMarcaRequest.getNombre(),
                newMarcaRequest.getDescripcion(), newMarcaRequest.getEstado());
        Marca savedMarca = marcaRepository.save(marca);
        URI locationOfNewMarca = ucb
                .path("marcas/{id}")
                .buildAndExpand(savedMarca.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewMarca).build();
    }

    @PutMapping("/{requestedId}")
    private ResponseEntity<Void> putMarca(@PathVariable Long requestedId,
            @RequestBody Marca marcaUpdate, Integer estado) {
        Marca marca = findMarca(requestedId, 1);
        if (null != marca) {
            Marca updatedMarca = new Marca(marca.getId(), marcaUpdate.getNombre(),
                    marcaUpdate.getDescripcion(), marcaUpdate.getEstado());
            marcaRepository.save(updatedMarca);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteMarca(@PathVariable Long id) {
        if (marcaRepository.existsById(id)) {
            Marca marca = marcaRepository.getReferenceById(id);
            marca.setEstado(2);
            marcaRepository.save(marca);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
