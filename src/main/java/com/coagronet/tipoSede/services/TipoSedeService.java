package com.coagronet.tipoSede.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coagronet.tipoSede.TipoSede;
import com.coagronet.tipoSede.repositories.TipoSedeRepository;

@Service
public class TipoSedeService {

    @Autowired
    private TipoSedeRepository tipoSedeRepository;

    // Create
    public TipoSede addTipoSede(TipoSede tipoSede) {
        return tipoSedeRepository.save(tipoSede);
    }

    // Read all
    public List<TipoSede> getAllTipoSedes() {
        return tipoSedeRepository.findAll();
    }

    // Read by ID
    public TipoSede getTipoSedeById(Integer id) {
        Optional<TipoSede> tipoSede = tipoSedeRepository.findById(id);
        return tipoSede.orElse(null);
    }

    // Update
    public TipoSede updateTipoSede(Integer id, TipoSede tipoSedeDetails) {
        TipoSede tipoSede = getTipoSedeById(id);
        if (tipoSede != null) {
            tipoSede.setNombre(tipoSedeDetails.getNombre());
            tipoSede.setDescripcion(tipoSedeDetails.getDescripcion());
            tipoSede.setEstado(tipoSedeDetails.getEstado());
            return tipoSedeRepository.save(tipoSede);
        }
        return null;
    }

    // Delete
    public void deleteTipoSede(Integer id) {
        tipoSedeRepository.deleteById(id);
    }

}
