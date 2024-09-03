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
    private Long id;

    @Column(name = "use_descripcion", nullable = false, unique = true)
    private String nombre;

    public static final UsuarioEstado ACTIVE = new UsuarioEstado(1L, "ACTIVE");
    public static final UsuarioEstado PENDING_VERIFICATION = new UsuarioEstado(2L, "PENDING_VERIFICATION");
}

