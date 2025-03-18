package com.translogistics.parcial.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;
    
    private String user;

    private String password;

    private RolDTO rol;

    private PersonaDTO persona;

}