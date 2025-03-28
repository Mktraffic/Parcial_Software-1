package com.translogistics.parcial.mapper;


import com.translogistics.parcial.dto.VehiculoDTO;
import com.translogistics.parcial.model.Vehiculo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehiculoMapper {

    VehiculoDTO toDTO(Vehiculo entity);
    Vehiculo toEntity(VehiculoDTO dto);
}
