package com.coagronet.persona.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.coagronet.persona.Persona;
import com.coagronet.persona.dtos.PersonaDTO;

@Mapper
public interface PersonaMapper {

    PersonaMapper INSTANCE = Mappers.getMapper(PersonaMapper.class);

    @Mapping(source = "tipoIdentificacion.id", target = "tipoIdentificacionId")
    PersonaDTO toDto(Persona persona);

    @Mapping(source = "tipoIdentificacionId", target = "tipoIdentificacion.id")
    Persona toEntity(PersonaDTO personaDTO);
}
