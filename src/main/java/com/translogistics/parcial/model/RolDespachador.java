package com.translogistics.parcial.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DESPACHADOR") // Se identifica como un despachador
public class RolDespachador extends Rol {
}
