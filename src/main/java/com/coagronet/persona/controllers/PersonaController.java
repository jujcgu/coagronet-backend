package com.coagronet.persona.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.coagronet.infrastructure.configuration.ResponseHandler;
import com.coagronet.persona.Persona;
import com.coagronet.persona.services.PersonaService;

;

@RestController
@RequestMapping("/api/v1/personas")
@CrossOrigin(origins = "*")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @RequestMapping("/test")
    ResponseEntity<Object> getAllCountry() {
        return ResponseHandler.generateResponse(
                HttpStatus.OK,
                false,
                "Success",
                null);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addPersona(@RequestBody Persona persona) {
        try {
            personaService.addPersona(persona);
            return ResponseHandler.generateResponse(
                    HttpStatus.OK,
                    false,
                    "Persona creada exitosamente.",
                    null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    true,
                    "Error al crear la Persona.",
                    null);
        }
    }

    // Get all Personas
    @GetMapping("/all")
    public List<Persona> getAllPersonas() {
        return personaService.getAllPersonas();
    }

    // Get a Persona by ID
    @GetMapping("/{id}")
    public Persona getPersonaById(@PathVariable Long id) {
        return personaService.getPersonaById(id);
    }

    // Update a Persona
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updatePersona(@PathVariable Long id, @RequestBody Persona personaDetails) {
        try {
            Persona existingPersona = personaService.getPersonaById(id);
            if (existingPersona == null) {
                return ResponseHandler.generateResponse(
                        HttpStatus.NOT_FOUND,
                        false,
                        "Persona no encontrada.",
                        null);
            }
            personaService.updatePersona(id, personaDetails);
            return ResponseHandler.generateResponse(
                    HttpStatus.OK,
                    false,
                    "Persona actualizada exitosamente.",
                    null);

        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    true,
                    "Error al actualizar la Persona.",
                    null);
        }
    }

    // Delete a Persona
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deletePersona(@PathVariable Long id) {
        try {
            Persona existingPersona = personaService.getPersonaById(id);
            if (existingPersona == null) {
                return ResponseHandler.generateResponse(
                        HttpStatus.NOT_FOUND,
                        false,
                        "Persona no encontrada.",
                        null);

            }
            personaService.deletePersona(id);
            return ResponseHandler.generateResponse(
                    HttpStatus.OK,
                    false,
                    "Persona borrada exitosamente.",
                    null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    true,
                    "Error al borrar la Persona.",
                    null);
        }
    }

}
