package com.coagronet.usuarioEstado;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "usuario_estado")
public class UsuarioEstado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "use_id")
    private Integer id;

    @Column(name = "use_descripcion")
    private String descripcion;
}
