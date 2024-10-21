package com.coagronet.kardex.asemblers;

import com.coagronet.kardex.Kardex;
import com.coagronet.kardex.controllers.KardexController;
import com.coagronet.kardex.dtos.KardexDTO;
import com.coagronet.kardex.mappers.KardexMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class KardexModelAssembler implements RepresentationModelAssembler<Kardex, EntityModel<KardexDTO>> {

    private final KardexMapper kardexMapper;

    public KardexModelAssembler(KardexMapper kardexMapper) {
        this.kardexMapper = kardexMapper;
    }

    @Override
    public EntityModel<KardexDTO> toModel(Kardex kardex) {
        KardexDTO kardexDTO = kardexMapper.toDto(kardex);
        return EntityModel.of(kardexDTO,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(KardexController.class).findKardex(kardex.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(KardexController.class).all(2, Pageable.unpaged())).withRel("kardex"));
    }
}

