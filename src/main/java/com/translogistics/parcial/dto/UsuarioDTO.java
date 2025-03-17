package com.translogistics.parcial.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class UsuarioDTO {

    private String user;

    private int id;

    private String password;

    private RolDTO rol;

    private PersonaDTO persona;

    public int getId() {
        return id;
    }

    public String getPasword() {
        return password;
    }
}