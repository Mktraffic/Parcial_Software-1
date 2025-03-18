package com.translogistics.parcial.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RolDespachadorDTO extends RolDTO {
    public RolDespachadorDTO(Long id, String nombreRol) {
        super(id, nombreRol);
    }
}
