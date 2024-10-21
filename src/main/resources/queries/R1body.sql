select A.kai_id,
    k.kar_fecha_hora,
    k.kar_almacen_id,
    k.kar_produccion_id,
    A.pro_nombre,
    A.kar_tipo_movimiento_id,
    A.cantidad,
    entrada,
    salida,
    (
        select sum(B.cantidad * prp_cantidad)
        from vista_kardex as B
        where B.prp_producto_id = A.prp_producto_id
            and B.kai_id <= A.kai_id
    ) AS saldo
from vista_kardex as A,
    kardex as k,
    kardex_item as ki
where A.kai_id = ki.kai_id
    and ki.kai_kardex_id = k.kar_id
    and A.prp_producto_id = 1
    and A.emp_id = 1
order by kai_id