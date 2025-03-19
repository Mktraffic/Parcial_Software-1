package com.translogistics.parcial.mapper;

import org.springframework.stereotype.Component;

import com.translogistics.parcial.dto.RolAdministradorDTO;
import com.translogistics.parcial.dto.RolConductorDTO;
import com.translogistics.parcial.dto.RolDTO;
import com.translogistics.parcial.dto.RolDespachadorDTO;
import com.translogistics.parcial.model.Rol;
import com.translogistics.parcial.model.RolAdministrador;
import com.translogistics.parcial.model.RolConductor;
import com.translogistics.parcial.model.RolDespachador;

@Component
public class RolMapperImplement implements RolMapper {

    @Override
    public RolDTO toDTO(Rol rol, String licencia, int experiencia) {
        if (rol.getNombreRol().equals("Administrador")) {
            return new RolAdministradorDTO(rol.getId(), "Administrador");
        }
        if (rol.getNombreRol().equals("Conductor")) {
            return new RolConductorDTO(rol.getId(), "Conductor", licencia, experiencia);
        }
        if (rol.getNombreRol().equals("Despachador")) {
            return new RolDespachadorDTO(rol.getId(), "Despachador");
        }
        return null;
    }

    @Override
    public Rol toEntity(RolDTO dto) {
        if (dto instanceof RolAdministradorDTO) {
            return new RolAdministrador(dto.getId(), "Administrador");
        }
        if (dto instanceof RolConductorDTO) {
            RolConductorDTO conductorDTO = (RolConductorDTO) dto;
            return new RolConductor(conductorDTO.getId(), "Conductor", conductorDTO.getLicencia(), conductorDTO.getExperiencia());
        }
        if (dto instanceof RolDespachadorDTO) {
            return new RolDespachador(dto.getId(), "Despachador");
        }
        return null;
    }

}
