package com.coagronet.marca;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "marca")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "marca_generator")
    @SequenceGenerator(name = "marca_generator", sequenceName = "marca_mar_id_seq", allocationSize = 1)
    @Column(name = "mar_id")
    private Long id;

    @Column(name = "mar_nombre", length = 100)
    private String nombre;

    @Column(name = "mar_descripcion", length = 255)
    private String descripcion;

    @Column(name = "mar_estado", columnDefinition = "integer default 1")
    private Integer estado;

}
