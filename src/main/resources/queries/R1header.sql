select e.emp_nombre,
    s.sed_nombre,
    a.alm_nombre,
    m.mun_nombre,
    *
from almacen as a,
    sede as s,
    empresa as e,
    municipio as m
where a.alm_sede_id = s.sed_id
    and s.sed_empresa_id = e.emp_id
    and s.sed_municipio_id = m.mun_id
    and s.sed_id = 1