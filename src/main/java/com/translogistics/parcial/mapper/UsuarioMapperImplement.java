package com.translogistics.parcial.mapper;

import org.springframework.stereotype.Component;

import com.translogistics.parcial.dto.UsuarioDTO;
import com.translogistics.parcial.model.Usuario;
@Component
public class UsuarioMapperImplement {
    private RolMapper rolMapper;
    private PersonaMapper personaMapper;

    public UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getUser_name(),
                usuario.getUser_password(),
                usuario.getRol(),
                usuario.getPersona()
        );
    }

    public Usuario toEntity(UsuarioDTO usuarioDTO) {
        if (usuarioDTO == null) {
            return null;
        }
        return Usuario.builder()
                .id(usuarioDTO.getId())
                .user_name(usuarioDTO.getUser_name())
                .user_password(usuarioDTO.getUser_password())
                .rol(usuarioDTO.getRol())
                .persona(usuarioDTO.getPersona())
                .build();
    }
}
