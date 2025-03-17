package com.translogistics.parcial.dto;

import com.translogistics.parcial.model.RolConductor;
import com.translogistics.parcial.model.Vehiculo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroViaje {

    private int id;
    private String fechaViaje;
    private Vehiculo vehiculo;
    private RolConductor conductor;
}
