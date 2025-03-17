package com.translogistics.parcial.dto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) // Para que herede de la clase padre
public class RolAdministradorDTO extends RolDTO {
}
