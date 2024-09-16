package com.coagronet.producto;

import com.coagronet.productoCategoria.ProductoCategoria;

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
@Entity(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_id")
    private Integer id;

    @Column(name = "pro_nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "pro_producto_categoria_id")
    private ProductoCategoria productoCategoria;

    @Column(name = "pro_descripcion")
    private String descripcion;

    @Column(name = "pro_estado", columnDefinition = "integer default 1")
    private Integer estado;

}
