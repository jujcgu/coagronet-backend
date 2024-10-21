package com.coagronet.marca.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.coagronet.marca.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long> {

    Marca findByIdAndEstado(Long id, Integer estado);

    Page<Marca> findAllByEstado(Integer estado, PageRequest pageRequest);

    @Query("select m from marca m where m.id = ?1")
    Marca findMarcaById(Long id);

}
