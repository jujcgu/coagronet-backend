package com.coagronet.persona.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class PersonaDTO {
    private Long id;
    private Long tipoIdentificacionId; // Solo el ID de TipoIdentificacion
    private String identificacion;
    private String apellido;
    private String nombre;
    private Boolean genero;
    private Date fechaNacimiento;
    private Integer estrato;
    private String direccion;
    private String celular;
    private Integer estado;
}
