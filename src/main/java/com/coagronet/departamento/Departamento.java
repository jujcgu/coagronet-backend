package com.coagronet.departamento;

import com.coagronet.pais.Pais;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "departamento")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departamento_generator")
    @SequenceGenerator(name = "departamento_generator", sequenceName = "departamento_dep_id_seq", allocationSize = 1)
    @Column(name = "dep_id", nullable = false)
    private Integer id;

    @Column(name = "dep_nombre", length = 25)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "dep_pais_id", referencedColumnName = "pai_id")
    private Pais pais;

    @Column(name = "dep_codigo")
    private Integer codigo;

    @Column(name = "dep_acronimo", length = 3)
    private String acronimo;

}
