package com.coagronet.unidad.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.coagronet.unidad.Unidad;

public interface UnidadRepository extends JpaRepository<Unidad, Integer> {

    Unidad findByIdAndEstado(Integer id, Integer estado);

    Page<Unidad> findAllByEstado(Integer estado, PageRequest pageRequest);

    @Query("select u from unidad u where u.id = ?1")
    Unidad findUnidadById(Integer id);

}
