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

    // Definir estados como constantes
    public static final UsuarioEstado DESACTIVADO = new UsuarioEstado(0L, "Usuario desactivado");
    public static final UsuarioEstado PENDIENTE_VERIFICACION = new UsuarioEstado(1L, "Usuario registrado, pero no se ha activado el email");
    public static final UsuarioEstado ACTIVADO_SIN_INFO = new UsuarioEstado(2L, "Usuario activado, pero no ha llenado información personal y no se ha asociado a una empresa");
    public static final UsuarioEstado ACTIVADO_SIN_EMPRESA = new UsuarioEstado(3L, "Usuario activado, ha llenado información personal, pero no se ha asociado a una empresa");
    public static final UsuarioEstado ACTIVADO_CON_EMPRESA = new UsuarioEstado(4L, "Usuario activado, ha llenado información personal y se ha asociado a una empresa");
}

