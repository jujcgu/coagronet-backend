package com.coagronet.kardex.repositories;

import com.coagronet.kardex.Kardex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KardexRepository extends JpaRepository<Kardex, Integer> {

    Kardex findByIdAndEstadoNot(Integer id, Integer estado);

    Page <Kardex> findByEstadoNot(Integer estado, Pageable pageable);

    boolean existsById(long id);

}
