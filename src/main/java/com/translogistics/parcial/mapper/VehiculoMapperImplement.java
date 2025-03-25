package com.translogistics.parcial.mapper;
import org.springframework.stereotype.Component;

import com.translogistics.parcial.dto.VehiculoDTO;
import com.translogistics.parcial.model.Vehiculo;

@Component
public class VehiculoMapperImplement implements VehiculoMapper {

    @Override
    public VehiculoDTO toDTO(Vehiculo entity) {
        return new VehiculoDTO(entity.getPlaca(), entity.getModelo(), entity.getAnio(), entity.getTipo(), entity.getEstado());
    }

    @Override
    public Vehiculo toEntity(VehiculoDTO dto) {
        return new Vehiculo(dto.getPlaca(), dto.getModelo(), dto.getAnio(), dto.getTipo(), dto.getEstado());
    }

}
