package com.translogistics.parcial.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class RolDTO { 
    private Long id;
    private String nombreRol;

}
