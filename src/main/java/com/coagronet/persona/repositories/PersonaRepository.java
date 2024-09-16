package com.coagronet.persona.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coagronet.persona.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    Page<Persona> findByEstadoNot(Integer estado, Pageable pageable);
}
