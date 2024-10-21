package com.coagronet.espacio.services;

import com.coagronet.espacio.Espacio;
import com.coagronet.espacio.repositories.EspacioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspacioService {

    @Autowired
    private EspacioRepository espacioRepository;

    public List<Espacio> obtenerEspaciosPorBloque(Integer bloqueId, Long empresaId) {
        return espacioRepository.buscarEspacioPorBloque(bloqueId, empresaId);
    }

}
