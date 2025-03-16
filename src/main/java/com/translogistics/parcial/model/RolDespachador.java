package com.translogistics.parcial.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "rol_despachador")
@Data
@Builder
public class RolDespachador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_despachador")
	private int id;

    @Column(name = "nombre_rol", length = 50)
    @Size(min = 3, max = 50)
    private String nombreRol;

}
