package com.translogistics.parcial.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Entity
@DiscriminatorValue("CONDUCTOR") // Se identifica como un conductor
@Data
public class RolConductor extends Rol {
    
    @Column(name = "licencia", length = 200)
    @Size(min = 3, max = 200)
    private String licencia;

    @Column(name = "anios_experiencia")
    private int experiencia;
}