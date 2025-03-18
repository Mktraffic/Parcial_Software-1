package com.translogistics.parcial.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UsuarioDTO {

    private String user;

    private int id;

    private String password;

    private RolDTO rol;

    private PersonaDTO persona;

}