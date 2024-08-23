package com.coagronet.empresa;

import com.coagronet.persona.Persona;
import com.coagronet.tipoIdentificacion.TipoIdentificacion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Empresa {

    @Id
    @GeneratedValue
    @Column(name = "emp_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "emp_tipo_identificacion_id", referencedColumnName = "tii_id", columnDefinition = "integer default 6")
    private TipoIdentificacion tipoIdentificacion;

    @Column(name = "emp_identificacion")
    private String identificacion;

    @Column(name = "emp_nombre")
    private String nombre;

    @OneToOne
    @JoinColumn(name = "emp_persona_id", referencedColumnName = "per_id")
    private Persona persona;

    @Column(name = "emp_descripcion")
    private String descripcion;

    @Column(name = "emp_estado", columnDefinition = "integer default 1")
    private Integer estado;

    @Column(name = "emp_celular")
    private String celular;

    @Column(name = "emp_correo")
    private String correo;

    @Column(name = "emp_contacto")
    private String contacto;
}
