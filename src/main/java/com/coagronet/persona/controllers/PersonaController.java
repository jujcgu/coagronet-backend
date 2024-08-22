package com.coagronet.persona.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<PersonaDTO> getAllPersonas() {
        return personaService.getAllPersonas()
                .stream()
                .map(PersonaMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
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
