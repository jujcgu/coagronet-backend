package com.coagronet.produccion.repositories;

import com.coagronet.produccion.Produccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProduccionRepository extends JpaRepository<Produccion, Integer> {

    @Query(value = "select p.* from produccion as p, espacio as e, bloque as b, sede as s\n" +
            "where p.pro_espacio_id = e.esp_id \n" +
            "and e.esp_bloque_id = b.blo_id \n" +
            "and b.blo_sede_id = s.sed_id\n" +
            "and p.pro_estado != 2\n" +
            "and s.sed_empresa_id = :empresaId\n" +
            "and p.pro_espacio_id = :espacioId\n" +
            "order by pro_nombre asc",
            nativeQuery = true)
    List<Produccion> buscarProduccionPorEspacio(@Param("espacioId") Integer espacioId, @Param("empresaId") Long empresaId);
}
