package com.translogistics.parcial.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolConductorDTO extends RolDTO {
    private String licencia;
    private String experiencia;

    public RolConductorDTO(Long id, String nombreRol, String licencia, String experiencia) {
        super(id, nombreRol);
        this.licencia = licencia;
        this.experiencia = experiencia;
    }
}
