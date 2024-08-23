package com.coagronet.empresa.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.coagronet.empresa.Empresa;
import com.coagronet.empresa.dtos.EmpresaDTO;

@Mapper(componentModel = "spring")
public interface EmpresaMapper {
    @Mapping(source = "tipoIdentificacion.id", target = "tipoIdentificacionId")
    @Mapping(source = "persona.id", target = "personaId")
    EmpresaDTO toEmpresaDTO(Empresa empresa);

    @Mapping(source = "tipoIdentificacionId", target = "tipoIdentificacion.id")
    @Mapping(source = "personaId", target = "persona.id")
    Empresa toEmpresa(EmpresaDTO empresaDTO);
}

