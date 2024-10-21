package com.coagronet.almacen.services;

import com.coagronet.almacen.Almacen;
import com.coagronet.almacen.repositories.AlmacenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlmacenService {

    @Autowired
    private AlmacenRepository almacenRepository;

    public List<Almacen> ObtenerAlmacenesPorSede(Long sedeId, Long empresaId) {
        return almacenRepository.buscarAlmacenesPorSede(sedeId, empresaId);
    }

}
