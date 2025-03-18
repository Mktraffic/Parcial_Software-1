package com.translogistics.parcial.mapper;

import com.translogistics.parcial.dto.RolConductorDTO;
import com.translogistics.parcial.model.RolConductor;
import org.mapstruct.Mapper;

//esta creo que no es necesaria

@Mapper(componentModel = "spring")
public interface RolConductorMapper {
    RolConductorDTO toDTO(RolConductor entity);
    RolConductor toEntity(RolConductorDTO dto);
}
