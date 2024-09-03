package com.coagronet.persona.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PersonaDTO {
    private Long id;
    private Long tipoIdentificacionId; // Solo el ID de TipoIdentificacion
    private String identificacion;
    private String apellido;
    private String nombre;
    private String genero;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    private Integer estrato;
    private String direccion;
    private String celular;
    private Integer estado;
}
