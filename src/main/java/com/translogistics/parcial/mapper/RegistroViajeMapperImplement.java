package com.translogistics.parcial.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.translogistics.parcial.dto.RegistroViajeDTO;
import com.translogistics.parcial.dto.UsuarioDTO;
import com.translogistics.parcial.dto.VehiculoDTO;
import com.translogistics.parcial.model.RegistroViaje;
import com.translogistics.parcial.model.Usuario;
import com.translogistics.parcial.model.Vehiculo;

@Component
public class RegistroViajeMapperImplement implements RegistroViajeMapper {

    @Autowired
    private UsuarioMapperImplement UsuarioMapper;

    @Autowired
    private VehiculoMapperImplement vehiculoMapper;

    @Override
    public RegistroViajeDTO toDTO(RegistroViaje entity) {
        VehiculoDTO vehiculo = vehiculoMapper.toDTO(entity.getVehiculo());
        UsuarioDTO conductor = UsuarioMapper.toDTO(entity.getConductor());
        return new RegistroViajeDTO(entity.getId(), entity.getFechaViaje(), vehiculo, conductor);
    }

    @Override
    public RegistroViaje toEntity(RegistroViajeDTO dto) {
        Vehiculo vehiculo = vehiculoMapper.toEntity(dto.getVehiculo());
        Usuario conductor = UsuarioMapper.toEntity(dto.getConductor());
        return new RegistroViaje(dto.getId(), dto.getFechaViaje(), vehiculo, conductor);
    }

}
