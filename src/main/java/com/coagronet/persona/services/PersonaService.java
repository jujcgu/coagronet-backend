package com.coagronet.persona.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coagronet.persona.Persona;
import com.coagronet.persona.repositories.PersonaRepository;
import com.coagronet.tipoIdentificacion.TipoIdentificacion;
import com.coagronet.tipoIdentificacion.repositories.TipoIdentificacionRepository;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private TipoIdentificacionRepository tipoIdentificacionRepository;

    public List<Persona> getAllPersonas() {
        // Filtra las personas cuyo estado no sea 2
        return personaRepository.findByEstadoNot(2);
    }

    public Persona getPersonaById(Long id) {
        // Asegúrate de que también se filtren por estado aquí si es necesario
        Persona persona = personaRepository.findById(id).orElse(null);
        return (persona != null && persona.getEstado() != 2) ? persona : null;
    }

    public Persona savePersona(Persona persona) {
        if (persona.getTipoIdentificacion() != null) {
            TipoIdentificacion tipoIdentificacion = tipoIdentificacionRepository
                    .findById(persona.getTipoIdentificacion().getId())
                    .orElseThrow(() -> new RuntimeException("TipoIdentificacion not found"));
            persona.setTipoIdentificacion(tipoIdentificacion);
        }
        return personaRepository.save(persona);
    }

    public Persona updatePersona(Persona persona) {
        return personaRepository.save(persona);
    }

    public void deletePersona(Long id) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona not found with id: " + id));
        persona.setEstado(2);
        personaRepository.save(persona);
    }
}
