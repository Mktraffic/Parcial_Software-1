package com.translogistics.parcial.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMINISTRADOR") // Valor que se guardará en la columna tipo_rol
public class RolAdministrador extends Rol {
    public RolAdministrador(Long id, String nombreRol) {
        super(id, nombreRol);
    }
}
