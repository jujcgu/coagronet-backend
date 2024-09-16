package com.coagronet.persona.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coagronet.persona.Persona;
import com.coagronet.persona.dtos.PersonaDTO;
import com.coagronet.persona.mappers.PersonaMapper;
import com.coagronet.persona.services.PersonaService;

@RestController
@RequestMapping("/api/v1/personas")
@CrossOrigin(origins = "*")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllPersonas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String field,
            @RequestParam(defaultValue = "asc") String sort) {

        // Convertir el parámetro sort en Sort.Direction
        Sort.Direction direction = Sort.Direction.fromString(sort);

        // Crear un objeto Sort con el campo y la dirección
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, field));

        // Obtener la página de personas
        Page<PersonaDTO> personaPage = personaService.getAllPersonas(pageable)
                .map(PersonaMapper.INSTANCE::toDto);

        // Construir la respuesta
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> header = new HashMap<>();
        header.put("totalElements", personaPage.getTotalElements());
        header.put("totalPages", personaPage.getTotalPages());
        header.put("size", personaPage.getSize());
        header.put("number", personaPage.getNumber());
        header.put("sort", personaPage.getSort());
        header.put("first", personaPage.isFirst());
        header.put("last", personaPage.isLast());
        header.put("numberOfElements", personaPage.getNumberOfElements());
        header.put("empty", personaPage.isEmpty());

        response.put("header", header);
        response.put("data", personaPage.getContent());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaDTO> getPersonaById(@PathVariable Long id) {
        Persona persona = personaService.getPersonaById(id);
        if (persona != null) {
            return ResponseEntity.ok(PersonaMapper.INSTANCE.toDto(persona));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PersonaDTO> createPersona(@RequestBody PersonaDTO personaDTO) {
        Persona persona = PersonaMapper.INSTANCE.toEntity(personaDTO);
        Persona savedPersona = personaService.savePersona(persona);
        return ResponseEntity.ok(PersonaMapper.INSTANCE.toDto(savedPersona));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaDTO> updatePersona(@PathVariable Long id, @RequestBody PersonaDTO personaDTO) {
        Persona persona = PersonaMapper.INSTANCE.toEntity(personaDTO);
        persona.setId(id);
        Persona updatedPersona = personaService.updatePersona(persona);
        return ResponseEntity.ok(PersonaMapper.INSTANCE.toDto(updatedPersona));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable Long id) {
        personaService.deletePersona(id);
        return ResponseEntity.noContent().build();
    }

}
