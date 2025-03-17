package com.translogistics.parcial.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class RolConductorDTO {

    private int id;
    private String nombreRol;
    private String licencia;
    private String experiencia;
    
}
