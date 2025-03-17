package com.translogistics.parcial.dto;
import java.util.ArrayList;

import com.translogistics.parcial.model.RegistroViaje;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PlanillaViajeDTO {
    private int id;
    private ArrayList<RegistroViaje> planilla;
 
}
