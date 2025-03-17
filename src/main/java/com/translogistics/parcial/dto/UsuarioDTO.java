package com.translogistics.parcial.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDTO {

    private String user;

    private String pasword;

    private String rol;

    private PersonaDTO persona;
}