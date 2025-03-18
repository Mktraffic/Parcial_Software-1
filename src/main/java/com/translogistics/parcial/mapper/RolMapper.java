package com.translogistics.parcial.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.translogistics.parcial.dto.RolDTO;
import com.translogistics.parcial.model.Rol;
import com.translogistics.parcial.model.RolAdministrador;
import com.translogistics.parcial.model.RolConductor;
import com.translogistics.parcial.model.RolDespachador;

//esta creo que no es necesaria

@Mapper(componentModel = "spring", uses = {RolAdministradorMapper.class, RolConductorMapper.class, RolDespachadorMapper.class})
public interface RolMapper {

    @Mapping(target = "tipoRol", expression = "java(obtenerTipoRol(rol))")
    RolDTO toDTO(Rol rol);

    Rol toEntity(RolDTO dto);

    default String obtenerTipoRol(Rol rol) {
        if (rol.getClass().equals(RolAdministrador.class)) return "Administrador";
        if (rol.getClass().equals(RolConductor.class)) return "Conductor";
        if (rol.getClass().equals(RolDespachador.class)) return "Despachador";
        return "Desconocido";
    }
}