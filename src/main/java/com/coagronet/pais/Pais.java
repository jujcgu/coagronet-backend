package com.coagronet.pais;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "pais")
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pais_generator")
    @SequenceGenerator(name = "pais_generator", sequenceName = "pais_pai_id_seq", allocationSize = 1)
    @Column(name = "pai_id", nullable = false)
    private Integer id;

    @Column(name = "pai_nombre", length = 25)
    private String nombre;

    @Column(name = "pai_codigo")
    private Integer codigo;

    @Column(name = "pai_acronimo", length = 3)
    private String acronimo;

}
