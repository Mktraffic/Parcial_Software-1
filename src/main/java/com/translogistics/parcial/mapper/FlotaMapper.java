package com.translogistics.parcial.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.translogistics.parcial.dto.FlotaDTO;
import com.translogistics.parcial.model.Flota;


@Mapper(componentModel = "spring", uses = {VehiculoMapper.class, UsuarioMapper.class})
public interface FlotaMapper {

    FlotaMapper INSTANCE = Mappers.getMapper(FlotaMapper.class);

    FlotaDTO toFlotaDTO(Flota flota);

    Flota toFlota(FlotaDTO flotaDTO);
}