package com.translogistics.parcial.mapper;

import org.springframework.stereotype.Component;

import com.translogistics.parcial.dto.RegistroViajeDTO;
import com.translogistics.parcial.dto.UsuarioDTO;
import com.translogistics.parcial.dto.VehiculoDTO;
import com.translogistics.parcial.model.RegistroViaje;
import com.translogistics.parcial.model.Usuario;
import com.translogistics.parcial.model.Vehiculo;

@Component
public class RegistroViajeMapperImplement implements RegistroViajeMapper {

    @Override
    public RegistroViajeDTO toDTO(RegistroViaje entity) {
        VehiculoMapperImplement vehiculoMapper = new VehiculoMapperImplement();
        VehiculoDTO vehiculo = vehiculoMapper.toDTO(entity.getVehiculo());
        UsuarioMapperImplement UsuarioMapper = new UsuarioMapperImplement();
        UsuarioDTO conductor = UsuarioMapper.toDTO(entity.getConductor());
        return new RegistroViajeDTO(entity.getId(), entity.getFechaViaje(), vehiculo, conductor);
    }

    @Override
    public RegistroViaje toEntity(RegistroViajeDTO dto) {
        VehiculoMapperImplement vehiculoMapper = new VehiculoMapperImplement();
        Vehiculo vehiculo = vehiculoMapper.toEntity(dto.getVehiculo());
        UsuarioMapperImplement UsuarioMapper = new UsuarioMapperImplement();
        Usuario conductor = UsuarioMapper.toEntity(dto.getConductor());
        return new RegistroViaje(dto.getId(), dto.getFechaViaje(), vehiculo, conductor);
    }

}
