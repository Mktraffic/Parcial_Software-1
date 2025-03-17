package com.translogistics.parcial.mapper;

import com.translogistics.parcial.dto.PlanillaViajeDTO;
import com.translogistics.parcial.model.PlanillaViaje;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlanillaViajeMapper {
    PlanillaViajeDTO toDTO(PlanillaViaje entity);
    PlanillaViaje toEntity(PlanillaViajeDTO dto);

}
