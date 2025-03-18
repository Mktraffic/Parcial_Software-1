package com.translogistics.parcial.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UsuarioDTO {

    private Long id;
    
    private String user;

    private String password;

    private RolDTO rol;

    private PersonaDTO persona;

}