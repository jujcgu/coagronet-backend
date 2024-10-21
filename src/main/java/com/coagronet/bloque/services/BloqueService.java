package com.coagronet.bloque.services;

import com.coagronet.bloque.Bloque;
import com.coagronet.bloque.repositories.BloqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloqueService {

    @Autowired
    private BloqueRepository bloqueRepository;

    public List<Bloque> obtenerBloquesPorSede(Long sedeId, Long empresaId) {
        return bloqueRepository.buscarBloquePorSede(sedeId, empresaId);
    }

}
