package com.translogistics.parcial.mapper;

import com.translogistics.parcial.dto.RolDespachadorDTO;
import com.translogistics.parcial.model.RolDespachador;
import org.mapstruct.Mapper;

//esta creo que no es necesaria

@Mapper(componentModel = "spring")
public interface RolDespachadorMapper {
    RolDespachadorDTO toDTO(RolDespachador entity);
    RolDespachador toEntity(RolDespachadorDTO dto);
}