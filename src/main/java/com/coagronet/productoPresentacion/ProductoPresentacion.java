package com.coagronet.productoPresentacion;

import com.coagronet.marca.Marca;
import com.coagronet.presentacion.Presentacion;
import com.coagronet.producto.Producto;
import com.coagronet.unidad.Unidad;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "producto_presentacion")
public class ProductoPresentacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prp_id")
    private Integer id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "prp_producto_id")
    private Producto producto;

    @Column(name = "prp_nombre")
    private String nombre;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "prp_unidad_id")
    private Unidad unidad;

    @Column(name = "prp_descripcion")
    private String descripcion;

    @Column(name = "prp_estado", columnDefinition = "integer default 1")
    private Integer estado;

    @Column(name = "prp_cantidad")
    private Double cantidad;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "prp_marca_id")
    private Marca marca;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "prp_presentacion_id")
    private Presentacion presentacion;
}
