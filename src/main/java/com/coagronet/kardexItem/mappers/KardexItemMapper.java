package com.coagronet.kardexItem.mappers;

import com.coagronet.kardexItem.KardexItem;
import com.coagronet.kardexItem.dtos.KardexItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface KardexItemMapper {

    KardexItemMapper INSTANCE = Mappers.getMapper(KardexItemMapper.class);

    @Mapping(source = "kardex.id", target = "kardexID")
    @Mapping(source = "productoPresentacion.id", target = "productoPresentacionID")
    KardexItemDTO toDto(KardexItem kardexItem);

    @Mapping(source = "kardexID", target = "kardex.id")
    @Mapping(source = "productoPresentacionID", target = "productoPresentacion.id")
    KardexItem toEntity(KardexItemDTO kardexItemDTO);
}

