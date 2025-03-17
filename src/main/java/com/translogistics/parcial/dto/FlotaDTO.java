package com.translogistics.parcial.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class FlotaDTO {

    private int id;
    private ArrayList<VehiculoDTO> vehiculos;
    private ArrayList<UsuarioDTO> conductores;
}