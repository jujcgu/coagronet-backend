package com.coagronet.grupo;

import com.coagronet.empresa.Empresa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "grupo")
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gru_id")
    private Long id;

    @Column(name = "gru_nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "gru_empresa_id")
    private Empresa empresa;

    @Column(name = "gru_descripcion")
    private String descripcion;

    @Column(name = "gru_estado", columnDefinition = "integer default 1")
    private Integer estado;
}
