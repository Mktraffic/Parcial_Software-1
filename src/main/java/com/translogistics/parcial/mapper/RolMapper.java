package com.translogistics.parcial.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.translogistics.parcial.dto.RolDTO;
import com.translogistics.parcial.model.Rol;
import com.translogistics.parcial.model.RolAdministrador;
import com.translogistics.parcial.model.RolConductor;
import com.translogistics.parcial.model.RolDespachador;

@Mapper(componentModel = "spring", uses = {RolAdministradorMapper.class, RolConductorMapper.class, RolDespachadorMapper.class})
public interface RolMapper {

    @Mapping(target = "tipoRol", expression = "java(obtenerTipoRol(rol))")
    RolDTO toDTO(Rol rol);

    default String obtenerTipoRol(Rol rol) {
        if (rol instanceof RolAdministrador) return "Administrador";
        if (rol instanceof RolConductor) return "Conductor";
        if (rol instanceof RolDespachador) return "Despachador";
        return "Desconocido";
    }
}