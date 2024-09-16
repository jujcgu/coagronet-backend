package com.coagronet.grupo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coagronet.grupo.Grupo;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long>{
    Page<Grupo> findById(Long id, PageRequest pageRequest);
}
