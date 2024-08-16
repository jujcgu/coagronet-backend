package com.coagronet.tipoIdentificacion.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coagronet.tipoIdentificacion.TipoIdentificacion;
import com.coagronet.tipoIdentificacion.repositories.TipoIdentificacionRepository;

@Service
public class TipoIdentificacionService {

    @Autowired
    private TipoIdentificacionRepository tipoIdentificacionRepository;

    // Create
    public TipoIdentificacion add(TipoIdentificacion tipoIdentificacion) {
        return tipoIdentificacionRepository.save(tipoIdentificacion);
    }

    // Read all
    public List<TipoIdentificacion> getAll() {
        return tipoIdentificacionRepository.findAll();
    }

    // Read by ID
    public TipoIdentificacion getById(Integer id) {
        Optional<TipoIdentificacion> tipoIdentificacion = tipoIdentificacionRepository.findById(id);
        return tipoIdentificacion.orElse(null);
    }

    // Update
    public TipoIdentificacion update(Integer id, TipoIdentificacion tipoIdentificacionDetails) {
        TipoIdentificacion tipoIdentificacion = getById(id);
        if (tipoIdentificacion != null) {
            tipoIdentificacion.setNombre(tipoIdentificacionDetails.getNombre());
            tipoIdentificacion.setDescripcion(tipoIdentificacionDetails.getDescripcion());
            tipoIdentificacion.setEstado(tipoIdentificacionDetails.getEstado());

            return tipoIdentificacionRepository.save(tipoIdentificacion);
        }
        return null;
    }

    // Delete
    public void delete(Integer id) {
        tipoIdentificacionRepository.deleteById(id);
    }

}
