package com.coagronet.sede.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.coagronet.empresa.Empresa;
import com.coagronet.sede.Sede;

public interface SedeRepository extends JpaRepository<Sede, Long> {

    Page<Sede> findByEstadoNotAndEmpresa(Integer estado, Empresa empresa, Pageable pageable);

    Optional<Sede> findByIdAndEstadoNotAndEmpresa(Long id, Integer estado, Empresa empresa); // Corrección aquí

    List<Sede> findByEstadoNotAndEmpresa(Integer estado, Empresa empresa, Sort sort);

    boolean existsByIdAndEmpresa(Long id, Empresa empresa);

}
