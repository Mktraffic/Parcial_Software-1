package com.translogistics.parcial.mapper;

import com.translogistics.parcial.dto.RegistroViajeDTO;
import com.translogistics.parcial.model.RegistroViaje;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegistroViajeMapper {
    RegistroViajeDTO toDTO(RegistroViaje entity);
    RegistroViaje toEntity(RegistroViajeDTO dto);

}