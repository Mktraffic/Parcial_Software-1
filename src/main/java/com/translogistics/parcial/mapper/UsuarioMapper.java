package com.translogistics.parcial.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.translogistics.parcial.dto.UsuarioDTO;
import com.translogistics.parcial.model.Usuario;

@Mapper(componentModel = "spring", uses = {RolMapper.class, PersonaMapper.class})
public interface UsuarioMapper {
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioDTO toDTO(Usuario usuario);
    Usuario toEntity(UsuarioDTO usuarioDTO);
}