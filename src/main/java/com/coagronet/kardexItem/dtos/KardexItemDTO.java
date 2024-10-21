package com.coagronet.kardexItem.dtos;

import lombok.Data;

@Data
public class KardexItemDTO {
    private Integer id;
    private Integer kardexID;
    private Integer productoPresentacionID;
    private Double cantidad;
    private Double precio;
    private Integer estado;
}
