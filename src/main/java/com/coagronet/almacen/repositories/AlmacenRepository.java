package com.coagronet.almacen.repositories;

import com.coagronet.almacen.Almacen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlmacenRepository extends JpaRepository<Almacen, Integer> {

    @Query(value = "select a.* from almacen as a, sede as s \n" +
            "where a.alm_sede_id = s.sed_id \n" +
            "and a.alm_estado != 2\n" +
            "and s.sed_empresa_id = :empresaId\n" +
            "and a.alm_sede_id = :sedeId\n" +
            "order by alm_nombre asc",
            nativeQuery = true)
    List<Almacen> buscarAlmacenesPorSede(@Param("sedeId") Long sedeId, @Param("empresaId") Long empresaId);

}
