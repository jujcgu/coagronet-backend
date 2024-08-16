package com.coagronet.tipoSede.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coagronet.tipoSede.TipoSede;

@Repository
public interface TipoSedeRepository extends JpaRepository<TipoSede, Integer> {

}
