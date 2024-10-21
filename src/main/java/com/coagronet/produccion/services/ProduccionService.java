package com.coagronet.produccion.services;

import com.coagronet.espacio.Espacio;
import com.coagronet.espacio.repositories.EspacioRepository;
import com.coagronet.produccion.Produccion;
import com.coagronet.produccion.repositories.ProduccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduccionService {

    @Autowired
    private ProduccionRepository produccionRepository;

    public List<Produccion> obtenerProduccionPorEspacios(Integer espacioId, Long empresaId) {
        return produccionRepository.buscarProduccionPorEspacio(espacioId, empresaId);
    }

}
