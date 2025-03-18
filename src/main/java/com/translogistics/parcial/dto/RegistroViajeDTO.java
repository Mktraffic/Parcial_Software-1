package com.translogistics.parcial.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroViajeDTO {

    private int id;
    private String fechaViaje;
    private VehiculoDTO vehiculo;
    private RolConductorDTO conductor; // Cambiar a RolConductorDTO
}
