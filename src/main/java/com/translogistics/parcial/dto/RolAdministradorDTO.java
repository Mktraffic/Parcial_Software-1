package com.translogistics.parcial.dto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RolAdministradorDTO extends RolDTO {
    public RolAdministradorDTO(Long id, String nombreRol) {
        super(id, nombreRol);
    }
}
