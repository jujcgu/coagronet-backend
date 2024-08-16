package com.coagronet.tipoIdentificacion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coagronet.tipoIdentificacion.TipoIdentificacion;

@Repository
public interface TipoIdentificacionRepository extends JpaRepository<TipoIdentificacion, Integer> {
}
