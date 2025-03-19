package com.translogistics.parcial.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.translogistics.parcial.dto.RolAdministradorDTO;
import com.translogistics.parcial.dto.RolConductorDTO;
import com.translogistics.parcial.dto.RolDespachadorDTO;
import com.translogistics.parcial.dto.UsuarioDTO;
import com.translogistics.parcial.model.RolAdministrador;
import com.translogistics.parcial.model.RolConductor;
import com.translogistics.parcial.model.RolDespachador;
import com.translogistics.parcial.model.Usuario;

@Component
public class UsuarioMapperImplement implements UsuarioMapper {

    @Override
    public UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        if (!usuario.equals(null)) {
            PersonaMapperImplement personaMapper = new PersonaMapperImplement();

            if (usuario.getRol() instanceof RolAdministrador) {
                RolAdministradorDTO administradorDTO = new RolAdministradorDTO(usuario.getRol().getId(),
                        usuario.getRol().getNombreRol());
                usuarioDTO = new UsuarioDTO(
                        usuario.getId(),
                        usuario.getUser_name(),
                        usuario.getUser_password(),
                        administradorDTO,
                        personaMapper.toDTO(usuario.getPersona()));
            }
            if (usuario.getRol() instanceof RolConductor) {
                RolConductor conductor = (RolConductor) usuario.getRol();
                RolConductorDTO conductorF = new RolConductorDTO(conductor.getId(), "CONDUCTOR",
                        conductor.getLicencia(), conductor.getExperiencia());
                usuarioDTO = new UsuarioDTO(
                        usuario.getId(),
                        usuario.getUser_name(),
                        usuario.getUser_password(),
                        conductorF,
                        personaMapper.toDTO(usuario.getPersona()));
            }
            if (usuario.getRol() instanceof RolDespachador) {
                RolDespachadorDTO despachador = new RolDespachadorDTO(usuario.getId(), usuario.getRol().getNombreRol());
                usuarioDTO = new UsuarioDTO(
                        usuario.getId(),
                        usuario.getUser_name(),
                        usuario.getUser_password(),
                        despachador,
                        personaMapper.toDTO(usuario.getPersona()));
            }
        }
        return usuarioDTO;
    }

    @Override
    public Usuario toEntity(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();

        if (!usuarioDTO.equals(null)) {
            PersonaMapperImplement personaMapper = new PersonaMapperImplement();

            if (usuarioDTO.getRol() instanceof RolAdministradorDTO) {
                RolAdministrador administrador = new RolAdministrador(usuarioDTO.getRol().getId(),
                        usuarioDTO.getRol().getNombreRol());
                usuario = new Usuario(
                        usuario.getId(),
                        usuario.getUser_name(),
                        usuario.getUser_password(),
                        administrador,
                        personaMapper.toEntity(usuarioDTO.getPersona()));
            }
            if (usuarioDTO.getRol() instanceof RolConductorDTO) {
                RolConductorDTO conductor = (RolConductorDTO) usuarioDTO.getRol();
                RolConductor conductorF = new RolConductor(conductor.getId(), "CONDUCTOR", conductor.getLicencia(),
                        conductor.getExperiencia());
                usuario = new Usuario(
                        usuario.getId(),
                        usuario.getUser_name(),
                        usuario.getUser_password(),
                        conductorF,
                        personaMapper.toEntity(usuarioDTO.getPersona()));
            }
            if (usuarioDTO.getRol() instanceof RolDespachadorDTO) {
                RolDespachador despachador = new RolDespachador(usuarioDTO.getId(), usuarioDTO.getRol().getNombreRol());
                usuario = new Usuario(
                        usuario.getId(),
                        usuario.getUser_name(),
                        usuario.getUser_password(),
                        despachador,
                        personaMapper.toEntity(usuarioDTO.getPersona()));
            }
        }
        return usuario;
    }
}
