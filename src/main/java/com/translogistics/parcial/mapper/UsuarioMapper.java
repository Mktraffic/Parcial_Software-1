package com.translogistics.parcial.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.translogistics.parcial.dto.UsuarioDTO;
import com.translogistics.parcial.model.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioDTO toUsuarioDTO(Usuario usuario);

    Usuario toUsuario(UsuarioDTO usuarioDTO);
}