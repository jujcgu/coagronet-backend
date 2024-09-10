package com.coagronet.productoCategoria;

import com.coagronet.empresa.Empresa;

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
@Entity(name = "producto_categoria")
public class ProductoCategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prc_id")
    private Long id;

    @Column(name = "prc_nombre")
    private String nombre;

    @Column(name = "prc_descripcion")
    private String descripcion;

    @Column(name = "prc_estado", columnDefinition = "integer default 1")
    private Integer estado;

    @ManyToOne
    @JoinColumn(name = "prc_empresa_id", referencedColumnName = "emp_id")
    private Empresa owner;
}
