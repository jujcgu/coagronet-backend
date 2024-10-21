package com.coagronet.sede.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.coagronet.sede.Sede;
import com.coagronet.sede.dtos.SedeDTO;

@Mapper(componentModel = "spring")
public interface SedeMapper {

    SedeMapper INSTANCE = Mappers.getMapper(SedeMapper.class);

    @Mapping(source = "grupo.id", target = "grupo")
    @Mapping(source = "tipoSede.id", target = "tipoSede")
    @Mapping(source = "empresa.id", target = "empresa")
    @Mapping(source = "municipio.id", target = "municipio")
    SedeDTO toDto(Sede sede);

    @Mapping(source = "grupo", target = "grupo.id")
    @Mapping(source = "tipoSede", target = "tipoSede.id")
    @Mapping(source = "empresa", target = "empresa.id")
    @Mapping(source = "municipio", target = "municipio.id")
    Sede toEntity(SedeDTO dto);
}