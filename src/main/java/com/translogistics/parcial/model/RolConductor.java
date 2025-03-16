package com.translogistics.parcial.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "rol_conductor")
@Data
@Builder
public class RolConductor {

    @Id
    @ManyToOne
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conductor")
    private int id;

    @Column(name = "nombre_rol", length = 50)
    @Size(min = 3, max = 50)
    private String nombreRol;

    @Column(name = "licencia", length = 50)
    @Size(min = 3, max = 50)
	private String licencia;

    @Column(name = "experiencia", length = 50)
    @Size(min = 3, max = 50)
	private String experiencia;

}