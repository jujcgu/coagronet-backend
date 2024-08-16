package com.coagronet.persona.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coagronet.persona.Persona;
import com.coagronet.persona.repositories.PersonaRepository;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    // Create
    public Persona addPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    // Read all
    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    // Read by ID
    public Persona getPersonaById(Long id) {
        Optional<Persona> persona = personaRepository.findById(id);
        return persona.orElse(null);
    }

    // Update
    public Persona updatePersona(Long id, Persona personaDetails) {
        Persona persona = getPersonaById(id);
        if (persona != null) {
            persona.setTipoIdentificacion(personaDetails.getTipoIdentificacion());
            persona.setIdentificacion(personaDetails.getIdentificacion());
            persona.setApellido(personaDetails.getApellido());
            persona.setNombre(personaDetails.getNombre());
            persona.setGenero(personaDetails.getGenero());
            persona.setFechaNacimiento(personaDetails.getFechaNacimiento());
            persona.setEstrato(personaDetails.getEstrato());
            persona.setDireccion(personaDetails.getDireccion());
            persona.setCelular(personaDetails.getCelular());
            persona.setEstado(personaDetails.getEstado());
            return personaRepository.save(persona);
        }
        return null;
    }

    // Delete
    public void deletePersona(Long id) {
        personaRepository.deleteById(id);
    }

}
