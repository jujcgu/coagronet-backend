package com.coagronet.productoCategoria.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coagronet.empresa.Empresa;
import com.coagronet.productoCategoria.ProductoCategoria;

@Repository
public interface ProductoCategoriaRepository extends JpaRepository<ProductoCategoria, Long> {
    Page<ProductoCategoria> findByEstadoNotAndOwner(Integer estado, Empresa owner, Pageable pageable);

    Optional<ProductoCategoria> findByIdAndOwner(Long id, Empresa owner);

    boolean existsByIdAndOwner(Long id, Empresa owner);
}
