package com.coagronet.presentacion.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.coagronet.presentacion.Presentacion;

public interface PresentacionRepository extends JpaRepository<Presentacion, Long> {

    Presentacion findByIdAndEstado(Long id, Integer estado);

    Page<Presentacion> findAllByEstado(Integer estado, PageRequest pageRequest);

    @Query("select p from presentacion p where p.id = ?1")
    Presentacion findPresentacionById(Long id);

}
