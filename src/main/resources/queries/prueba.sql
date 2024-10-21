INSERT INTO sede (
        sed_area,
        sed_comuna,
        sed_coordenadas,
        sed_descripcion,
        sed_empresa_id,
        tis_estado,
        sed_geolocalizacion,
        sed_grupo_id,
        sed_municipio_id,
        sed_nombre,
        sed_tipo_sede_id,
        sed_id
    )
VALUES (?, ?, CAST(? AS JSON), ?, ?, ?, ?, ?, ?, ?, ?, ?)