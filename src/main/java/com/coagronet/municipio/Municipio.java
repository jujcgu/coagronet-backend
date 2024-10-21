package com.coagronet.municipio;

import com.coagronet.departamento.Departamento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "municipio")
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "municipio_generator")
    @SequenceGenerator(name = "municipio_generator", sequenceName = "municipio_mun_id_seq", allocationSize = 1)
    @Column(name = "mun_id", nullable = false)
    private Integer id;

    @Column(name = "mun_nombre", length = 25)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "mun_departamento_id", referencedColumnName = "dep_id")
    private Departamento departamento;

    @Column(name = "mun_codigo")
    private Integer codigo;

    @Column(name = "mun_acronimo", length = 3)
    private String acronimo;
}
